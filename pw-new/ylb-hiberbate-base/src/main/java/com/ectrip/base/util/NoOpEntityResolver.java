package com.ectrip.base.util;

import org.xml.sax.*;
import java.io.*;

public class NoOpEntityResolver implements EntityResolver {
	  public InputSource resolveEntity(String publicId, String systemId) {
	    return new InputSource(new StringBufferInputStream(""));
	  }
}
