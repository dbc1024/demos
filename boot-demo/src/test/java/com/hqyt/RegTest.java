package com.hqyt;

import cn.hutool.core.util.IdcardUtil;
import org.junit.Test;

/**
 * @author: CSZ 991587100@qq.com
 * @date: 2018/12/26 20:12
 */
public class RegTest {

    @Test
    public void IDTest(){

        String id = "511521199503071919";

        System.out.println(IdcardUtil.isValidCard(id));

        System.out.println(IdcardUtil.isvalidCard18(id));

        System.out.println(IdcardUtil.isvalidCard15(id));

    }
}
