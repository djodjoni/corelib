/*
 * Copyright 2007-2012 The Europeana Foundation
 *
 *  Licenced under the EUPL, Version 1.1 (the "Licence") and subsequent versions as approved
 *  by the European Commission;
 *  You may not use this work except in compliance with the Licence.
 * 
 *  You may obtain a copy of the Licence at:
 *  http://joinup.ec.europa.eu/software/page/eupl
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under
 *  the Licence is distributed on an "AS IS" basis, without warranties or conditions of
 *  any kind, either express or implied.
 *  See the Licence for the specific language governing permissions and limitations under
 *  the Licence.
 */
package eu.europeana.corelib.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

/**
 * String array util classes
 * 
 * @author Yorgos.Mamakis@ kb.nl
 * 
 */
public class StringArrayUtils {

	public static final String[] EMPTY_ARRAY = new String[0];

	/**
	 * Check if an array is not empty
	 * 
	 * @param array
	 * @return
	 */
	public static boolean isNotBlank(String[] array) {
		return ((array != null) && (array.length > 0)) && StringUtils.join(array).trim().length()>0;
		
	}

	/**
	 * Check if an array is empty
	 * 
	 * @param array
	 * @return
	 */
	public static boolean isBlank(String[] array) {
		return !isNotBlank(array);
	}

	/**
	 * Convert a list to array
	 * 
	 * @param list
	 * @return
	 */
	public static String[] toArray(List<String> list) {
		if (list != null) {
			return list.toArray(new String[list.size()]);
		}
		return new String[] {};
	}
	
	public static List<String> toList(String... items) {
		List<String> list = new ArrayList<String>();
		if ( isNotBlank(items) ) {
			for (String s : items) {
				if (StringUtils.isNotBlank(s)) {
					list.add(s);
				}
			}
		}
		return list;
	}
	
	public static Set<String> toSet(String... items) {
		Set<String> list = new HashSet<String>();
		if ( isNotBlank(items) ) {
			for (String s : items) {
				if (StringUtils.isNotBlank(s)) {
					list.add(s);
				}
			}
		}
		return list;
	}

	/**
	 * Adds a string array to alist of strings
	 * 
	 * @param list
	 * @param toAdd
	 */
	public static void addToList(List<String> list, String[] toAdd) {
		if ((list != null) && (isNotBlank(toAdd))) {
			for (String string : toAdd) {
				list.add(string);
			}
		}
	}

	/**
	 * Create a string representation of a string array
	 * 
	 * @param items
	 * @return
	 */
	public static String formatList(String[] items) {
		if (isNotBlank(items)) {
			if (items.length == 1) {
				return StringUtils.trim(items[0]);
			}
			StringBuilder sb = new StringBuilder();
			for (String item : items) {
				if (sb.length() > 0) {
					sb.append("| ");
				}
				sb.append(StringUtils.trim(item));
			}
			int p = sb.lastIndexOf("|");
			if (p > 0) {
				sb.replace(p, p + 1, " &");
			}
			return sb.toString().replace("|", ",");
		}
		return "";
	}

	/**
	 * Adds a string to a string array. If the array is null it creates it
	 * 
	 * @param items
	 * 			The array to append data in
	 * @param str
	 * 			The string to append
	 * @return The modified array
	 */
	public static String[] addToArray(String[] items, String str) {
		List<String> itemList;
		if (items == null) {
			itemList = new ArrayList<String>();
		} else {
			itemList = new ArrayList<String>(Arrays.asList(items));
		}
		if (str != null) {
			itemList.add(str);
		}
		return itemList.toArray(new String[itemList.size()]);

	}

}
