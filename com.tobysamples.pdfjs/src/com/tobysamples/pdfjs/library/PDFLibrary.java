package com.tobysamples.pdfjs.library;

import com.ibm.xsp.library.AbstractXspLibrary;
import com.tobysamples.pdfjs.Activator;



public class PDFLibrary extends AbstractXspLibrary{
	
	public PDFLibrary() {

	}
	
	public String getLibraryId() {
		return PDFLibrary.class.getName();
	}

	public String getPluginId() {
		return Activator.PLUGIN_ID;
	}
	@Override
	public String[] getXspConfigFiles() {
		String[] files = new String[] { "META-INF/pdf.xsp-config"};

		return files;
	}
	public String[] getFacesConfigFiles() {
		String[] files = new String[] { "META-INF/pdf-faces-config.xml" };
		return files;
	}
	
	
}
