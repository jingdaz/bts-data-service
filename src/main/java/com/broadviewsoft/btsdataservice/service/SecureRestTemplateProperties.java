package com.broadviewsoft.btsdataservice.service;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@ConfigurationProperties("rest.ssl")
@Data
public class SecureRestTemplateProperties {
  String trustStore;
  char[] trustStorePassword;
  String protocol = "TLSv1.2";
}