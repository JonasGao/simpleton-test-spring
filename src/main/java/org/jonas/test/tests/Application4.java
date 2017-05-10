package org.jonas.test.tests;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

/**
 * Created by jonas on 2017/1/13.
 */
@SpringBootApplication
@EnableCaching
public class Application4 implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Application4.class, args);
    }

    @Autowired
    Service1 service1;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("----------------4-----------------");

        String content = service1.say("world");
        System.out.println(content);
        content = service1.say("world");
        System.out.println(content);

        System.out.println("----------------4-----------------");
    }
}

@Service
class Service1 {
    @Cacheable("AA")
    public String say(String name) {
        System.out.println("Hi! Im here");
        return "Hello " + name;
    }
}
