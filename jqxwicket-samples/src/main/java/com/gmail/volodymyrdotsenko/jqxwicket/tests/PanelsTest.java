package com.gmail.volodymyrdotsenko.jqxwicket.tests;

import org.apache.wicket.markup.html.WebPage;

public class PanelsTest extends WebPage{

	public PanelsTest() {
		super();
		
		MyWebCont cont1 = new MyWebCont("cont1", "", this);
		MyWebCont cont2 = new MyWebCont("cont2", "", cont1);
		add(cont1);
		cont1.add(cont2);
	}
}