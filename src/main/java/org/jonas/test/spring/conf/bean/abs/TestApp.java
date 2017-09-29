package org.jonas.test.spring.conf.bean.abs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
public class TestApp implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(TestApp.class, args);
    }

    @Autowired
    Data2 data2;

    @Override
    public void run(String... args) throws Exception {
        System.out.println(data2.getName());
    }
}

@Configuration
@EnableConfigurationProperties(Data1.class)
class Conf1 {
}

abstract class Data1 {
    abstract String getName();
}

@ConfigurationProperties
class Data2 extends Data1 {
    @Override
    String getName() {
        return "Hello Spring!";
    }
}
