/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.panks.opclientmanager.core;


/**
 *
 * @author panks
 */
public interface OperationAsyncHandler<R> {
    /** Called when the response to an asynchronous operation is available.
     *
     * @param res The response to the operation invocation.
     *
    **/
    void handleResponse(OperationResponse<R> res);
}
