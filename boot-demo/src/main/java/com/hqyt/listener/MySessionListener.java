/**
 * 
 */
package com.hqyt.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
* @Description: TODO
* @Author: CSZ
* @Date: 2018-09-11 14:55:34
*/
@WebListener
public class MySessionListener implements HttpSessionListener{

	@Override
	public void sessionCreated(HttpSessionEvent sessionEvent) {
		MySessionContext.AddSession(sessionEvent.getSession());
		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent sessionEvent) {
		MySessionContext.DelSession(sessionEvent.getSession());
		
	}

}
