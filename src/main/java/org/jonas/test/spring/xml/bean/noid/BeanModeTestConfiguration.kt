package org.jonas.test.spring.xml.bean.noid

import org.springframework.beans.factory.annotation.Autowire
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BeanModeTestConfiguration {
    @Bean(autowire = Autowire.BY_NAME)
    fun new1() = NewMessage("New Groovy!")
}