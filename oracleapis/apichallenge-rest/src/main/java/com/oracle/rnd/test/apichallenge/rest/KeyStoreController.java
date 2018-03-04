/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oracle.rnd.test.apichallenge.rest;

import com.oracle.rnd.test.apichallenge.model.Data;
import com.oracle.rnd.test.apichallenge.service.HashService;
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
public class KeyStoreController {

    private final Logger L = LoggerFactory.getLogger(KeyStoreController.class);

    @Autowired
    private HashService hashService;

    @RequestMapping(value = "/api/createhash/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity createNewKeyValue(@RequestParam(value = "key", required = true) String key, @RequestParam(value = "value", required = true) String value, HttpServletRequest request, HttpServletResponse response) {

        L.info("Start creating hash for new key:" + key + " and value:" + value);
        int v = hashService.createNewKVWithHash(key, value);

        //TODO handle if any exception from service layer
        return new ResponseEntity(HttpStatus.CREATED);

    }

    @RequestMapping(value = "/api/getdata/{key}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE + "; charset=UTF-8")
    public @ResponseBody
    ResponseEntity getDataByKey(@PathVariable("key") String key, HttpServletRequest request, HttpServletResponse response) {

        L.info("Start processing getDataByKey:" + key);

        if (key == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        } else {
            Data data = hashService.getData(key);
            return new ResponseEntity(data, HttpStatus.OK);
        }
    }

}
