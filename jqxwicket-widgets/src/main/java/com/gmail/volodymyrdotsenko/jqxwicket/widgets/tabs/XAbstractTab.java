package com.gmail.volodymyrdotsenko.jqxwicket.widgets.tabs;

import org.apache.wicket.extensions.markup.html.tabs.AbstractTab;
import org.apache.wicket.model.IModel;

public abstract class XAbstractTab extends AbstractTab implements IXTab{

	private static final long serialVersionUID = 1L;
	
	private boolean closeable;

	public XAbstractTab(IModel<String> title) {
		super(title);
	}

	public XAbstractTab(IModel<String> title, boolean closeable) {
		super(title);
		
		this.closeable = closeable;
	}

	@Override
	public boolean isCloseable() {
		return closeable;
	}

	@Override
	public void setCloseable(boolean closeable) {
		this.closeable = closeable;
	}
}