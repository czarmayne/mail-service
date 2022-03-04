package com.siteminder.presentation.rest

import com.siteminder.mailservice.MailServiceApplication
import com.siteminder.mailservice.core.dto.MailRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.*
import spock.lang.Specification

import javax.validation.ConstraintViolation
import javax.validation.Validation
import javax.validation.Validator
import javax.validation.ValidatorFactory

import static com.siteminder.factory.MailRequestFactory.defaultMailRequest
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT

@SpringBootTest(classes = [MailServiceApplication], webEnvironment = RANDOM_PORT)
class MailControllerSpec extends Specification {

    @Autowired
    TestRestTemplate restTemplate

    private Validator validator

    def setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory()
        validator = factory.getValidator()
    }

    def 'Send message successfully'() {
        given: "A header request"
        def headers = new HttpHeaders()
        headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE)
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE)

        and: "A request body"
        def request = defaultMailRequest()

        and: "An HTTP Entity"
        def httpEntity = new HttpEntity<>(request, headers)

        when: "A client request has been triggered"
        def response = restTemplate.exchange('/mail/default', HttpMethod.POST, httpEntity, String.class)

        then: "Verify response"
        response.getStatusCode() == HttpStatus.OK

    }

    def 'Send message unsuccessfully due to SERVER_ERROR'() {
        given: "A header request"
        def headers = new HttpHeaders()
        headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE)
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE)

        and: "A request body"
        def request = null

        and: "An HTTP Entity"
        def httpEntity = new HttpEntity<>(request, headers)

        when: "A client request has been triggered"
        def response = restTemplate.exchange('/mail/default', HttpMethod.POST, httpEntity, String.class)

        then: "Verify response"
        response.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR

    }

    def "Verify MailRequest Field Validation"() {
        given: "An invalid Mail request"
        def inRequest = defaultMailRequest(recipients: null)

        when: "validated"
        Set<ConstraintViolation<MailRequest>> violations = validator.validate(inRequest)

        then: "field validation has been violated"
        false != violations.isEmpty()
    }
}
