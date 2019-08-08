package com.harry.jcodemo.jco;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhouhong
 * @version 1.0
 * @title: CustomJcoController
 * @description: TODO
 * @date 2019/8/7 11:01
 */
@RestController
@RequestMapping("/jco")
public class CustomJcoController {

    @Autowired
    private CustomJcoService customJcoService;

    @Autowired
    private JcoProperties properties;

    @GetMapping("/ping")
    public R pingCalls() {

        R r = customJcoService.pingCalls( properties.getDestName() );

        return r;

    }

    /**
     * 传入功能名称和Map类型参数
     * @param
     * @return
     */
    @PostMapping("/executeFunction")
    public R executeJcoFunction( @RequestBody Map<String,Object> param ){
        Map<String, Object> paramMap = new HashMap<String, Object>();
        String functionName = (String) param.get( "functionName" );
        paramMap = (Map<String, Object>) param.get( "paramMap" );
        if ( functionName==null ){
            return new R( R.FAIL,"param未包含functionName参数" );
        }
        return customJcoService.callRFC( functionName, paramMap );
    }
}
