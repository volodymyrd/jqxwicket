package com.gmail.volodymyrdotsenko.jqxwicket;

import org.apache.wicket.markup.html.link.Link;

import com.gmail.volodymyrdotsenko.jqxwicket.tests.DynamicComponentsPage;
import com.gmail.volodymyrdotsenko.jqxwicket.tests.PanelsTest;

public class HomePage extends TemplatePage {
	private static final long serialVersionUID = 1L;

	public HomePage() {
		add(new Link("tests") {
			@Override
			public void onClick() {
				// we redirect browser to another page.
				setResponsePage(DynamicComponentsPage.class);
			}
		});
	}
}