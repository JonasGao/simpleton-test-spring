package org.jonas.test.spring.conf.other.lazy

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Service

/**
 * Created by jonas on 2017/3/29.
 */
@Configuration
@Lazy
@ComponentScan(basePackages = "org.jonas.test.other.javaconfig")
class InnerPkgConfig {
}

@Service
class InnerPkgService {
    InnerPkgService() {
        println "new inner pkg service"
    }
}