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
package com.gmail.volodymyrdotsenko.jqxwicket.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.wicket.ajax.json.JSONObject;
import org.apache.wicket.util.io.IClusterable;

import com.gmail.volodymyrdotsenko.jqxwicket.core.utils.DateUtils;

/**
 * Provides a wrapper on a {@link Map} that will contains jQuery behavior
 * options (key/value).<br/>
 * the {@link #toString()} methods returns the JSON representation of the
 * options.
 * 
 * @author Sebastien Briquet - sebfz1
 * 
 */
public class Options implements IClusterable {
	private static final long serialVersionUID = 1L;

	public static final String QUOTE = "\"";

	/**
	 * Converts an object to its javascript representation. ie: "myvalue" (with
	 * the double quotes)<br/>
	 * If the supplied value is null, "null" is returned
	 * 
	 * @param value
	 *            the object
	 * @return the JSON value
	 */
	public static String asString(Object value) {
		return Options.asString(String.valueOf(value));
	}

	/**
	 * Converts a string to its javascript representation. ie: "myvalue" (with
	 * the double quotes)
	 * 
	 * @param value
	 *            the object
	 * @return the JSON value
	 */
	public static String asString(String value) {
		return JSONObject.quote(value);
	}

	/**
	 * Converts a list of strings to its javascript representation. ie: [
	 * "myvalue1", "myvalue2" ] (with the double quotes)
	 * 
	 * @param values
	 *            the {@link List} of values
	 * @return the JSON value
	 */
	public static String asString(List<String> values) {
		List<String> list = new ArrayList<String>();

		for (Object object : values) {
			list.add(Options.asString(object));
		}

		return list.toString();
	}

	/**
	 * Converts a date to its ISO8601/javascript representation. ie:
	 * "2009-11-05T13:15:30+0200" (with the double quotes)
	 * 
	 * @param date
	 *            the date to convert
	 * @return the JSON value
	 */
	public static String asDate(long date) {
		return Options.asDate(new Date(date));
	}

	/**
	 * Converts a date to its ISO8601/javascript representation. ie:
	 * "2009-11-05T13:15:30+0200" (with the double quotes)
	 * 
	 * @param date
	 *            the date to convert
	 * @return the JSON value
	 */
	public static String asDate(Date date) {
		return Options.asString(DateUtils.toISO8601(date));
	}

	/**
	 * Converts a list of options to a comma delimited string.
	 * 
	 * @param objects
	 *            options
	 * @return a comma delimited string
	 */
	public static String fromArray(Object... objects) {
		StringBuilder builder = new StringBuilder();

		for (int i = 0; i < objects.length; i++) {
			if (i > 0) {
				builder.append(", ");
			}

			builder.append(objects[i]);
		}

		return builder.toString();
	}

	/**
	 * Escape JSON quotes ({@value #QUOTE})
	 * 
	 * @param value
	 *            the string to escape
	 * @return the escaped string
	 * @deprecated use {@link JSONObject#quote(String)} instead TODO remove in
	 *             next version
	 */
	@Deprecated
	public static String escapeQuotes(String value) {
		if (value != null) {
			return value.replaceAll(QUOTE, "\\" + QUOTE);
		}

		return ""; // fixes #92
	}

	/**
	 * Helper method that adds a key/value JSON pair to the specified builder<br/>
	 * If the supplied value is null, "null" is returned
	 * 
	 * @param builder
	 *            the {@link StringBuilder}
	 * @param key
	 *            the key
	 * @param value
	 *            the object
	 */
	// FIXME: replace where appropriate
	// TODO: move to OptionsUtils
	public static void append(StringBuilder builder, String key, Object value) {
		builder.append(Options.QUOTE).append(key).append(Options.QUOTE)
				.append(": ").append(String.valueOf(value));
	}

	/**
	 * Helper method that adds a key/value JSON pair to the specified builder
	 * 
	 * @param builder
	 *            the {@link StringBuilder}
	 * @param key
	 *            the key
	 * @param value
	 *            the value
	 */
	// TODO: move to OptionsUtils
	public static void append(StringBuilder builder, String key, String value) {
		builder.append(Options.QUOTE).append(key).append(Options.QUOTE)
				.append(": ").append(JSONObject.quote(value));
	}

