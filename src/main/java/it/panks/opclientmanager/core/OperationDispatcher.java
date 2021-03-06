/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.panks.opclientmanager.core;

import java.util.concurrent.Future;

/**
 *
 * @author paolo.panconi
 */
public interface OperationDispatcher {

    String getOperationId();
    
    <T, R> R invoke(T req, Class<R> responseType);
    
    <T, R> Future<?> invokeAsync(T req, OperationAsyncHandler<R> handler);
}
