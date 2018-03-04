/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oracle.rnd.test.apichallenge.service;

import com.oracle.rnd.test.apichallenge.model.Data;

/**
 *
 * @author anandakumaran
 */
public interface HashService {

    public int createNewKVWithHash(String key, String value);

    public Data getData(String key);


}
