package com.gmail.volodymyrdotsenko.jqxwicket.widgets.buttons;

import org.apache.wicket.ajax.AjaxRequestTarget;

/**
 * Event listener shared by the {@link XButton} widget and the
 * {@link ButtonBehavior}
 * 
 * @author Volodymyr Dotsenko
 * 
 */
interface IXButtonListener {

	/**
	 * Triggered when a button has been click <br/>
	 * 
	 * @param target
	 *            the {@link AjaxRequestTarget}
	 */
	void onClick(AjaxRequestTarget target);
}