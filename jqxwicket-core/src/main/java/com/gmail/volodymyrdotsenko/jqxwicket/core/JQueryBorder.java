package com.gmail.volodymyrdotsenko.jqxwicket.core;

import org.apache.wicket.markup.html.border.Border;
import org.apache.wicket.model.IModel;

public abstract class JQueryBorder extends Border implements IJQueryWidget {

	private static final long serialVersionUID = 1L;

	protected final Options options;

	/**
	 * Constructor
	 * 
	 * @param id
	 *            the markup id
	 */
	protected JQueryBorder(String id) {
		this(id, new Options());
	}

	/**
	 * Constructor
	 * 
	 * @param id
	 *            the markup id
	 * @param options
	 *            the {@link Options}
	 */
	public JQueryBorder(String id, Options options) {
		super(id);

		this.options = options;
	}

	/**
	 * Constructor
	 * 
	 * @param id
	 *            the markup id
	 * @param model
	 *            the {@link IModel}
	 */
	protected JQueryBorder(String id, IModel<?> model) {
		this(id, model, new Options());
	}

	/**
	 * Constructor
	 * 
	 * @param id
	 *            the markup id
	 * @param model
	 *            the {@link IModel}
	 * @param options
	 *            the {@link Options}
	 */
	public JQueryBorder(String id, IModel<?> model, Options options) {
		super(id, model);

		this.options = options;
	}

	// Events //

	@Override
	public void onConfigure(JQueryBehavior behavior) {
		// noop
	}

	@Override
	public void onBeforeRender(JQueryBehavior behavior) {
		// noop
	}
}