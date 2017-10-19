package org.jonas.test.spring.xml.bean.noid

import org.springframework.stereotype.Service

@Service("messageHelper")
class MessageHelper {

    fun getPrefix() = "Hello, "
}