package com.gmail.volodymyrdotsenko.jqxwicket.widgets.buttons;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.attributes.CallbackParameter;

import com.gmail.volodymyrdotsenko.jqxwicket.core.JQueryBehavior;
import com.gmail.volodymyrdotsenko.jqxwicket.core.JQueryEvent;
import com.gmail.volodymyrdotsenko.jqxwicket.core.Options;
import com.gmail.volodymyrdotsenko.jqxwicket.core.ajax.IJQueryAjaxAware;
import com.gmail.volodymyrdotsenko.jqxwicket.core.ajax.JQueryAjaxBehavior;
import com.gmail.volodymyrdotsenko.jqxwicket.widgets.JQueryUIBehavior;

/**
 * Provides a jqxButton behavior {@link JQueryBehavior}
 * 
 * @author Volodymyr Dotsenko
 */
public abstract class XButtonBehavior extends JQueryUIBehavior implements
		IJQueryAjaxAware, IXButtonListener {

	private static final long serialVersionUID = 1L;
	private static final String METHOD = "jqxButton";

	private JQueryAjaxBehavior clickEventBehavior = null;

	public XButtonBehavior(String selector) {
		super(selector, METHOD);
	}

	public XButtonBehavior(String selector, Options options) {
		super(selector, METHOD, options);
	}

	public XButtonBehavior(String selector, String icon) {
		super(selector, METHOD, new Options("icons", String.format(
				"{ primary: '%s' }", icon)));
	}

	@Override
	public void bind(Component component) {
		super.bind(component);

		component.add(this.clickEventBehavior = this.newClickEventBehavior());
	}

	@Override
	public void onConfigure(Component component) {
		super.onConfigure(component);

		this.on("click", this.clickEventBehavior.getCallbackFunction());
	}

	/**
	 * Gets a new {@link JQueryAjaxBehavior} that acts as the 'click' javascript
	 * callback
	 * 
	 * @return the {@link JQueryAjaxBehavior}
	 */
	protected JQueryAjaxBehavior newClickEventBehavior() {
		return new JQueryAjaxBehavior(this) {

			private static final long serialVersionUID = 1L;

			@Override
			protected CallbackParameter[] getCallbackParameters() {
				return new CallbackParameter[] { CallbackParameter
						.context("event") };
			}

			@Override
			protected JQueryEvent newEvent() {
				return new ClickEvent();
			}
		};
	}

	@Override
	public void onAjax(AjaxRequestTarget target, JQueryEvent event) {
		if (event instanceof ClickEvent) {
			this.onClick(target);
		}
	}

	// Event objects //

	/**
	 * Provides an event object that will be broadcasted by the
	 * {@link JQueryAjaxBehavior} 'click' callback
	 */
	protected static class ClickEvent extends JQueryEvent {
		/**
		 * Constructor
		 */
		public ClickEvent() {
			super();
		}
	}
}