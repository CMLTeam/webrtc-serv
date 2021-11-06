package com.cmlteam.webrtc_serv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.PostConstruct;

@SpringBootApplication
@ComponentScan(basePackages = "com.cmlteam")
public class WebrtcServApplication {

  public static void main(String[] args) {
    SpringApplication.run(WebrtcServApplication.class, args);
  }

  @PostConstruct
  public void doAtStartup() {
    // Insert here post-start action if need be
  }
}
