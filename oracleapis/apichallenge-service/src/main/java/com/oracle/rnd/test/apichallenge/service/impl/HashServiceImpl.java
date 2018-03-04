/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oracle.rnd.test.apichallenge.service.impl;

import com.oracle.rnd.test.apichallenge.model.Data;
import com.oracle.rnd.test.apichallenge.service.HashService;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.oracle.rnd.test.apichallenge.mappers.KeyvalueMapper;

/**
 *
 * @author anandakumaran
 */
@Service("hashService")
public class HashServiceImpl implements HashService {

    @Autowired
    private KeyvalueMapper keyValueMapper;

    @Override
    @Transactional
    public int createNewKVWithHash(String key, String value) {

        String hash = createHash(key + value);

        Data data = new Data(key, value, hash);
        int cnt = keyValueMapper.persist(data);

        return cnt;
    }

    private String createHash(String kv) {

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] sha1hash = new byte[40];
            md.update(kv.getBytes("utf-8"), 0, kv.length());
            sha1hash = md.digest();
            return convertToHex(sha1hash);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            Logger.getLogger(HashServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private String convertToHex(byte[] data) {
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < data.length; i++) {
            int halfbyte = (data[i] >>> 4) & 0x0F;
            int two_halfs = 0;
            do {
                if ((0 <= halfbyte) && (halfbyte <= 9)) {
                    buf.append((char) ('0' + halfbyte));
                } else {
                    buf.append((char) ('a' + (halfbyte - 10)));
                }
                halfbyte = data[i] & 0x0F;
            } while (two_halfs++ < 1);
        }
        return buf.toString();
    }

    @Override
    public Data getData(String key) {
        return keyValueMapper.getData(key);
    }

}
