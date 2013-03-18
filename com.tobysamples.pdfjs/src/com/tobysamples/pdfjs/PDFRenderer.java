package com.tobysamples.pdfjs;

import java.io.IOException;
import com.ibm.xsp.resource.*;
import com.ibm.xsp.component.FacesPageProvider;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.Renderer;

import com.tobysamples.pdfjs.resources.ResourceProvider;

public class PDFRenderer extends Renderer {

	public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
		
		
		if (component instanceof PDF) {
		ResponseWriter rw = context.getResponseWriter();
		PDF pdfcomponent = (PDF)component;
		UIComponent root = FacesContext.getCurrentInstance().getViewRoot();
		ScriptResource resource = new ScriptResource(ResourceProvider.RESOURCE_PATH + "pdf.js", true);
        FacesPageProvider asPageProvider = root instanceof FacesPageProvider ? (FacesPageProvider) root : null;
        if( null != asPageProvider ){
            asPageProvider.addResource(resource);
        }
		
		
		rw.startElement("canvas", component);
		rw.writeAttribute("id", pdfcomponent.getCanvasId(), "id");
		rw.endElement("canvas");
		
		
		rw.startElement("script", component);
		rw.writeAttribute("type", "text/javascript", "type");
		
    	rw.write("var url = '" + pdfcomponent.getPdfURL() + "';");
    	rw.write("PDFJS.disableWorker = true;");
    	rw.write("PDFJS.getDocument(url).then(function getPdfHelloWorld(pdf) {");
        rw.write("pdf.getPage(1).then(function getPageHelloWorld(page) {");
        rw.write("var scale = 1.5;");
        rw.write("var viewport = page.getViewport(scale);");
        rw.write("var canvas = document.getElementById('" + pdfcomponent.getCanvasId() + "');");
        rw.write("var context = canvas.getContext('2d');");
        rw.write("canvas.height = viewport.height;");
        rw.write("canvas.width = viewport.width;");
        rw.write("page.render({canvasContext: context, viewport: viewport});");
        rw.write("});");	
    	rw.write("});");
		rw.endElement("script");
		
		}
		super.encodeBegin(context, component);
		
	}
	

	
	public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
		ResponseWriter rw = context.getResponseWriter();
		
		super.encodeEnd(context, component);
		
	}
	
}

