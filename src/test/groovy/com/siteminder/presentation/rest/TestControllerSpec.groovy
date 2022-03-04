package com.siteminder.presentation.rest


import com.siteminder.mailservice.MailServiceApplication
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import spock.lang.Specification

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT

@SpringBootTest(classes = [MailServiceApplication], webEnvironment = RANDOM_PORT)
class TestControllerSpec extends Specification {

    @Autowired
    TestRestTemplate restTemplate

    def 'Get hello message'() {
        given: "A header request"
        def headers = new HttpHeaders()
        headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE)
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE)

        and: "An HTTP Entity"
        def httpEntity = new HttpEntity<>(null, headers)

        when: "A client request has been triggered"
        def response = restTemplate.exchange('/hello', HttpMethod.GET, httpEntity, String.class)

        then: "Verify response"
        response.getStatusCode() == HttpStatus.OK

    }
}
