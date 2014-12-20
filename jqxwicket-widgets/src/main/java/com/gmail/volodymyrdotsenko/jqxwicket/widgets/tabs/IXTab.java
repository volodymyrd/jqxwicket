package com.gmail.volodymyrdotsenko.jqxwicket.widgets.tabs;

import org.apache.wicket.extensions.markup.html.tabs.ITab;

public interface IXTab extends ITab {

	boolean isCloseable();

	void setCloseable(boolean closeable);
}