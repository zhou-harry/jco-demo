package com.harry.jcodemo.jco;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author zhouhong
 * @version 1.0
 * @title: JcoProperties
 * @description: TODO
 * @date 2019/8/7 10:25
 */
@Getter
@Setter
@Data
@ConfigurationProperties(prefix = "sap.jco.provider")
public class JcoProperties {

    private String destName;
    private String ashost;
    private String client;
    private String sysnr;
    private String lang;
    private String user;
    private String passwd;
    private String poolCapacity;
    //同时可创建的最大活动连接数，0表示无限制，默认为JCO_POOL_CAPACITY的值
    private String peakLimit;
    //开启R/3需要该行配置
    private String saprouter;
}
