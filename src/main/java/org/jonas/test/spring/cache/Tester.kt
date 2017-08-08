package org.jonas.test.spring.cache

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan("org.jonas.test.spring.cache")
@EnableCaching
class CachingTestApplication: CommandLineRunner {

    @Autowired(required = false)
    var cacheManager: CacheManager? = null

    override fun run(vararg args: String?) {
        println(cacheManager)
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(CachingTestApplication::class.java)
}