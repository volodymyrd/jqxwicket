package com.gmail.volodymyrdotsenko.jqxwicket.widgetspages.tabs;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.io.IClusterable;

import com.gmail.volodymyrdotsenko.jqxwicket.core.Options;
import com.gmail.volodymyrdotsenko.jqxwicket.widgets.JQueryUIBehavior;
import com.gmail.volodymyrdotsenko.jqxwicket.widgets.tabs.IXTab;
import com.gmail.volodymyrdotsenko.jqxwicket.widgets.tabs.SimpleTab;
import com.gmail.volodymyrdotsenko.jqxwicket.widgets.tabs.TabbedPanel;
import com.gmail.volodymyrdotsenko.jqxwicket.widgets.tabs.XAbstractTab;

interface IDynCompModel extends IClusterable {
}

public class TabsSimpleSubTabsPage extends TabsTemplatePage {

	private static final long serialVersionUID = 1L;

	private final WebMarkupContainer wmc = new WebMarkupContainer("wmc") {
		private static final long serialVersionUID = 1L;

		@Override
		protected void onInitialize() {
			super.onInitialize();

			this.removeAll();
		}
	};

	private final List<IDynCompModel> componentlList = new ArrayList<IDynCompModel>();

	TabbedPanel tabbedPanel = new TabbedPanel("ExtTabs", this.newExtTabList(),
			new Options()) {

		private static final long serialVersionUID = 1L;

		@Override
		public boolean isActivateEventEnabled() {
			return true;
		}

		@Override
		public void onActivate(org.apache.wicket.ajax.AjaxRequestTarget target,
				int index, IXTab tab) {
			if (index == 1 && componentlList.size() < 1) {
				componentlList.add(new IDynCompModel() {
					private static final long serialVersionUID = 1L;
				});

				target.add(wmc);
			}
		};
	};

	public TabsSimpleSubTabsPage() {
		add(tabbedPanel);
	}

	private List<IXTab> newExtTabList() {
		List<IXTab> tabs = new ArrayList<IXTab>();

		// Ext tab 1
		tabs.add(new SimpleTab(Model.of("ExtTab 1"), Model.of("my content"),
				false));

		// Ext tab 2 //
		tabs.add(new XAbstractTab(Model.of("ExtTab 2")) {

			private static final long serialVersionUID = 1L;

			@Override
			public WebMarkupContainer getPanel(final String panelId) {
				// SubFragment p = new SubFragment(panelId, "ExtMainPanel");

				Fragment f = new Fragment(panelId, "ExtPanel",
						TabsSimpleSubTabsPage.this);

				// Panel p = new BasePanel("ExtMainPanel", panelId);
				// f.add(new TabbedPanel("InnerTabs", TabsSimpleSubTabsPage.this
				// .newInnerTabList(), new Options().set("width", "'90%'")));

				ListView<IDynCompModel> lv = new ListView<IDynCompModel>(
						"components", getModelObject()) {

					private static final long serialVersionUID = 1L;

					@Override
					protected void onConfigure() {
						super.onConfigure();

						this.removeAll();
					}

					protected void populateItem(ListItem<IDynCompModel> item) {
						// item.add(new TabbedPanel("InnerTabs",
						// TabsSimpleSubTabsPage.this.newInnerTabList(),
						// new Options().set("width", "'90%'")));
						item.add(new JQueryUIBehavior("#InnerTabs", "jqxTabs",
								new Options().set("width", "'90%'")));
					}
				};

				lv.setOutputMarkupId(true);
				wmc.setOutputMarkupId(true);
				add(wmc);
				try {
					wmc.add(lv);
				} catch (Exception ex) {
					//ex.printStackTrace();
					setResponsePage(TabsSimpleSubTabsPage.class);
				}

				f.add(wmc);

				return f;
			}
		});

		return tabs;
	}

	public List<IDynCompModel> getModelObject() {

		return componentlList;
	}

	private List<IXTab> newInnerTabList() {
		List<IXTab> tabs = new ArrayList<IXTab>();

		// tab 1
		tabs.add(new SimpleTab(Model.of("Tab 1"), Model.of("my content1"),
				false));

		// tab 2
		tabs.add(new SimpleTab(Model.of("Tab 2"), Model.of("my content2"), true));

		return tabs;
	}

}