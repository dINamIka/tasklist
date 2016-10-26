package com.tasklist.domain;

import java.util.Arrays;

public interface StringCutter {
	
	/**
	 * cut the string for proper view in command line ui 
	 * @param str String that we will cut
	 * @param length desired length
	 * @return String with particular length. If length < actual str.length 
	 * then all missing gaps will switch on spaces, if length > actual str.length
	 * then last 3 signs will switch on "..."
	 */
	default String cutString(String str, int length) {
		if (length < 4) return "...";
		char[] array = new char[length];
	    Arrays.fill(array, ' ');
		String space = new String(array);
		if (str != null) {
			if (str.length() >= length) {
				return str.substring(0, length - 3) + "...";
			} else {
				return str + space.substring(0, length - str.length());
			}
		}
		return space;
	}

}
