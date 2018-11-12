package com.ectrip.ticket.sale;

import java.util.List;

public class countList {
  static List coulist;
  static String stdt;
  static int insertnumb;
	public static List getCoulist() {
		return coulist;
	}

	public static void setCoulist(List coulist) {
		countList.coulist = coulist;
	}

	public static String getStdt() {
		return stdt;
	}

	public static void setStdt(String stdt) {
		countList.stdt = stdt;
	}

	public static int getInsertnumb() {
		return insertnumb;
	}

	public static void setInsertnumb(int insertnumb) {
		countList.insertnumb = insertnumb;
	}
}

