package com.ectrip.ec.ticket.service;

import java.util.Comparator;

public class MyComparator implements Comparator {

	public int compare(Object o1, Object o2) {
		return o1.toString().compareTo(o2.toString());
	}
}
