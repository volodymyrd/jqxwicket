/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.gmail.volodymyrdotsenko.jqxwicket.widgets.tabs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.attributes.CallbackParameter;
import org.apache.wicket.extensions.markup.html.tabs.ITab;

import com.gmail.volodymyrdotsenko.jqxwicket.widgets.JQueryUIBehavior;
import com.gmail.volodymyrdotsenko.jqxwicket.core.JQueryEvent;
import com.gmail.volodymyrdotsenko.jqxwicket.core.Options;
import com.gmail.volodymyrdotsenko.jqxwicket.core.ajax.IJQueryAjaxAware;
import com.gmail.volodymyrdotsenko.jqxwicket.core.ajax.JQueryAjaxBehavior;
import com.gmail.volodymyrdotsenko.jqxwicket.core.utils.RequestCycleUtils;

/**
 * Provides a jQuery tabs behavior.<br/>
 * Note, this class has almost the same code as AccordionBehavior
 * 
 * @author Sebastien Briquet - sebfz1
 * @since 1.2.1
 */
public abstract class TabsBehavior extends JQueryUIBehavior implements
		IJQueryAjaxAware, ITabsListener {

	private static final long serialVersionUID = 1L;
	private static final String METHOD = "jqxTabs";

	private JQueryAjaxBehavior createEventBehavior = null;
	private JQueryAjaxBehavior activateEventBehavior = null;

	// private JQueryAjaxBehavior activatingEventBehavior = null;

	/**
	 * Constructor
	 * 
	 * @param selector
	 *            the html selector (ie: "#myId")
	 */
	public TabsBehavior(String selector) {
		super(selector, METHOD);

		this.selector = selector;
	}

	/**
	 * Constructor
	 * 
	 * @param selector
	 *            the html selector (ie: "#myId")
	 * @param options
	 *            the {@link Options}
	 */
	public TabsBehavior(String selector, Options options) {
		super(selector, METHOD, options);

		this.selector = selector;
	}

	// Properties //
	/**
	 * Gets the reference {@link List} of {@link ITab}<tt>s</tt>.<br/>
	 * Usually the model object of the component on which this
	 * {@link TabsBehavior} is bound to.
	 * 
	 * @return a non-null {@link List}
	 */
	protected abstract List<ITab> getTabs();

	/**
	 * Gets a read-only {@link ITab} {@link List} having its visible flag set to
	 * true.
	 * 
	 * @return a {@link List} of {@link ITab}<tt>s</tt>
	 */
	protected List<ITab> getVisibleTabs() {
		List<ITab> list = new ArrayList<ITab>();

		for (ITab tab : this.getTabs()) {
			if (tab.isVisible()) {
				list.add(tab);
			}
		}

		return Collections.unmodifiableList(list);
	}

	// Methods //
	@Override
	public void bind(Component component) {
		super.bind(component);

		// if (this.isCreateEventEnabled()) {
		// component.add(this.createEventBehavior = this
		// .newActivateEventBehavior());
		// }

		if (this.isActivateEventEnabled()) {
			component.add(this.activateEventBehavior = this
					.newActivateEventBehavior());
		}

		// if (this.isActivatingEventEnabled()) {
		// component.add(this.activatingEventBehavior = this
		// .newActivatingEventBehavior());
		// }
	}

	/**
	 * Activates the selected tab, identified by the index
	 * 
	 * @param target
	 *            the {@link AjaxRequestTarget}
	 * @param index
	 *            the tab's index
	 */
	public void activate(int index, AjaxRequestTarget target) {
		target.appendJavaScript(this.$("'option'", "'selectedItem'", index));
	}

	// Events //
	@Override
	public void onConfigure(Component component) {
		super.onConfigure(component);

		// if (this.createEventBehavior != null) {
		// this.setOption("created",
		// this.createEventBehavior.getCallbackFunction());
		// }

		if (this.activateEventBehavior != null) {
			this.on("selected",
					this.activateEventBehavior.getCallbackFunction());
			// "function(event){ var selectedTab = event.args.item; "
			// + "console.log('The selected tab is '+ selectedTab)}");
		}

		// if (this.activatingEventBehavior != null) {
		// this.setOption("beforeActivate",
		// this.activatingEventBehavior.getCallbackFunction());
		// }
	}

	@Override
	public void onAjax(AjaxRequestTarget target, JQueryEvent event) {
		if (event instanceof ActivateEvent) {
			int index = ((ActivateEvent) event).getIndex();
			final List<ITab> tabs = this.getVisibleTabs();

			if (-1 < index && index < tabs.size()) /*
													 * index could be unknown
													 * depending on options and
													 * user action
													 */
			{
				ITab tab = tabs.get(index);

				if (tab instanceof AjaxTab) {
					((AjaxTab) tab).load(target);
				}

				if (event instanceof ActivatingEvent) {
					this.onActivating(target, index, tab);
				} else {
					this.onActivate(target, index, tab);
				}
			}
		}
	}

	// Factories //
	/**
	 * Gets a new {@link JQueryAjaxBehavior} that acts as the 'activate'
	 * javascript callback
	 * 
	 * @return the {@link JQueryAjaxBehavior}
	 */
	protected JQueryAjaxBehavior newActivateEventBehavior() {
		return new JQueryAjaxBehavior(this) {

			private static final long serialVersionUID = 1L;

			@Override
			protected CallbackParameter[] getCallbackParameters() {
				return new CallbackParameter[] {
						CallbackParameter.context("event"),
						// CallbackParameter.context("ui"),
						CallbackParameter.resolved("index", "event.args.item") };
			}

			@Override
			protected JQueryEvent newEvent() {
				return new ActivateEvent();
			}
		};
	}

	/**
	 * Gets a new {@link JQueryAjaxBehavior} that acts as the 'beforeActivate'
	 * javascript callback
	 * 
	 * @return the {@link JQueryAjaxBehavior}
	 */
	// protected JQueryAjaxBehavior newActivatingEventBehavior() {
	// return new JQueryAjaxBehavior(this) {
	//
	// private static final long serialVersionUID = 1L;
	//
	// @Override
	// protected CallbackParameter[] getCallbackParameters() {
	// return new CallbackParameter[] {
	// CallbackParameter.context("event"),
	// CallbackParameter.context("ui"),
	// CallbackParameter
	// .resolved("index",
	// "jQuery(event.target).jqxTabs('option', 'selectedItem')"), };
	// }
	//
	// @Override
	// protected JQueryEvent newEvent() {
	// return new ActivatingEvent();
	// }
	// };
	// }

	// Event objects //

	/**
	 * Provides an event object that will be broadcasted by the
	 * {@link JQueryAjaxBehavior} 'activate' callback
	 */
	protected static class ActivateEvent extends JQueryEvent {
		private final int index;

		/**
		 * Constructor
		 */
		public ActivateEvent() {
			super();

			this.index = RequestCycleUtils.getQueryParameterValue("index")
					.toInt(-1);
		}

		/**
		 * Gets the tab's index
		 * 
		 * @return the index
		 */
		public int getIndex() {
			return this.index;
		}
	}

	/**
	 * Provides an event object that will be broadcasted by the
	 * {@link JQueryAjaxBehavior} 'beforeActivate' callback
	 */
	protected static class ActivatingEvent extends ActivateEvent {
	}
}