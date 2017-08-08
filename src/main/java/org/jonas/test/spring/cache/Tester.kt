package org.jonas.test.spring.cache

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.Cacheable
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.ComponentScan
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@SpringBootApplication
@ComponentScan("org.jonas.test.spring.cache")
@EnableCaching
class CachingTestApplication

@Service
class TestRunnerService(private val cachingTestService: CachingTestService) : CommandLineRunner {

    @Autowired(required = false)
    var cacheManager: CacheManager? = null

    override fun run(vararg args: String?) {
        println(cacheManager)

        println(cachingTestService.get("A"))
        println(cachingTestService.tryGet("A"))
        println(cachingTestService.tryGet("B"))
        println(cachingTestService.get("B"))
        println(cachingTestService.tryGet("B"))
    }
}

@Service
class CachingTestService {

    @Cacheable("test1")
    fun get(key: String): String {
        return LocalDateTime.now().toString() + " " + key
    }

    @Cacheable("test1")
    fun tryGet(key: String): String? {
        return null
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(CachingTestApplication::class.java)
}