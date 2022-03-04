package com.siteminder

import com.siteminder.mailservice.MailServiceApplication
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest(classes = [MailServiceApplication])
class MailServiceApplicationSpec extends Specification {

    @Test
    void contextLoads() throws Exception {

    }
}
