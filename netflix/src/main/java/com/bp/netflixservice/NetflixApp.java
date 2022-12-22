package com.bp.netflixservice;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(
        scanBasePackages = {
                "com.bp.netflixservice",
                "com.bp.util.model"
        }
)
@ComponentScan(basePackages = {
        "com.bp.netflixservice"
})
@EnableJpaRepositories(basePackages = {
        "com.bp.util.model",
        "com.bp.netflixservice"
})
@Slf4j
@AllArgsConstructor
public class NetflixApp implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(NetflixApp.class, args);
    }

    private final NetflixService netflixService;

    @Override
    public void run(String... args) throws Exception {
        netflixService.start();
    }





}
