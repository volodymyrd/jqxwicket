package com.gmail.volodymyrdotsenko.jqxwicket.widgetspages.buttons;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

import com.gmail.volodymyrdotsenko.jqxwicket.core.Options;
import com.gmail.volodymyrdotsenko.jqxwicket.widgets.buttons.XButton;

public class ButtonsDefaultPage extends ButtonTemplatePage {

	private static final long serialVersionUID = 1L;

	final FeedbackPanel events = new FeedbackPanel("events");

	public ButtonsDefaultPage() {
		add(new XButton("jqxButton", new Options("width", "150")) {

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				ButtonsDefaultPage.this.info("Button " + this.getId()
						+ " clicked!");
				target.add(events);
			}
		});

		add(new XButton("jqxSubmitButton", new Options("width", "150")) {

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				ButtonsDefaultPage.this.info("Button " + this.getId()
						+ " clicked!");
				target.add(events);
			}
		});

		Options o = new Options();
		add(new XButton("jqxDisabledButton", o.set("width", "150").set(
				"disabled", true)) {

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				ButtonsDefaultPage.this.info("Button " + this.getId()
						+ " clicked!");
				target.add(events);
			}
		});

		add(events.setOutputMarkupId(true));
	}
}