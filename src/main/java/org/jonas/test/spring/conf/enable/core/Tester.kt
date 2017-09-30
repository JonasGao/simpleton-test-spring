package org.jonas.test.spring.conf.enable.core

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.context.annotation.*
import org.springframework.core.annotation.Order

@SpringBootApplication
@ComponentScan("org.jonas.test.spring.conf.enable.core")
class EnableFlagTestApplication : CommandLineRunner {

    @Autowired
    lateinit var data1: Data1

    @Autowired
    lateinit var data2: Data2

    override fun run(vararg args: String?) {
        println("run")
        println(data1.name)
        println(data2.name)
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(EnableFlagTestApplication::class.java, *args)
}

@Configuration
@Role(BeanDefinition.ROLE_SUPPORT)
@Order(1)
class Conf1 {
    init {
        println("配置 1, " + javaClass.name)
    }

    @Bean
    @ConditionalOnMissingBean(Data1::class)
    fun data11() = Data1("Hello World 1")
}

@Configuration
@Order(2)
class Conf2 {
    @Bean
    @ConditionalOnMissingBean(Data1::class)
    fun data12() = Data1("Hello World 2")

    @Bean
    @ConditionalOnMissingBean(Data1::class)
    fun data13() = Data1("Hello World 3")

    @Bean
    fun data21() = Data2("Hi Spring 2")
}

@Configuration
@ConditionalOnMissingBean(Data2::class)
@Import(Conf4::class)
class Conf3

class Conf4 {
    @Bean
    fun data21() = Data2("Hi Spring 1")
}

class Data1(val name: String) {
    init {
        println("init ing '$name'")
    }
}

class Data2(val name: String) {
    init {
        println("init ing '$name'")
    }
}
