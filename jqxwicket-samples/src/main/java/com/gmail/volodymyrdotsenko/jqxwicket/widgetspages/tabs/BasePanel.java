package com.gmail.volodymyrdotsenko.jqxwicket.widgetspages.tabs;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

import com.gmail.volodymyrdotsenko.jqxwicket.core.Options;
import com.gmail.volodymyrdotsenko.jqxwicket.widgets.JQueryUIBehavior;
import com.gmail.volodymyrdotsenko.jqxwicket.widgets.tabs.IXTab;
import com.gmail.volodymyrdotsenko.jqxwicket.widgets.tabs.SimpleTab;
import com.gmail.volodymyrdotsenko.jqxwicket.widgets.tabs.TabbedPanel;
import com.gmail.volodymyrdotsenko.jqxwicket.widgets.tabs.XAbstractTab;

public class BasePanel extends Panel {

	private static final long serialVersionUID = 1L;

	private String panelId;

	public BasePanel(String id, String panelId) {
		super(id);

		this.panelId = panelId;
		// Fragment f = new Fragment(id, "InnerPanel", this);

		// add(f);
	}

	@Override
	protected void onInitialize() {
		// TODO Auto-generated method stub
		super.onInitialize();

		// add(new JQueryUIBehavior("#InnerTabs", "jqxTabs", new Options().set(
		// "width", "'90%'")));

		add(new TabbedPanel("InnerTabs", this.newInnerTabList(),
				new Options().set("width", "'90%'")));
	}
	

	private List<IXTab> newInnerTabList() {
		List<IXTab> tabs = new ArrayList<IXTab>();

		// SubTab1
		tabs.add(new XAbstractTab(Model.of("Sub Tab1")) {

			private static final long serialVersionUID = 1L;

			@Override
			public WebMarkupContainer getPanel(String panelId) {

				Fragment f = new Fragment(BasePanel.this.panelId, "SubTab1",
						BasePanel.this);

				return f;
			}
		});

		// tab 2
		// tabs.add(new SimpleTab(Model.of("Tab 2"), Model.of("my content2"),
		// true));

		return tabs;
	}
}