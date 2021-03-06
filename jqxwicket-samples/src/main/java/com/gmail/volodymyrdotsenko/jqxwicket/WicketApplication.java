package com.gmail.volodymyrdotsenko.jqxwicket;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;

/**
 * Application object for your web application. If you want to run this
 * application without deploying, run the Start class.
 * 
 * @see com.gmail.volodymyrdotsenko.jqxwicket.JQXStart#main(String[])
 */
public class WicketApplication extends WebApplication {
	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override
	public Class<? extends WebPage> getHomePage() {
		return HomePage.class;
	}

	/**
	 * @see org.apache.wicket.Application#init()
	 */
	@Override
	public void init() {
		super.init();

		this.getResourceSettings().setThrowExceptionOnMissingResource(true);

		// Integrate Spring with Wicket
		getComponentInstantiationListeners().add(
				new SpringComponentInjector(this));
	}
}
