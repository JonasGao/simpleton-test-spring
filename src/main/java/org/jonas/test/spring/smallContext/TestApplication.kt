package org.jonas.test.spring.smallContext

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

/**
 * Created by jonas on 2017/4/20.
 */
fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java)
    println("Application already close")
}

@SpringBootApplication
open class Application : CommandLineRunner {
    override fun run(vararg args: String?) {
        println("Hello world!")
    }
}
