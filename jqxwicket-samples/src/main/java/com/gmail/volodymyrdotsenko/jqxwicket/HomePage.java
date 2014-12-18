package com.gmail.volodymyrdotsenko.jqxwicket;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.WebPage;

import com.gmail.volodymyrdotsenko.jqxwicket.widgetspages.ButtonsPage;
import com.gmail.volodymyrdotsenko.jqxwicket.widgetspages.TabsPage;

public class HomePage extends WebPage {
	private static final long serialVersionUID = 1L;

	public HomePage(final PageParameters parameters) {
		super(parameters);

		add(new Link("id_tabs_page_link") {
			@Override
			public void onClick() {
				// we redirect browser to another page.
				setResponsePage(TabsPage.class);
			}
		});

		add(new Link("id_buttons_page_link") {
			@Override
			public void onClick() {
				// we redirect browser to another page.
				setResponsePage(ButtonsPage.class);
			}
		});
		
		add(new Label("version", getApplication().getFrameworkSettings()
				.getVersion()));

		// TODO Add your page's components here

	}
}
