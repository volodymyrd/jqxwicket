package com.gmail.volodymyrdotsenko.jqxwicket.tests;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.util.io.IClusterable;

import com.gmail.volodymyrdotsenko.jqxwicket.widgets.buttons.XButton;

interface IDynCompModel extends IClusterable {
}

public class DynamicComponentsPage extends WebPage {

	private static final long serialVersionUID = 1L;

	private final WebMarkupContainer wmc = new WebMarkupContainer("wmc");
	private final List<IDynCompModel> componentlList = new ArrayList<IDynCompModel>();

	public DynamicComponentsPage() {
		XButton b = new XButton("but") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				// target.appendJavaScript("alert('click')");
				super.onClick(target);

				componentlList.add(new IDynCompModel() {
					private static final long serialVersionUID = 1L;
				});

				target.add(wmc);
			}
		};

		add(b);

		ListView<IDynCompModel> lw = new ListView<IDynCompModel>("components",
				getModelObject()) {

			private static final long serialVersionUID = 1L;

			protected void populateItem(ListItem<IDynCompModel> item) {
				Label link = DynamicComponentsPage.this.newTitleLabel("link",
						"link");
				item.add(link);
			}
		};

		lw.setOutputMarkupId(true);
		wmc.setOutputMarkupId(true);
		add(wmc);
		wmc.add(lw);
	}

	protected Label newTitleLabel(String id, String title) {
		return new Label(id, title);
	}

	public List<IDynCompModel> getModelObject() {

		return componentlList;
	}
}