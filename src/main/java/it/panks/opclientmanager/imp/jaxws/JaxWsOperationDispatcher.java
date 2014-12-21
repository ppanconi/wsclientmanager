/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.panks.opclientmanager.imp.jaxws;

import it.panks.opclientmanager.core.BaseOperationDispatcher;
import it.panks.opclientmanager.core.OperationDispatcher;
import javax.xml.ws.Dispatch;

/**
 *
 * @author paolo.panconi
 */
public class JaxWsOperationDispatcher extends BaseOperationDispatcher implements OperationDispatcher {

    private Dispatch dispatch;

    public JaxWsOperationDispatcher(Dispatch dispatch, String operationId) {
        super(operationId);
        this.dispatch = dispatch;
    }
    
    @Override
    public <T, R> R invoke(T req, Class<R> responseType) {
        Object resp = dispatch.invoke(req);
        return responseType.cast(resp);
    }
    
}
