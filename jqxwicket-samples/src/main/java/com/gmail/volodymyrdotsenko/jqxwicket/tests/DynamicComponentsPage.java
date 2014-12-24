package com.gmail.volodymyrdotsenko.jqxwicket.tests;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;

import com.gmail.volodymyrdotsenko.jqxwicket.widgets.buttons.XButton;
import com.gmail.volodymyrdotsenko.jqxwicket.widgets.tabs.IXTab;

public class DynamicComponentsPage extends WebPage {

	private static final long serialVersionUID = 1L;

	private ListView lw;

	public DynamicComponentsPage() {
		XButton b = new XButton("but") {
			@Override
			public void onClick(AjaxRequestTarget target) {
				// target.appendJavaScript("alert('click')");
				super.onClick(target);

				
				target.add(lw);
				// DynamicComponentsPage.this.add(new AutoMarkupGenPanel("q"));
			}
		};

		add(b);

		List<AutoMarkupGenPanel> components = new ArrayList<AutoMarkupGenPanel>();
		// components.add( new AutoMarkupGenPanel() );
		// components.add( new AutoMarkupGenPanel() );
		lw = new ListView<AutoMarkupGenPanel>("components", getModelObject()) {
			protected void populateItem(ListItem item) {
				item.add((AutoMarkupGenPanel) item.getModelObject());
			}
		};

		add(lw);
	}

	public List<AutoMarkupGenPanel> getModelObject() {
		List<AutoMarkupGenPanel> list = (List<AutoMarkupGenPanel>) this
				.getDefaultModelObject();

		if (list != null) {
			return list;
		}

		return Collections.emptyList();
	}
}