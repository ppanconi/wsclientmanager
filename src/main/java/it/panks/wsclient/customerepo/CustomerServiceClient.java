/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.panks.wsclient.customerepo;

import it.panks.customerepo.model.ClientProspet;
import it.panks.customerepo.service.FetchClientProspect;
import it.panks.customerepo.service.FetchClientProspectResponse;
import it.panks.opclientmanager.core.OperationDispatcher;
import it.panks.opclientmanager.core.OperationDispatcherManager;

/**
 *
 * @author paolo.panconi
 */
public class CustomerServiceClient {

    public ClientProspet loadClient(Long id) {

        FetchClientProspect req = new FetchClientProspect();
        req.setId(id);
        
        OperationDispatcher dipatcher = OperationDispatcherManager.getInstance().provideDispatcher("fetchClientProspect");
        FetchClientProspectResponse c = dipatcher.invoke(req, FetchClientProspectResponse.class);
        
        if (c != null) {
            return c.getReturn();
        } else {
            return null;
        }

    }
}
