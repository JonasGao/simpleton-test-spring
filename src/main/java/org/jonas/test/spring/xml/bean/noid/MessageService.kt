package org.jonas.test.spring.xml.bean.noid

class MessageService {

    lateinit var messages: Map<String, Message>

    fun showMessages() {
        for (message in messages) {
            println("%-20s | %s".format(message.key, message.value.get()))
        }
    }
}
