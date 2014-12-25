package com.gmail.volodymyrdotsenko.jqxwicket;

import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.gmail.volodymyrdotsenko.jqxwicket.services.ProjectService;
import com.gmail.volodymyrdotsenko.jqxwicket.tests.DynamicComponentsPage;
import com.gmail.volodymyrdotsenko.jqxwicket.tests.PanelsTest;

public class HomePage extends TemplatePage {
	private static final long serialVersionUID = 1L;

	@SpringBean
	private ProjectService projectService;

	public HomePage() {
		
		add(new Link("tests") {
			@Override
			public void onClick() {
				// we redirect browser to another page.
				setResponsePage(DynamicComponentsPage.class);
			}
		});

		add(new Link("install") {
			@Override
			public void onClick() {
				projectService.install();
			}
		});

	}
}