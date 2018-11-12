/*
 *
 * @(#)Debug.java	1.0, 2003/05/30
 *
 * Copyright 2003 Oaking Information, Inc. All rights reserved.
 * 系统中最重要的日志输出文件,一定要切记
 *
 */
package com.ectrip.base.util;

import java.io.*;
import java.text.*;

import org.apache.log4j.*;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;


/**
 * 日志打印类,
 * 调试完后可能通过 ectrip.xml DEBUG 的值进关闭.
 * @author lijin
 *
 */
public final class Debug {
  public static String FILEREALPATH   = WebContant.REALPATH	+ "/WEB-INF/ectrip.xml"; // 配置文件实际的路径
  private static  boolean debuggingOn = WebContant.GetKeyValue("DEBUG", "true").equalsIgnoreCase("true");
  private static  boolean islog4 = false;
  private static  Logger logger = Logger.getLogger("PRINT");
  private static  String SystemName = "平台";
  public static void alert(boolean condition) {
    if (!condition) {
    	
      println("Alert Failed: ");

      throw new IllegalArgumentException();
    }
  }
/**
 * 打印日志
 * @param msg
 */
  /**
   * 构造函数,配置是否打印日志
   */
  Debug()
  {
	  new WebContant();
	String ls_debug =WebContant.GetKeyValue("DEBUG", "false" );
	  if ( ls_debug.equalsIgnoreCase("ture"))
	     setDebuggingOn(true );
	  else
		 setDebuggingOn(false );
  }
  
  public static void print(String msg) {
     	println(msg);
  }
  public static void main(String[] args) {
		// TODO Auto-generated method stub
	    java.text.DecimalFormat df = new java.text.DecimalFormat("####.00");
	    System.out.println(df.format(434343.4));
		
	}
  public static void println(String msg) {

    StringBuffer ls_msg = new StringBuffer();
    if (debuggingOn) {
    	ls_msg.append(msg);

      if (islog4) {
        logger.debug("[log4]" +
                     (new SimpleDateFormat("'<'HH:mm:ss,SSS'>' ")).format(new
            java.util.Date()).concat(ls_msg.toString()));
      }
      else {
        System.out.println( (new SimpleDateFormat("'<'HH:mm:ss,SSS'>' ")).
                           format(new
                                  java.util.Date()).concat(ls_msg.toString()));
      }
    }
    ls_msg  = null ;
  }

  //Added On 2003-06-30
  public static void println(int msg) {
    println(msg + "");
  }


  public static void println(boolean msg) {
     println(msg + "");
   }

  public static void print(Exception e, String msg) {
    print( (Throwable) e, msg);
  }

  public static void println(Exception e) {
    print( (Throwable) e);
  }

  public static void print(Exception e) {
    print(e, null);
  }

  public static void print(int e) {
    print(e + "");
  }

  public static void print(Integer e) {
    print(e + "");
  }

  public static void print(Long e) {
    print(e + "");
  }

  public static void print(Float e) {
    print(e + "");
  }

  public static void print(Throwable t, String msg) {
    if (debuggingOn) {
       msg =SystemName+":类的方法"+sun.reflect.Reflection.getCallerClass(2)+"中:"+ msg;
      if (islog4) {
        logger.debug("log4" + "Received throwable with Message: " + t.getMessage());
        if (msg != null) {
          logger.error(msg);
        }
        t.printStackTrace();
      }
      else {
        System.out.println("Received throwable with Message: " + t.getMessage());
        if (msg != null) {
          System.err.print(msg);
        }
        t.printStackTrace();
      }
    }
  }

  public static void print(Throwable t) {
    print(t, null);
  }

  /**
   * Return the names of the callers.
   * @return java.lang.String - callers' names
   */
  public static String printStackTrace(Exception e) {
    String xRString = "";
    StringWriter xStrWtr = null;
    Throwable t = null;

    try {
      t = e;
      xStrWtr = new StringWriter();

      t.printStackTrace(new PrintWriter(xStrWtr));
      xRString = xStrWtr.getBuffer().toString();

      xStrWtr.close();
    }
    catch (IOException E) {}

    return xRString;
  }

  public static void showMemory(String pPrefix) {
    println(pPrefix + "[Heap Memory Status] FreeMem = " +
            Runtime.getRuntime().freeMemory() / 1024 + "K ; TotalMem = " +
            Runtime.getRuntime().totalMemory() / 1024 + "K");
  }

  public static void showMemory() {
    showMemory(SystemName+":内存为：");
  }
  
/**
 * @return Returns the debuggingOn.
 */
  
public static boolean isDebuggingOn() {
	return debuggingOn;
}
/**
 * @param debuggingOn The debuggingOn to set.
 */
public static void setDebuggingOn(boolean debuggingOn) {
	Debug.debuggingOn = debuggingOn;
}
/**
 * @return Returns the islog4.
 */

/**
 * @return Returns the logger.
 */
public static Logger getLogger() {
	return logger;
}
/**
 * @param logger The logger to set.
 */
public static void setLogger(Logger logger) {
	Debug.logger = logger;
}
/**
 * @return Returns the systemName.
 */
public static String getSystemName() {
	return SystemName;
}
/**
 * @param systemName The systemName to set.
 */
public static void setSystemName(String systemName) {
	SystemName = systemName;
}
/**
 * @param islog4 The islog4 to set.
 */

/**
 * @return Returns the islog4.
 */
public static boolean isIslog4() {
	return islog4;
}
/**
 * @param islog4 The islog4 to set.
 */
public static void setIslog4(boolean islog4) {
	Debug.islog4 = islog4;
}
public static String readxmlparm(String attibute) {

	FileReader fileReader = null;
	BufferedReader reader = null;
	String strvalue = "";
	//System.out.println("attibute"+attibute);
	//System.out.println("FILEREALPATH"+FILEREALPATH);
	
	try {

		fileReader = new FileReader(FILEREALPATH);
		reader = new BufferedReader(fileReader);

		String tempStr;
		String ewXml = "";
		StringBuffer sb = new StringBuffer();
		while ((tempStr = reader.readLine()) != null) {
			sb.append(tempStr);
		}
		ewXml = sb.toString();

		Element root = null;
		root = DocumentHelper.parseText(ewXml).getRootElement();

		Element bankurlElement = root.element(attibute);
		strvalue = bankurlElement.getTextTrim();
		root = null;

	} catch (Exception e) {
		e.printStackTrace();
	} finally {

		try {
			if (fileReader != null) {
				fileReader.close();
				fileReader = null;
			}
			if (reader != null) {
				reader.close();
				reader = null;
			}
		} catch (IOException exp) {
			System.out.println("cmbxml文件关闭错误：" + exp.getMessage());
			exp.printStackTrace();
		}

	}
	return strvalue;
}
}