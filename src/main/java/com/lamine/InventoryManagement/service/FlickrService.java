package com.lamine.InventoryManagement.service;

import com.flickr4java.flickr.FlickrException;

import java.awt.im.InputContext;
import java.io.InputStream;

public interface FlickrService {
    String savePhoto (InputStream photo , String title) throws FlickrException;
}
