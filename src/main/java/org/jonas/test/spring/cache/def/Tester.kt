package org.jonas.test.spring.cache.def

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.ComponentScan
import org.springframework.stereotype.Service
import java.time.LocalDateTime


fun main(args: Array<String>) {
    SpringApplication.run(CachingTestApplication::class.java)
}

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

        cachingTestService.clear()

        val a1 = cachingTestService.get("A")
        val a2 = cachingTestService.tryGet("A")!!

        assert(a1 == a2)

        val b1 = cachingTestService.tryGet("B")
        val b2 = cachingTestService.get("B")
        val b3 = cachingTestService.tryGet("B")

        assert(b1 == null)
        assert(b3 != null)
        assert(b2 == b3)

        val c1 = cachingTestService.getData(Data("A", "B"))
        val c2 = cachingTestService.tryGetData(Data("A", "B"))
        val c3 = cachingTestService.tryGetData(Data("A", "C"))
        val c4 = cachingTestService.cantGetData(Data("A", "B"))

        assert(c2 != null)
        assert(c3 != null)
        assert(c4 == null)
        assert(c1 == c2)
        assert(c1 == c3)
    }
}

@Service
class CachingTestService {

    @CacheEvict("test1", "test2")
    fun clear() {
    }

    @Cacheable("test1")
    fun get(key: String): String {
        return LocalDateTime.now().toString() + " " + key
    }

    @Cacheable("test1")
    fun tryGet(key: String): String? {
        return null
    }

    @Cacheable("test2", key = "#key.a + '?' + #key.b")
    fun getData(key: Data): String {
        return LocalDateTime.now().toString() + " " + key
    }

    @Cacheable("test2", key = "#key.a + '?' + #key.b")
    fun tryGetData(key: Data): String? {
        return null
    }

    @Cacheable("test2", key = "#key.a + '!' + #key.b")
    fun cantGetData(key: Data): String? {
        return null
    }
}

data class Data(val a: String, val b: String)