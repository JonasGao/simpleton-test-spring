package org.jonas.test.spring.cache.json;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.stereotype.Service;

@SpringBootApplication
@ComponentScan("org.jonas.test.spring.cache.json")
@EnableCaching
public class Tester1 implements CommandLineRunner {

    private CachingService cachingService;

    @Autowired
    public Tester1(CachingService cachingService) {
        this.cachingService = cachingService;
    }

    public static void main(String[] args) {
        SpringApplication.run(Tester1.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        A a = cachingService.getModel("Hello", "World");
        System.out.println(a);
    }
}

@Configuration
class RedisTemplateConfiguration {
    @Bean
    public RedisTemplate<Object, Object> redisTemplate(JedisConnectionFactory jedisConnectionFactory) {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory);
        redisTemplate.setDefaultSerializer(new GenericJackson2JsonRedisSerializer(new ObjectMapper()));
        return redisTemplate;
    }
}

@Service
class CachingService {

    @Cacheable(value = "test-cache", key = "#fa + #fb")
    public A getModel(String fa, String fb) {
        System.out.println("没走缓存");
        return new B() {{
            setFa(fa);
            setFb(fb);
        }};
    }
}

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
class A {
    private String fa;

    public String getFa() {
        return fa;
    }

    public void setFa(String fa) {
        this.fa = fa;
    }

    @Override
    public String toString() {
        return fa;
    }
}

class B extends A {
    private String fb;

    public String getFb() {
        return fb;
    }

    public void setFb(String fb) {
        this.fb = fb;
    }

    @Override
    public String toString() {
        return getFa() + ", " + getFb();
    }
}