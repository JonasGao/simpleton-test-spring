package org.jonas.test.spring.conf.bean.call

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.support.ResourcePatternResolver

fun main(args: Array<String>) {
    SpringApplication.run(TestApp::class.java, *args)
}

@SpringBootApplication
class TestApp(val data1: Data1, val data2: Data2) : CommandLineRunner {
    override fun run(vararg args: String?) {
        println(data1.data.name)
        println(data2.data1 == data1)
    }
}

@Configuration
class Conf1 {
    @Bean
    fun data1(data31: Data3): Data1 {
        println("create data 1 !!")
        return Data1(data31)
    }

    @Bean
    fun data2(data32: Data3): Data2 {
        println(data32.name)
        return Data2(data1(data32))
    }
}

@Configuration
class Conf2 {
    @Bean fun data31() = Data3("One!")
    @Bean fun data32() = Data3("Two!")
}

class Data1(val data: Data3)

class Data2(val data1: Data1)

class Data3(val name: String)