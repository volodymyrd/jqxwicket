package com.gmail.volodymyrdotsenko.jqxwicket.widgetspages.tabs;

import com.gmail.volodymyrdotsenko.jqxwicket.TemplatePage;
import com.gmail.volodymyrdotsenko.jqxwicket.widgets.JQueryUIBehavior;

public class DefaultTabsPage extends TemplatePage {
	private static final long serialVersionUID = 1L;

	public DefaultTabsPage() {
		this.add(new JQueryUIBehavior("#tabs", "jqxTabs"));
	}
}