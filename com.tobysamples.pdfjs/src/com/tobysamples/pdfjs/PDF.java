package com.tobysamples.pdfjs;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;




import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;

import com.ibm.xsp.component.UIOutputEx;

public class PDF extends UIOutputEx {

	public static final String COMPONENT_FAMILY = "com.tobysamples.pdfjs.component.PDF";
	public static final String RENDERER_TYPE  = "com.tobysamples.pdfjs.component.PDFRenderer";
	private String canvasId;
	private String pdfURL;
	
	
	public String getPdfURL() {
		if (pdfURL != null) {
			return pdfURL;
		}

		ValueBinding valueBinding = getValueBinding("pdfURL");
		if (valueBinding != null) {
			return (String) valueBinding.getValue(FacesContext.getCurrentInstance());
		}
		return null;
	}


	public void setPdfURL(String pdfURL) {
		this.pdfURL = pdfURL;
	}


	public String getCanvasId() {
		
		if (canvasId != null) {
			return canvasId;
		}

		ValueBinding valueBinding = getValueBinding("canvasId");
		if (valueBinding != null) {
			return (String) valueBinding.getValue(FacesContext.getCurrentInstance());
		}
		return null;
	}


	public void setCanvasId(String canvasId) {
		this.canvasId = canvasId;
	}

	public PDF() {
		super();
		setRendererType(RENDERER_TYPE);
	}
	
	@Override
	public String getFamily() {
		return COMPONENT_FAMILY;
	}
    public Object saveState(FacesContext context) {
        if (isTransient()) {
            return null;
        }
        Object[] state = new Object[3];
        state[0] = super.saveState(context);
        state[1] = canvasId;
        state[2] = pdfURL;

        return state;
    }


    public void restoreState(FacesContext context, Object state) {
        Object[] values = (Object[])state;
        super.restoreState(context, values[0]);
        canvasId = (String)values[1];
        pdfURL= (String)values[2];

    }


}
