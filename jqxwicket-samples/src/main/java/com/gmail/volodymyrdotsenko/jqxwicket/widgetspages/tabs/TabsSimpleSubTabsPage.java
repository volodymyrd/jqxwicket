package com.gmail.volodymyrdotsenko.jqxwicket.widgetspages.tabs;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.markup.html.panel.IMarkupSourcingStrategy;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.html.panel.PanelMarkupSourcingStrategy;
import org.apache.wicket.model.Model;

import com.gmail.volodymyrdotsenko.jqxwicket.core.Options;
import com.gmail.volodymyrdotsenko.jqxwicket.widgets.tabs.IXTab;
import com.gmail.volodymyrdotsenko.jqxwicket.widgets.tabs.SimpleTab;
import com.gmail.volodymyrdotsenko.jqxwicket.widgets.tabs.TabbedPanel;
import com.gmail.volodymyrdotsenko.jqxwicket.widgets.tabs.XAbstractTab;

public class TabsSimpleSubTabsPage extends TabsTemplatePage {

	private static final long serialVersionUID = 1L;

	TabbedPanel tabbedPanel = new TabbedPanel("ExtTabs", this.newExtTabList(),
			new Options());

	public TabsSimpleSubTabsPage() {
		add(tabbedPanel);
	}

	private List<IXTab> newExtTabList() {
		List<IXTab> tabs = new ArrayList<IXTab>();

		// Ext tab 2 //
		tabs.add(new XAbstractTab(Model.of("ExtTab 2")) {

			private static final long serialVersionUID = 1L;

			@Override
			public WebMarkupContainer getPanel(String panelId) {
				// SubFragment p = new SubFragment(panelId, "ExtMainPanel");

				Fragment f = new Fragment(panelId, "ExtPanel",
						TabsSimpleSubTabsPage.this);

				Panel p = new BasePanel("ExtMainPanel", panelId);
				// f.add(new TabbedPanel("InnerTabs", TabsSimpleSubTabsPage.this
				// .newInnerTabList(), new Options().set("width", "'90%'")));
				f.add(p);

				return f;
			}
		});

		// Ext tab 1
		tabs.add(new SimpleTab(Model.of("ExtTab 1"), Model.of("my content"),
				false));

		return tabs;
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