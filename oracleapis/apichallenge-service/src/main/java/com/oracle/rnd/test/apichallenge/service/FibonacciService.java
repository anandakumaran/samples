/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oracle.rnd.test.apichallenge.service;

/**
 *
 * @author anandakumaran
 */
public interface FibonacciService {

    /**
     *
     * @param size
     * @param sort
     * @param useJava
     * @return
     */
    public Integer[] getFibonacciSeries(int size, boolean sort, boolean useJava);

}
