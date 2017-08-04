package org.jonas.test.spring.conf.enable.core

import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Role

@SpringBootApplication
@ComponentScan("org.jonas.test.spring.conf.enable.core")
class EnableFlagTestApplication : CommandLineRunner {
    override fun run(vararg args: String?) {
        println("run")
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(EnableFlagTestApplication::class.java, *args)
}

@Configuration
@Role(BeanDefinition.ROLE_SUPPORT)
class Conf {
    init {
        print("配置 ")
        println(javaClass.name)
    }
}