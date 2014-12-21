/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.panks.opclientmanager.core;

/**
 *
 * @author paolo.panconi
 */
public abstract class BaseOperationDispatcher implements OperationDispatcher {
    
    private String operationId;

    public BaseOperationDispatcher(String operationId) {
        this.operationId = operationId;
    }
    
    @Override
    public String getOperationId() {
        return operationId;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }
    
}
