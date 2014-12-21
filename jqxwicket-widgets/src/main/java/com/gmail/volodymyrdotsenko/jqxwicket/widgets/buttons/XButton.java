package com.gmail.volodymyrdotsenko.jqxwicket.widgets.buttons;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebComponent;
import org.apache.wicket.model.IModel;

import com.gmail.volodymyrdotsenko.jqxwicket.core.IJQueryWidget;
import com.gmail.volodymyrdotsenko.jqxwicket.core.JQueryBehavior;
import com.gmail.volodymyrdotsenko.jqxwicket.core.Options;

/**
 * Provides a jqxButton
 * 
 * @author Volodymyr Dotsenko
 * 
 */
public class XButton extends WebComponent implements IJQueryWidget,
		IXButtonListener {

	private static final long serialVersionUID = 1L;

	private final Options options;

	/**
	 * Constructor
	 * 
	 * @param id
	 *            the markup id
	 */
	public XButton(String id) {
		this(id, null, new Options());
	}

	/**
	 * Constructor
	 * 
	 * @param id
	 *            the markup id
	 * @param model
	 *            the {@link IModel}
	 */
	public XButton(String id, IModel<String> model) {
		this(id, model, new Options());
	}

	/**
	 * Constructor
	 * 
	 * @param id
	 *            the markup id
	 * @param options
	 *            {@link Options}
	 */
	public XButton(String id, Options options) {
		this(id, null, options);
	}

	/**
	 * Constructor
	 * 
	 * @param id
	 *            the markup id
	 * @param model
	 *            the list model of {@link ITab}<code>s</code>
	 * @param options
	 *            {@link Options}
	 */
	public XButton(String id, IModel<String> model, Options options) {
		super(id, model);

		setOutputMarkupId(true);

		this.options = options;
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();

		this.add(JQueryWidget.newWidgetBehavior(this)); // cannot be in ctor as
														// the markupId may be
														// set manually
														// afterward
	}

	@Override
	public void onBeforeRender(JQueryBehavior behavior) {
	}

	@Override
	public void onConfigure(JQueryBehavior behavior) {
		// if (!JQueryIcon.isNone(this.getIcon())) {
		// behavior.setOption("icons",
		// String.format("{ primary: '%s' }", this.getIcon()));
		// }
	}

	@Override
	public JQueryBehavior newWidgetBehavior(String selector) {
		return new XButtonBehavior(selector, options) {

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				XButton.this.onClick(target);
			}
		};
	}

	@Override
	public void onClick(AjaxRequestTarget target) {

	}
}