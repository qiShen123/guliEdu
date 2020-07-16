package com.itheima.canal;

import com.itheima.canal.client.CanalClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CanalApplication implements CommandLineRunner {
    @Autowired
    private CanalClient canalClient;
    public static void main(String[] args) {
        SpringApplication.run(CanalApplication.class,args);
    }

    @Override
    public void run(String... args) throws Exception {
        canalClient.run();
    }
}
