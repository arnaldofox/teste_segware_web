package com.segware.web.mb.exception;

import java.util.ArrayList;
import java.util.List;

/**
 * Exceção HTTP
 *
 * @author Arnaldo
 */
public class HttpException extends Exception {

    private static final long serialVersionUID = 1927072908234016046L;

    private Object[] parameters;
    private List<HttpException> exceptions;

    public HttpException() {
    }

    public HttpException(String mensagem) {
        super(mensagem);
    }

    public HttpException(String mensagem, Object... parameters) {
        super(mensagem);
        this.parameters = parameters;
    }

    public void add(String mensagem, Object... parametros) {
        if (exceptions == null) {
            exceptions = new ArrayList<>();
        }
        exceptions.add(new HttpException(mensagem, parametros));
    }

    public void add(HttpException ex) {
        if (exceptions == null) {
            exceptions = new ArrayList<>();
        }
        if (ex.getExceptions() == null || ex.getExceptions().isEmpty()) {
            exceptions.add(ex);
        } else {
            ex.getExceptions().forEach((ce) -> {
                exceptions.add(ce);
            });
        }
    }

    public void check() throws HttpException {
        if (this.isNotEmpty()) {
            throw this;
        }
    }

    public List<HttpException> getExceptions() {
        return exceptions;
    }

    public boolean isNotEmpty() {
        return exceptions != null && !exceptions.isEmpty();
    }

    public Object[] getParameters() {
        return parameters;
    }

}
