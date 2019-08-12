package com.harry.jcodemo;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.harry.jcodemo.jco.CustomJcoService;
import com.harry.jcodemo.jco.JcoProperties;
import com.harry.jcodemo.jco.R;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JcoDemoApplicationTests {

    @Autowired
    private CustomJcoService jcoService;
    @Autowired
    private JcoProperties jcoProperties;

    Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 连通性调试
     */
    @Test
    public void pingCallsTest() {
        R r = jcoService.pingCalls(jcoProperties.getDestName());

        logger.info(r.toString());
    }

    @Test
    public void callRFCTest() {
        Map<String, Object> input = Maps.newHashMap();

        ArrayList<Object> list = Lists.newArrayList();
        HashMap<Object, Object> ZSCONTENT = Maps.newHashMap();
        ZSCONTENT.put("BUKRS","1000");
        ZSCONTENT.put("PARTNER","111");
        ZSCONTENT.put("LIST_ID","O-FAS20190806000000002");
        ZSCONTENT.put("FEE_TYPE","01");
        ZSCONTENT.put("FEE_STATUS","1");
        ZSCONTENT.put("AMOUNT","15.58");
        ZSCONTENT.put("WAERS","HDK");
        ZSCONTENT.put("PAY_MODE","99");
        ZSCONTENT.put("DUEDA","20190624");
        ZSCONTENT.put("BUDAT","20190624");
        ZSCONTENT.put("FUND_CODE","1000101");
        ZSCONTENT.put("OPTXT","测试");

        list.add(ZSCONTENT);
        input.put("IT_CONTENT",list);

        R rfc = jcoService.callRFC("Z_TEST", input);

        logger.info(rfc.toString());
    }

}
