/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.panks.opclientmanager.imp.jaxws;

import it.panks.opclientmanager.core.OperationResponse;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.xml.ws.Response;

/**
 *
 * @author panks
 */
class JaxWsOperationResponse<R> implements OperationResponse<R> {

    private Response<R> jaxWsResponse;

    public JaxWsOperationResponse(Response<R> jaxWsResponse) {
        this.jaxWsResponse = jaxWsResponse;
    }
    
    @Override
    public Map<String, Object> getContext() {
        return jaxWsResponse.getContext();
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return jaxWsResponse.cancel(mayInterruptIfRunning);
    }

    @Override
    public boolean isCancelled() {
        return jaxWsResponse.isCancelled();
    }

    @Override
    public boolean isDone() {
        return jaxWsResponse.isDone();
    }

    @Override
    public R get() throws InterruptedException, ExecutionException {
        return jaxWsResponse.get();
    }

    @Override
    public R get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return jaxWsResponse.get(timeout, unit);
    }
    
}
