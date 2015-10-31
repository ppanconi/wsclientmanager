/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.panks.opclientmanager.client;

import it.panks.customerepo.service.FetchClientProspectResponse;
import it.panks.opclientmanager.core.OperationAsyncHandler;
import it.panks.opclientmanager.core.OperationDispatcher;
import it.panks.opclientmanager.core.OperationDispatcherManager;
import java.util.concurrent.Future;

/**
 *
 * @author paolo.panconi
 */
public abstract class OperationClientBase {
    
    protected <T, R> R invokeOperation(String operationId, T req, Class<R> responseType) {
        
        OperationDispatcher dipatcher = OperationDispatcherManager.getInstance().provideDispatcher(operationId);
        R c = dipatcher.invoke(req, responseType);
        return c;
    }
    
    protected <T, R> Future<?> invokeOperationAsync(String operationId, T req, OperationAsyncHandler<R> handler) {
        
        OperationDispatcher dipatcher = OperationDispatcherManager.getInstance().provideDispatcher(operationId, true);
        Future f = dipatcher.invokeAsync(req, handler);
        return f;
    }
    
}
