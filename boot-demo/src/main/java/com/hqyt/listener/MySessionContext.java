/**
 * 
 */
package com.hqyt.listener;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

/**
* @Description: TODO
* @Author: CSZ
* @Date: 2018-09-11 15:01:58
*/
public class MySessionContext {

	private static HashMap<String, Object> sessionMap = new HashMap<>();
	
    public static synchronized void AddSession(HttpSession session) {
	    if (session != null) {
	    	sessionMap.put(session.getId(), session);
	    }
    }
    
	public static synchronized void DelSession(HttpSession session) {
		if (session != null) {
			sessionMap.remove(session.getId());
		}
	}
	
	public static synchronized HttpSession getSession(String session_id) {
		if (session_id == null) {
			return null;
		}
		return (HttpSession) sessionMap.get(session_id);
	}
}
