package com.garrykevin.payvice;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class PayViceApp {
  public static void main(String[] args) {
    SpringApplication.run(PayViceApp.class, args);
  }
}
