package com.gmail.volodymyrdotsenko.jqxwicket.tests;

import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.IMarkupResourceStreamProvider;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.resource.IResourceStream;
import org.apache.wicket.util.resource.StringResourceStream;

public class AutoMarkupGenPanel extends Panel implements
		IMarkupResourceStreamProvider {

	private static final long serialVersionUID = 1L;

	public AutoMarkupGenPanel(String id, IModel<?> model) {
		super(id, model);
	}

	public AutoMarkupGenPanel(String id) {
		super(id);
	}

	@Override
	public IResourceStream getMarkupResourceStream(MarkupContainer container,
			Class<?> containerClass) {
		String markup = "<div>Panel markup</div>";
		StringResourceStream resourceStream = new StringResourceStream(markup);

		return resourceStream;
	}
}