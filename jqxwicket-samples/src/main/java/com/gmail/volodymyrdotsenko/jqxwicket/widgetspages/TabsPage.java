package com.gmail.volodymyrdotsenko.jqxwicket.widgetspages;

import com.gmail.volodymyrdotsenko.jqxwicket.TemplatePage;
import com.gmail.volodymyrdotsenko.jqxwicket.widgets.JQueryUIBehavior;

public class TabsPage extends TemplatePage
{
	private static final long serialVersionUID = 1L;

	public TabsPage()
	{
		this.add(new JQueryUIBehavior("#tabs", "jqxTabs"));
	}
}