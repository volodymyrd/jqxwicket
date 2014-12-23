package com.gmail.volodymyrdotsenko.jqxwicket.tests;

import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.border.Border;
import org.apache.wicket.markup.html.panel.Panel;

import com.gmail.volodymyrdotsenko.jqxwicket.core.panel.BorderFragment;

public class MyWebCont extends BorderFragment{

	public MyWebCont(String id, String markupId, MarkupContainer markupProvider) {
		super(id, markupId, markupProvider);
	}


}