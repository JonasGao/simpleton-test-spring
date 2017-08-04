package org.jonas.test.spring.conf.lazy

import org.jonas.test.spring.conf.other.lazy.InnerPkgConfig
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
/**
 * Created by jonas on 2017/3/29.
 */
class Tester {
    static void main(String[] args) {
        SpringApplication.run(DoTester.class, args)
    }
}

@SpringBootApplication
@ComponentScan(basePackages = ["org.jonas.test.javaconfig"], lazyInit = true)
class DoTester {
}

@Component
class Runner implements CommandLineRunner {

//    @Autowired OuterBean outerBean

//    @Autowired
//    InnerPkgService innerPkgService

    @Autowired
    InnerBean innerBean

//    @Autowired
//    SomeInterface someInterface

    @Override
    void run(String... args) throws Exception {
//        println "Outer is $outerBean"
        println "Inner is $innerBean"
//        println "interface is $someInterface"
    }
}

@Service
//@Lazy
class SomeService {
    SomeService() {
        println "new service"
    }
}

@Configuration
@Import(InnerPkgConfig)
//@Lazy
class OuterConfig {

    @Bean
//    @Lazy
    OuterBean outerBean1() {
        println "new outer bean1"
        new OuterBean()
    }

    @Bean
    OuterBean outerBean2() {
        println "new outer bean2"
        new OuterBean()
    }

    @Configuration
//    @Lazy
    class InnerConfig {

        @Bean
        InnerBean innerBean() {
            new InnerBean()
        }

        @Bean
        SomeInterface someInterface() {
            println "new interface"
            return new InnerInterfaceImpl();
        }
    }
}

class OuterBean {

}

class InnerBean {

}

interface SomeInterface {

}

class InnerInterfaceImpl implements SomeInterface {

}