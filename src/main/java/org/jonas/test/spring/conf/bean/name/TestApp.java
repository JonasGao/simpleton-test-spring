package org.jonas.test.spring.conf.bean.name;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
public class TestApp implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(TestApp.class, args);
    }

    @Autowired
    @Qualifier("A")
    private Data bean1;

    @Autowired
    @Qualifier("B")
    private Data bean2;

    @Override
    public void run(String... args) throws Exception {
        System.out.println(bean1.getName());
        System.out.println(bean2.getName());
        System.out.println(bean1 == bean2);
    }
}

@Configuration
class Conf1 {
    @Bean({"A", "B"})
    public Data bean1() {
        return new Data("Hello World!");
    }
}

class Data {
    private final String name;

    Data(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}