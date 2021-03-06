package com.gmail.volodymyrdotsenko.jqxwicket;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;

import com.gmail.volodymyrdotsenko.jqxwicket.widgetspages.buttons.ButtonsDefaultPage;
import com.gmail.volodymyrdotsenko.jqxwicket.widgetspages.tabs.TabsDefaultPage;

public abstract class TemplatePage extends WebPage {

	private static final long serialVersionUID = 1L;

	public TemplatePage() {
		super();

		add(new Link("jqxTabs") {
			@Override
			public void onClick() {
				// we redirect browser to another page.
				setResponsePage(TabsDefaultPage.class);
			}
		});

		add(new Link("jqxButtons") {
			@Override
			public void onClick() {
				// we redirect browser to another page.
				setResponsePage(ButtonsDefaultPage.class);
			}
		});

		this.add(new Label("version", getApplication().getFrameworkSettings()
				.getVersion()));
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();

	}
}