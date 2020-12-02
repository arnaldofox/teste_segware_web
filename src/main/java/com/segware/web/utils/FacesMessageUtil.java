package com.segware.web.utils;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Arnaldo
 */
public class FacesMessageUtil {

    public static void info(String sumario) {
        montarMensagem(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_INFO, sumario);
    }

    public static void warn(String sumario) {
        montarMensagem(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_WARN, sumario);
    }

    public static void error(String sumario) {
        montarMensagem(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_ERROR, sumario);
    }

    public static void fatal(String sumario) {
        montarMensagem(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_FATAL, sumario);
    }

    public static void montarMensagem(FacesContext facesContext, FacesMessage.Severity severity, String sumario) {
        facesContext.addMessage(null, new FacesMessage(severity, sumario, null));
    }

}
