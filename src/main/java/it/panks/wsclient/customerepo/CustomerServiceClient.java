/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.panks.wsclient.customerepo;

import it.panks.customerepo.model.ClientProspet;
import it.panks.customerepo.service.FetchClientProspect;
import it.panks.customerepo.service.FetchClientProspectResponse;
import it.panks.opclientmanager.client.OperationClientBase;
import it.panks.opclientmanager.core.OperationAsyncHandler;
import it.panks.opclientmanager.core.OperationDispatcher;
import it.panks.opclientmanager.core.OperationDispatcherManager;
import it.panks.opclientmanager.core.OperationResponse;
import java.util.concurrent.Future;

/**
 *
 * @author paolo.panconi
 */
public class CustomerServiceClient extends OperationClientBase {

    public ClientProspet loadClient(Long id) {

        FetchClientProspect req = new FetchClientProspect();
        req.setId(id);
        
//        OperationDispatcher dipatcher = OperationDispatcherManager.getInstance().provideDispatcher("fetchClientProspect");
//        FetchClientProspectResponse c = dipatcher.invoke(req, FetchClientProspectResponse.class);
            FetchClientProspectResponse c = invokeOperation("fetchClientProspect", req, FetchClientProspectResponse.class);
        
        if (c != null) {
            return c.getReturn();
        } else {
            return null;
        }
    }
    
    public Future<?> loadClientAsynch(Long id, OperationAsyncHandler<FetchClientProspectResponse> handler) {
        
        FetchClientProspect req = new FetchClientProspect();
        req.setId(id);
        return invokeOperationAsync("fetchClientProspect", req, handler);
        
    }
    
}
