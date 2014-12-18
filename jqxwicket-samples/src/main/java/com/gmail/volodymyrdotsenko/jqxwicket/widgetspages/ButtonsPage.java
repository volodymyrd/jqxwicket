package com.gmail.volodymyrdotsenko.jqxwicket.widgetspages;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

import com.gmail.volodymyrdotsenko.jqxwicket.TemplatePage;
import com.gmail.volodymyrdotsenko.jqxwicket.widgets.form.button.AjaxButton;


//import com.googlecode.wicket.jquery.ui.form.button.AjaxButton;


public class ButtonsPage extends TemplatePage {
	
	public ButtonsPage() {
		this.initialize();
	}

	private void initialize() {
		final Form<Void> form = new Form<Void>("form");
		this.add(form);

		// FeedbackPanel //
		final FeedbackPanel feedback = new FeedbackPanel("feedback");
		form.add(feedback.setOutputMarkupId(true));

		// Buttons //
		form.add(new AjaxButton("button1") {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				ButtonsPage.this.info(this);
				target.add(feedback);
			}
		});

		form.add(new AjaxButton("button2") {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				ButtonsPage.this.info(this);
				target.add(form);
			}
		});
	}

	private void info(Component component) {
		this.info(component.getMarkupId() + " has been clicked");
	}
}