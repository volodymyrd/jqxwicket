package com.gmail.volodymyrdotsenko.jqxwicket.widgetspages.tabs;

import org.apache.wicket.markup.html.link.Link;

import com.gmail.volodymyrdotsenko.jqxwicket.TemplatePage;

public class TabsTemplatePage extends TemplatePage {

	private static final long serialVersionUID = 1L;

	public TabsTemplatePage() {
		super();

		add(new Link("jqxTabsDefault") {
			@Override
			public void onClick() {
				// we redirect browser to another page.
				setResponsePage(TabsDefaultPage.class);
			}
		});

		add(new Link("jqxTabsSubTabs") {
			@Override
			public void onClick() {
				// we redirect browser to another page.
				setResponsePage(TabsSimpleSubTabsPage.class);
			}
		});
	}
}