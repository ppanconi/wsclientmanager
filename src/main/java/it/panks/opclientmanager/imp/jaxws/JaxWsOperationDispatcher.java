/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.panks.opclientmanager.imp.jaxws;

import it.panks.opclientmanager.core.BaseOperationDispatcher;
import it.panks.opclientmanager.core.OperationAsyncHandler;
import it.panks.opclientmanager.core.OperationDispatcher;
import java.util.concurrent.Future;
import javax.xml.ws.AsyncHandler;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Response;

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
    
    @Override
    public <T, R> Future<?> invokeAsync(T req, final OperationAsyncHandler<R> handler) {
       return dispatch.invokeAsync(req, new AsyncHandler<R>() {

           @Override
           public void handleResponse(Response<R> res) {
               handler.handleResponse(new JaxWsOperationResponse(res));
           }
       });
    }

    
}
