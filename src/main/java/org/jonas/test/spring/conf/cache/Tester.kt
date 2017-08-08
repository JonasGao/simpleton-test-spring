package org.jonas.test.spring.conf.cache

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import javax.annotation.PostConstruct

@SpringBootApplication
@ComponentScan("org.jonas.test.spring.conf.cache")
class ConfCachingTestApplication : CommandLineRunner {
    override fun run(vararg args: String?) {
        println("run")
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(ConfCachingTestApplication::class.java, *args)
}

class CachingManageService(private val cacheManager: CacheManager) {
    @PostConstruct
    fun init() {
        println(cacheManager)
    }
}

@Configuration
@EnableCaching
class CachingManageAutoConfiguration {
    @Bean
    fun cachingManageService(cacheManager: CacheManager): CachingManageService {
        println(cacheManager)
        return CachingManageService(cacheManager)
    }
}