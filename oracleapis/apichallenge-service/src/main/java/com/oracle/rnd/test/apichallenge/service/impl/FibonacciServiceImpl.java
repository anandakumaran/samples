/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oracle.rnd.test.apichallenge.service.impl;

import com.oracle.rnd.test.apichallenge.service.FibonacciService;
import com.oracle.rnd.test.apichallenge.service.util.FibonacciUtil;
import org.springframework.stereotype.Service;

/**
 *
 * @author anandakumaran
 */
@Service("fibonacciService")
public class FibonacciServiceImpl implements FibonacciService {

    @Override
    public Integer[] getFibonacciSeries(int size, boolean sort, boolean useJava) {
        Integer[] fibonacciArray = new Integer[size];

        if (useJava) {
            fibonacciArray = FibonacciUtil.getFibonacciSeries(size, sort);
        } else {
            String response = invokeShellScript();
        }

        return fibonacciArray;
    }

    private String invokeShellScript() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
