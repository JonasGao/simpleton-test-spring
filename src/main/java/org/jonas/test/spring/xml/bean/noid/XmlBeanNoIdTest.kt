package org.jonas.test.spring.xml.bean.noid

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.ImportResource

@SpringBootApplication
@ImportResource("classpath:test-no-id-context.xml")
class XmlBeanNoIdTest : CommandLineRunner {

    @Autowired
    private lateinit var messageService: MessageService

    @Autowired(required = false)
    private var groovy: Message? = null

    @Autowired
    private lateinit var context: ApplicationContext

    @Autowired
    private lateinit var newMessage: NewMessage

    override fun run(vararg args: String?) {
        messageService.showMessages()
        println("--")
        println(groovy?.get())
        println("--")
        for (mutableEntry in context.getBeansOfType(Message::class.java)) {
            println("%-20s | %s".format(mutableEntry.key, mutableEntry.value.get()))
        }
        println("--")
        println(newMessage.messageHelper)
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(XmlBeanNoIdTest::class.java, *args)
}