package org.jonas.test.spring.async

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.scheduling.annotation.Async
import org.springframework.scheduling.annotation.AsyncConfigurerSupport
import org.springframework.scheduling.annotation.AsyncResult
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import org.springframework.stereotype.Component

import javax.annotation.Resource
import java.util.concurrent.Executor
import java.util.concurrent.Future
/**
 * Created by jonas on 2017/3/24.
 */
class AsyncTest {
    static void main(String[] args) {
        SpringApplication.run(TestBooter.class, args)
    }
}

@SpringBootApplication
@EnableAsync
@ComponentScan("org.jonas.test.spring.async")
class TestBooter extends AsyncConfigurerSupport implements CommandLineRunner {

    @Resource
    AsyncService asyncService

    @Override
    Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor()
        executor.setCorePoolSize(4)
        executor.setMaxPoolSize(4)
        executor.setQueueCapacity(500)
        executor.setThreadNamePrefix("GithubLookup-")
        executor.initialize()
        return executor
    }

    @Override
    void run(String... args) throws Exception {
        asyncService.async1()
        println "over 1"
        asyncService.async2()
        println "over 2"
        asyncService.async3()
        println "over 3"
        asyncService.async4()
        println "over 4"
    }
}

@Component
class AsyncService {
    @Async
    Future<Integer> async1() {
        Thread.sleep 1000
        println "Hello i m Async method 1"
        AsyncResult.forValue(1)
    }

    @Async
    void async2() {
        Thread.sleep 3000
        println "Hello i m Async method 2"
    }

    @Async
    void async3() {
        println "Hello i m Async method 3"
    }

    @Async
    void async4() {
        println "Hello i m Async method 4"
    }
}