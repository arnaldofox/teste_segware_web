package com.segware.web.mb.exception;

import java.util.ArrayList;
import java.util.List;

/**
 * Exceção de regra de negócio
 *
 * @author Arnaldo
 */
public class BusinessException extends Exception {

    private static final long serialVersionUID = 1927072908234016046L;

    private Object[] parameters;
    private List<BusinessException> exceptions;

    public BusinessException() {
    }

    public BusinessException(String mensagem) {
        super(mensagem);
    }

    public BusinessException(String mensagem, Object... parameters) {
        super(mensagem);
        this.parameters = parameters;
    }

    public void add(String mensagem, Object... parametros) {
        if (exceptions == null) {
            exceptions = new ArrayList<>();
        }
        exceptions.add(new BusinessException(mensagem, parametros));
    }

    public void add(BusinessException ex) {
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

    public void check() throws BusinessException {
        if (this.isNotEmpty()) {
            throw this;
        }
    }

    public List<BusinessException> getExceptions() {
        return exceptions;
    }

    public boolean isNotEmpty() {
        return exceptions != null && !exceptions.isEmpty();
    }

    public Object[] getParameters() {
        return parameters;
    }

}
