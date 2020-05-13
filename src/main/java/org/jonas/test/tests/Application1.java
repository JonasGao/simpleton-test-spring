package org.jonas.test.tests;

//import org.jonas.test.spring.async.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * Created by jonas on 2017/1/13.
 */
public class Application1 implements CommandLineRunner {

//    private AsyncService a;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public static void main(String[] args) {
        SpringApplication.run(Application1.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("---------------------------------");
        String key1 = "spring.boot.redis.test1";
        String key2 = "spring.boot.redis.test2";
        String key3 = "spring.boot.redis.test3";
        String key4 = "spring.boot.redis.test4";

        redisTemplate.afterPropertiesSet();
        redisTemplate.delete(key1);
        redisTemplate.delete(key2);
        redisTemplate.delete(key3);
        redisTemplate.delete(key4);

        redisTemplate.opsForValue().set(key1, "20");
        redisTemplate.opsForValue().increment(key1, 10);
        redisTemplate.opsForValue().append(key1, "aaaa");

        System.out.println("---------------------------------");

        System.out.println(redisTemplate.opsForValue().get(key1));

        System.out.println("---------------------------------");
    }
}
