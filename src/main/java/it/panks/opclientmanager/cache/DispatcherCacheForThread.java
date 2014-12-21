/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.panks.opclientmanager.cache;

import it.panks.opclientmanager.core.OperationDispatcher;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.configuration.CacheEntryListenerConfiguration;
import javax.cache.configuration.Configuration;
import javax.cache.integration.CompletionListener;
import javax.cache.processor.EntryProcessor;
import javax.cache.processor.EntryProcessorException;
import javax.cache.processor.EntryProcessorResult;

/**
 *
 * @author paolo.panconi
 */
public class DispatcherCacheForThread implements Cache<String, OperationDispatcher> {
    
    private Map<Thread, Map<String, OperationDispatcher>> _cache = new WeakHashMap<>();
    
    private Map<String, OperationDispatcher> getLc() {
     
        /**
         * optimistic concurrent access to local _cache Map
         * based on the following specification:
         * 
         * "Writes to and reads of references are always atomic, 
         * regardless of whether they are implemented as 32 or 64 bit values."
         * 
         * See https://docs.oracle.com/javase/specs/jls/se5.0/html/memory.html#17.7
         * 
         */
        try {
            if (_cache == null) {
                this._cache = new WeakHashMap<>();
            }

            Thread curThread = Thread.currentThread();
            
            if (_cache.get(curThread) == null) {
                this._cache.put(curThread, new WeakHashMap<String, OperationDispatcher>());
            }

            Map<String, OperationDispatcher> map = _cache.get(curThread);
            //safe for to concurent clear 
            if (map == null) {
                map = new HashMap<>();
            }

            return map;

        } catch (NullPointerException e) {
            /**
             * if we a concurrent disposeCache we return a temp Map
             */
            return new HashMap<>();
        }
    }
    
    private void disposeCache() {
        this._cache = null;
    }

    public DispatcherCacheForThread() {
    }
    
    //////////////////////

    @Override
    public OperationDispatcher get(String key) {
        
        if (key == null) throw new NullPointerException("null key");
        if (isClosed()) throw new IllegalStateException("Cache actually closed");
        
        return getLc().get(key);
        
    }

    @Override
    public Map<String, OperationDispatcher> getAll(Set<? extends String> keys) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean containsKey(String key) {
        
        if (key == null) throw new NullPointerException("null key");
        if (isClosed()) throw new IllegalStateException("Cache actually closed");
        
        return getLc().containsKey(key);
    }

    @Override
    public void loadAll(Set<? extends String> keys, boolean replaceExistingValues, CompletionListener completionListener) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void put(String key, OperationDispatcher value) {
        if (key == null) throw new NullPointerException("null key");
        if (value == null) throw new NullPointerException("null value");
        
        if (isClosed()) throw new IllegalStateException("Cache actually closed");
        
        getLc().put(key, value);
    }

    @Override
    public OperationDispatcher getAndPut(String key, OperationDispatcher value) {
        
        if (key == null) throw new NullPointerException("null key");
        if (value == null) throw new NullPointerException("null value");
        if (isClosed()) throw new IllegalStateException("Cache actually closed");
        
        OperationDispatcher v = getLc().get(key);
        getLc().put(key, value);
        
        return v;
    }

    @Override
    public void putAll(Map<? extends String, ? extends OperationDispatcher> map) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean putIfAbsent(String key, OperationDispatcher value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean remove(String key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean remove(String key, OperationDispatcher oldValue) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OperationDispatcher getAndRemove(String key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean replace(String key, OperationDispatcher oldValue, OperationDispatcher newValue) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean replace(String key, OperationDispatcher value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OperationDispatcher getAndReplace(String key, OperationDispatcher value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeAll(Set<? extends String> keys) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void clear() {
        disposeCache();
    }

    @Override
    public <C extends Configuration<String, OperationDispatcher>> C getConfiguration(Class<C> clazz) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <T> T invoke(String key, EntryProcessor<String, OperationDispatcher, T> entryProcessor, Object... arguments) throws EntryProcessorException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <T> Map<String, EntryProcessorResult<T>> invokeAll(Set<? extends String> keys, EntryProcessor<String, OperationDispatcher, T> entryProcessor, Object... arguments) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CacheManager getCacheManager() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void close() {
        clear();
    }

    @Override
    public boolean isClosed() {
        return false;
    }

    @Override
    public <T> T unwrap(Class<T> clazz) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void registerCacheEntryListener(CacheEntryListenerConfiguration<String, OperationDispatcher> cacheEntryListenerConfiguration) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deregisterCacheEntryListener(CacheEntryListenerConfiguration<String, OperationDispatcher> cacheEntryListenerConfiguration) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Iterator<Entry<String, OperationDispatcher>> iterator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
