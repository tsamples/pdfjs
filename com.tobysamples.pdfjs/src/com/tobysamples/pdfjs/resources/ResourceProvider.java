package com.tobysamples.pdfjs.resources;

import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import com.tobysamples.pdfjs.Activator;
import javax.servlet.http.HttpServletRequest;

import org.osgi.framework.Bundle;

import com.ibm.xsp.webapp.FacesResourceServlet;
import com.ibm.xsp.webapp.resources.BundleResourceProvider;
import com.ibm.xsp.webapp.resources.URLResourceProvider;


public class ResourceProvider extends BundleResourceProvider {
	
    public static final String BUNDLE_RES_PATH = "/resources/pdfjs/";  // $NON-NLS-1$
    
    public static final String PDFJS_PREFIX = ".pdfjs";  // $NON-NLS-1$
    
    // Resource Path
    public static final String RESOURCE_PATH =    FacesResourceServlet.RESOURCE_PREFIX  // "/.ibmxspres/" 
                                                + ResourceProvider.PDFJS_PREFIX      // ".pdfjs" 
                                                + "/";
    
    public ResourceProvider() {
        super(Activator.instance.getBundle(),PDFJS_PREFIX);
    }

    @Override
    protected URL getResourceURL(HttpServletRequest request, String name) {
        String path = BUNDLE_RES_PATH+name;
        return ResourceProvider.getResourceURL(getBundle(), path);
    }
    
	public static URL getResourceURL(Bundle bundle, String path) {
		int fileNameIndex = path.lastIndexOf('/');
		String fileName = path.substring(fileNameIndex + 1);
		path = path.substring(0, fileNameIndex + 1);
		// see http://www.osgi.org/javadoc/r4v42/org/osgi/framework/Bundle.html
		// #findEntries%28java.lang.String,%20java.lang.String,%20boolean%29
		Enumeration<?> urls = bundle.findEntries(path, fileName, false/* recursive */);
		if (null != urls && urls.hasMoreElements()) {
			URL url = (URL) urls.nextElement();
			if (null != url) {
				return url;
			}
		}
		return null; // no match, 404 not found.
	}
	
}


