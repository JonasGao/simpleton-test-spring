package org.jonas.test.spring.conf.enable.other

import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Role

@Configuration
@Role(BeanDefinition.ROLE_SUPPORT)
class OtherConf {
    init {
        print("配置 2")
        println(javaClass.name)
    }
}