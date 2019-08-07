package com.harry.jcodemo;

import com.harry.jcodemo.jco.CustomJcoService;
import com.harry.jcodemo.jco.CustomJcoServiceImpl;
import com.harry.jcodemo.jco.JcoProperties;
import com.harry.jcodemo.jco.R;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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

}
