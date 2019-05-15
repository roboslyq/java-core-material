/**
 * Copyright (C), 2015-2019
 * FileName: DtoHelper
 * Author:   luo.yongqian
 * Date:     2019/5/6 15:39
 * Description: SimpleDto 容器
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2019/5/6 15:39      1.0.0               创建
 */
package com.roboslyq.java9stream;

import com.roboslyq.java9stream.dto.SimpleDto;
import com.roboslyq.java9stream.dto.SimpleDto1;

import java.util.ArrayList;
import java.util.List;

/**
 *  此类作为一个简单工具类，主要用来初始化简单对象集合。
 * 〈SimpleDto 容器〉
 * @author luo.yongqian
 * @create 2019/5/6
 * @since 1.0.0
 */
public class DtoHelper {
    /**
     * @param len 返回list的长度
     * @return
     */
    public static List<SimpleDto> getSimpleDtos(int len){
        ArrayList<SimpleDto>  simpleDtoArrayList = new ArrayList<>(len);
        for(int i = 0; i < len ; i++){
            SimpleDto simpleDto = new SimpleDto();
            simpleDto.setId(i);
            simpleDto.setName("roboslyq-" + i);
            simpleDtoArrayList.add(simpleDto);
        }
        return simpleDtoArrayList;
    }
    /**
     * @param len 返回list的长度
     * @return
     */
    public static List<SimpleDto1> getSimpleDto1s(int len){
        ArrayList<SimpleDto1>  simpleDtoArrayList = new ArrayList<>(len);
        for(int i = 0; i < len ; i++){
            SimpleDto1 simpleDto1 = new SimpleDto1();
            simpleDto1.setId(i);
            simpleDto1.setName("roboslyq-" + i);
            simpleDtoArrayList.add(simpleDto1);
        }
        return simpleDtoArrayList;
    }
}