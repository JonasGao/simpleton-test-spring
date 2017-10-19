package org.jonas.test.spring.xml.bean.noid

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ImportResource

@SpringBootApplication
@ImportResource("classpath:test-no-id-context.xml")
class XmlBeanNoIdTest : CommandLineRunner {

    @Autowired
    private lateinit var messageService: MessageService

    @Autowired
    private lateinit var groovy: Message

    override fun run(vararg args: String?) {
        messageService.showMessages()
        println("--")
        println(groovy.get())
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(XmlBeanNoIdTest::class.java, *args)
}