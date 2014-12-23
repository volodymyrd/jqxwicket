package com.gmail.volodymyrdotsenko.jqxwicket.widgetspages.tabs;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.gmail.volodymyrdotsenko.jqxwicket.core.Options;
import com.gmail.volodymyrdotsenko.jqxwicket.widgets.JQueryUIBehavior;
import com.gmail.volodymyrdotsenko.jqxwicket.widgets.tabs.AjaxTab;
import com.gmail.volodymyrdotsenko.jqxwicket.widgets.tabs.IXTab;
import com.gmail.volodymyrdotsenko.jqxwicket.widgets.tabs.SimpleTab;
import com.gmail.volodymyrdotsenko.jqxwicket.widgets.tabs.TabbedPanel;
import com.gmail.volodymyrdotsenko.jqxwicket.widgets.tabs.XAbstractTab;

public class TabsDefaultPage extends TabsTemplatePage {
	private static final long serialVersionUID = 1L;

	public TabsDefaultPage() {
		Options options = new Options();
		options.set("collapsible", true).set("width", "'90%'")
				.set("height", 700).set("position", "'top'");

		this.add(new TabbedPanel("tabs", this.newTabList(), options));
		// this.add(new JQueryUIBehavior("#tabs1", "jqxTabs", options));
	}

	private List<IXTab> newTabList() {
		List<IXTab> tabs = new ArrayList<IXTab>();

		// tab #1 //
		tabs.add(new SimpleTab(Model.of("Tab 1"), Model.of("my content"), true));

		// tab #2 //
		class TabsWithSubtabs extends XAbstractTab {

			private static final long serialVersionUID = 1L;

			private WebMarkupContainer cont;

			public TabsWithSubtabs(IModel<String> title) {
				super(title);
			}

			@Override
			public WebMarkupContainer getPanel(String panelId) {

				this.cont = new Fragment(panelId, "sub-tabs-panel",
						TabsDefaultPage.this);

				cont.add(new JQueryUIBehavior("#sub-tabs", "jqxTabs", new Options()));
				
				return cont;
			}

		}

		TabsWithSubtabs tabsWithSubtabs = new TabsWithSubtabs(Model.of("Tab 2"));

		tabs.add(tabsWithSubtabs);

		// tab #3 //
		tabs.add(new XAbstractTab(Model.of("Tab 3"), true) {

			private static final long serialVersionUID = 1L;

			@Override
			public WebMarkupContainer getPanel(String panelId) {
				return new Fragment(panelId, "panel-1", TabsDefaultPage.this);
			}
		});

		// tab #4 //
		tabs.add(new AjaxTab(Model.of("Tab (ajax)")) {

			private static final long serialVersionUID = 1L;

			@Override
			public WebMarkupContainer getLazyPanel(String panelId) {
				try {
					// sleep the thread for a half second to simulate a long
					// load
					Thread.sleep(500);
				} catch (InterruptedException e) {
					error(e.getMessage());
				}

				return new Fragment(panelId, "panel-2", TabsDefaultPage.this);
			}
		});

		return tabs;
	}
}