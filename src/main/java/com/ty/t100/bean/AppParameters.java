package com.ty.t100.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component("appParameters")
@ConfigurationProperties(prefix = "app-parameters")
public class AppParameters {
    private String appid;

    private String addSecret;

}
