/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.panks.opclientmanager.core;

import java.util.Map;
import java.util.concurrent.Future;

/**
 *
 * @author panks
 */
public interface OperationResponse<R> extends Future<R>  {
    /** Gets the contained response context.
     *
     * @return The contained response context. May be <code>null</code> if a
     * response is not yet available.
     *
    **/
    Map<String,Object> getContext();
}
