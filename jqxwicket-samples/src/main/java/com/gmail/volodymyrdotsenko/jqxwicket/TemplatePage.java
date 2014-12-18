package com.gmail.volodymyrdotsenko.jqxwicket;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;

public abstract class TemplatePage extends WebPage {

	private static final long serialVersionUID = 1L;

	public TemplatePage() {
		super();

		this.add(new Label("version", getApplication().getFrameworkSettings()
				.getVersion()));
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();

	}
}