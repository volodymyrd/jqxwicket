package com.gmail.volodymyrdotsenko.jqxwicket.core.panel;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.IMarkupFragment;
import org.apache.wicket.markup.html.border.Border;
import org.apache.wicket.markup.html.panel.FragmentMarkupSourcingStrategy;
import org.apache.wicket.markup.html.panel.IMarkupSourcingStrategy;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.lang.Args;

public class BorderFragment extends Border {
	private static final long serialVersionUID = 1L;

	/** The wicket:id of the associated markup fragment */
	private final String associatedMarkupId;

	private final MarkupContainer markupProvider;

	/**
	 * Constructor.
	 * 
	 * @see org.apache.wicket.Component#Component(String)
	 * 
	 * @param id
	 *            The component id
	 * @param markupId
	 *            The associated id of the associated markup fragment
	 * @param markupProvider
	 *            The component whose markup contains the fragment's markup
	 */
	public BorderFragment(final String id, final String markupId,
			final MarkupContainer markupProvider) {
		this(id, markupId, markupProvider, null);
	}

	/**
	 * Constructor.
	 * 
	 * @see org.apache.wicket.Component#Component(String)
	 * 
	 * @param id
	 *            The component id
	 * @param markupId
	 *            The associated id of the associated markup fragment
	 * @param markupProvider
	 *            The component whose markup contains the fragment's markup
	 * @param model
	 *            The model for this fragment
	 */
	public BorderFragment(final String id, final String markupId,
			final MarkupContainer markupProvider, final IModel<?> model) {
		super(id, model);

		associatedMarkupId = Args.notNull(markupId, "markupId");
		this.markupProvider = markupProvider;
	}


	/**
	 * Get the markup stream which shall be used to search for the fragment
	 * 
	 * @param provider
	 * @return The markup stream to be used to find the fragment markup
	 */
	protected IMarkupFragment chooseMarkup(final MarkupContainer provider) {
		return provider.getMarkup(null);
	}

	/**
	 * @return the markup id associated to this Fragment
	 */
	public final String getAssociatedMarkupId() {
		return associatedMarkupId;
	}
}