package org.jonas.test.spring.profiles

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.stereotype.Component

/**
 * Created by jonas on 2017/3/29.
 */
@SpringBootApplication
@ComponentScan("org.jonas.test.spring.profiles")
class ProfilesTest {
    static void main(String[] args) {
        SpringApplication.run(ProfilesTest.class, args)
    }
}

@Component
class ProfilesComponent implements CommandLineRunner {

    @Value('${spring.profiles.active}')
    private String profiles

    @Value('${test.message}')
    private String message

    @Value('${test.message2}')
    private String message2

    @Value('${cache.local:true}')
    private boolean local

    @Override
    void run(String... args) throws Exception {
        println profiles
        println message
        println message2
        println local
    }
}