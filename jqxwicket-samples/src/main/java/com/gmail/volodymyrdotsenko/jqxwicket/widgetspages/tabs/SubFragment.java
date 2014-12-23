package com.gmail.volodymyrdotsenko.jqxwicket.widgetspages.tabs;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.model.Model;

import com.gmail.volodymyrdotsenko.jqxwicket.core.Options;
import com.gmail.volodymyrdotsenko.jqxwicket.widgets.JQueryUIBehavior;
import com.gmail.volodymyrdotsenko.jqxwicket.widgets.tabs.IXTab;
import com.gmail.volodymyrdotsenko.jqxwicket.widgets.tabs.SimpleTab;
import com.gmail.volodymyrdotsenko.jqxwicket.widgets.tabs.TabbedPanel;

public class SubFragment extends BasePanel {

	public SubFragment(String id, String panelId) {
		super(id);

		// add(new Fragment(panelId, "InnerPanel", this).add(new TabbedPanel(
		// "InnerTabs", this.newInnerTabList(), new Options().set("width",
		// "'90%'"))));
		add(new Fragment(panelId, "InnerPanel", this).add(new JQueryUIBehavior(
				"#InnerTabs", "jqxTabs", new Options().set("width", "'90%'"))));
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

	private static final long serialVersionUID = 1L;
}