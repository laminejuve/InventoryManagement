package com.lamine.InventoryManagement.service.strategy;

import com.flickr4java.flickr.FlickrException;

import java.io.InputStream;

public interface Strategy<T> {

    T save (Integer id ,InputStream photo , String titre) throws FlickrException;
}
