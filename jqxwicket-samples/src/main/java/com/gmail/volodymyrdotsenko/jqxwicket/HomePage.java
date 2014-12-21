package com.gmail.volodymyrdotsenko.jqxwicket;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;

import com.gmail.volodymyrdotsenko.jqxwicket.widgetspages.ButtonsPage;
import com.gmail.volodymyrdotsenko.jqxwicket.widgetspages.tabs.DefaultTabsPage;
import com.gmail.volodymyrdotsenko.jqxwicket.widgetspages.tabs.TabbedPanelPage;

public class HomePage extends TemplatePage {
	private static final long serialVersionUID = 1L;

	public HomePage() {


		add(new Link("id_tabs_panel_page_link") {
			@Override
			public void onClick() {
				// we redirect browser to another page.
				setResponsePage(TabbedPanelPage.class);
			}
		});
	}
}
