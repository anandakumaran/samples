/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oracle.rnd.test.apichallenge.rest;

import com.oracle.rnd.test.apichallenge.service.FibonacciService;
import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author anandakumaran
 */
@RestController
public class MathAPIController {

    private final Logger L = LoggerFactory.getLogger(MathAPIController.class);

    @Autowired
    private FibonacciService fibonacciservice;

    @RequestMapping(value = "/api/getfibonacci/{size}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE + "; charset=UTF-8")
    public @ResponseBody
    ResponseEntity getFibonacciBySize(@PathVariable("size") int fibonacciSize, @RequestParam(value = "sort", required = false) String sort, @RequestParam(value = "usejava", required = false) String useJava, HttpServletRequest request, HttpServletResponse response) {

        L.info("Start processing getFibonacci series for size:" + fibonacciSize + ". Sort requested:" + ("Y".equals(sort) ? "YES" : "NO"));
        if (fibonacciSize <= 0) {
            return new ResponseEntity("Please pass a valid value for the size", HttpStatus.BAD_REQUEST);
        }

        Integer[] fibonacciSeries = processFibonacciRequest(fibonacciSize, "Y".equals(sort), ("Y".equals(useJava) || useJava == null));
        L.info("Fibonacci Series (unsorted): " + Arrays.toString(fibonacciSeries));

        return new ResponseEntity(fibonacciSeries, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/getfibonacci/sorted", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE + "; charset=UTF-8")
    public @ResponseBody
    ResponseEntity getSortedFibonacci(HttpServletRequest request, HttpServletResponse response) {

        L.info("Start processing getSortedFibonacci of size:50. Sort: YES");

        Integer[] sortedFibonacciSeries = processFibonacciRequest(50, true, true);

        L.info("sortedFibonacciSeries: " + Arrays.toString(sortedFibonacciSeries));
        return new ResponseEntity(sortedFibonacciSeries, HttpStatus.OK);
    }

    private Integer[] processFibonacciRequest(int fibonacciSize, boolean sort, boolean useJava) {

        return fibonacciservice.getFibonacciSeries(fibonacciSize, sort, useJava);

    }

}
