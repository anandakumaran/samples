/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oracle.rnd.test.apichallenge.mappers;

import com.oracle.rnd.test.apichallenge.model.Data;
import org.apache.ibatis.annotations.Param;

/**
 *
 * @author anandakumaran
 */
public interface KeyvalueMapper {

    public int persist(@Param("data") Data data);

    public Data getData(@Param("key") String key);

}