	private final Map<String, Object> map;

	/**
	 * Constructor.
	 */
	public Options() {
		this.map = new HashMap<String, Object>();
	}

	/**
	 * Constructor which adds an options defined by a key/value pair.
	 * 
	 * @param key
	 *            the option name
	 * @param value
	 *            the option value
	 */
	public Options(String key, Object value) {
		this();
		this.set(key, value);
	}

	/**
	 * Constructor which adds an array of objects, ie: "key" : ["value1",
	 * "value2"].
	 * 
	 * @param key
	 *            the option name
	 * @param values
	 *            the option values
	 */
	public Options(String key, Object... values) {
		this();
		this.set(key, values);
	}

	/**
	 * Constructor which a list of objects, ie: "key" : ["value1", "value2"].
	 * 
	 * @param key
	 *            the option name
	 * @param values
	 *            the option values
	 */
	public Options(String key, List<?> values) {
		this();
		this.set(key, values);
	}

	/**
	 * Returns the value to which the specified key is mapped, or null if this
	 * map contains no mapping for the key.
	 * 
	 * @param key
	 *            the key whose associated value is to be returned
	 * @return the value to which the specified key is mapped, or null if this
	 *         map contains no mapping for the key
	 */
	@SuppressWarnings("unchecked")
	public <T extends Object> T get(String key) {
		Object value = this.map.get(key);

		if (value != null) {
			return (T) value;
		}

		return null;
	}

	/**
	 * Adds or replace an option defined by a key/value pair.<br/>
	 * If for a given key, the value is null, then the pair is removed.
	 * 
	 * @param key
	 *            - key with which the specified value is to be associated
	 * @param value
	 *            - value to be associated with the specified key
	 * @return this, for chaining
	 */
	public final Options set(String key, Object value) {
		if (value == null) {
			this.map.remove(key);
		} else {
			this.map.put(key, value);
		}

		return this;
	}

	/**
	 * In addition to {@link #set(String, Object)} this enables to build trees
	 * of Options.
	 * 
	 * <p>
	 * Example:
	 * 
	 * <pre>
	 * Options o = new Options();
	 * o.set("foo", new Options("foo1", "value1"), new Options("foo2", Options.asString("value2")));
	 * 
	 * results in json:
	 * { "foo": [
	 *            { "foo1": value1 },
	 *            { "foo2": "value2" }
	 *          ]
	 * }
	 * </pre>
	 * 
	 * </p>
	 * 
	 * @param key
	 *            - key with which the specified value is to be associated
	 * @param values
	 *            - values to be associated with the specified key
	 * @return this, for chaining
	 */
	public final Options set(String key, Object... values) {
		return this.set(key, Arrays.asList(values));
	}

	/**
	 * In addition to {@link #set(String, Object)} this enables to build trees
	 * of Options.
	 * 
	 * <p>
	 * Example:
	 * 
	 * <pre>
	 * Options o = new Options();
	 * o.set("foo", new Options("foo1", "value1"), new Options("foo2", Options.asString("value2")));
	 * 
	 * results in json:
	 * { "foo": [
	 *            { "foo1": value1 },
	 *            { "foo2": "value2" }
	 *          ]
	 * }
	 * </pre>
	 * 
	 * </p>
	 * 
	 * @param key
	 *            - key with which the specified value is to be associated
	 * @param values
	 *            - values to be associated with the specified key
	 * @return this, for chaining
	 */
	public final Options set(String key, List<?> values) {
		return this.set(key, values.toString());
	}

	/**
	 * Gets a read-only entry set of options
	 * 
	 * @return an unmodifiable set of internal map entries
	 */
	public Set<Entry<String, Object>> entries() {
		return Collections.unmodifiableSet(this.map.entrySet());
	}

	/**
	 * Gets the JSON representation of the Options
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("{ ");

		int i = 0;
		for (Entry<String, Object> entry : this.map.entrySet()) {
			if (i++ > 0) {
				builder.append(", ");
			}

			builder.append(QUOTE).append(entry.getKey()).append(QUOTE)
					.append(": ").append(entry.getValue());
		}

		return builder.append(" }").toString();
	}
}