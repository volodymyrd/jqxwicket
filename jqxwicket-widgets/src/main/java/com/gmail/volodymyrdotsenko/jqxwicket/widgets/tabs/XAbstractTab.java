package com.gmail.volodymyrdotsenko.jqxwicket.widgets.tabs;

import org.apache.wicket.extensions.markup.html.tabs.AbstractTab;
import org.apache.wicket.model.IModel;

public abstract class XAbstractTab extends AbstractTab implements IXTab{

	private static final long serialVersionUID = 1L;
	
	private boolean closable;

	public XAbstractTab(IModel<String> title) {
		super(title);
	}

	public XAbstractTab(IModel<String> title, boolean closable) {
		super(title);
		
		this.closable = closable;
	}

	@Override
	public boolean isClosable() {
		return closable;
	}

	@Override
	public void setClosable(boolean closable) {
		this.closable = closable;
	}
}