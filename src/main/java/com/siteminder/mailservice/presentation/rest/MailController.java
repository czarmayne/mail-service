package com.siteminder.mailservice.presentation.rest;

import com.siteminder.mailservice.core.annotation.LogExecutionTime;
import com.siteminder.mailservice.core.handler.MailHandler;
import com.siteminder.mailservice.core.dto.MailRequest;
import com.siteminder.mailservice.presentation.dto.ResponseWrapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;
import java.util.UUID;

import static com.siteminder.mailservice.core.constant.Const.LOGREF;

/**
 * For FUTURE IMPLEMENTATION
 * TODO: 1. Can select which mail provider can be selected
 * TODO: 2. Add name for each email (supported by both existing mail providers)
 * TODO: 3. Add scheduled mail
 */
@Slf4j
@RestController
@RequestMapping("/mail")
public class MailController {

    private  final MailHandler mailHandler;

    @Autowired
    public MailController(MailHandler mailHandler) {
        this.mailHandler = mailHandler;
    }

    /**
     * Default will used both the mail providers;
     * <br/>
     * TODO: Auth and Trace / Correlation Id
     * @param request
     * @return
     */
    @LogExecutionTime
    @PostMapping(path = "/default"
            , consumes = MediaType.APPLICATION_JSON_VALUE
            , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseWrapper<Object>> sendMail(@Valid @RequestBody MailRequest request) {
        addTraceability();
        log.info("[START] Send Mail");
        log.debug("Request {}", request);
        var response = mailHandler.sendMail(request);
        log.debug("Response {}", response);
        log.info("[END] Send Mail");
        MDC.clear();
        return ResponseEntity.ok(getResponseWrapper(response));
    }

    //TODO: from this section, move to a different class
    private ResponseWrapper<Object> getResponseWrapper(String response) {
        return ResponseWrapper.builder()
                .statusCode(HttpStatus.OK.toString())
                .response(response)
                .result(true)
                .build();
    }

    //FIXME: should be implemented via filters / interceptors
    private void addTraceability() {
        MDC.put(LOGREF, UUID.randomUUID().toString());
        final Map<String, String> copyOfContextMap = MDC.getCopyOfContextMap();
        log.trace(LOGREF + " {}", copyOfContextMap);
    }
}
