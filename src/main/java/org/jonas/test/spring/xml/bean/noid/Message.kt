package org.jonas.test.spring.xml.bean.noid

import org.springframework.beans.factory.annotation.Autowired

class Message(private val value: String) {
    @Autowired
    private lateinit var messageHelper: MessageHelper

    fun get() = messageHelper.getPrefix() + value
}