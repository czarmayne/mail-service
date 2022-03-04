## API Reference

#### Send Mail to SendGrid and MailGun Providers

```http
  POST /mail/default
```
| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `recipients`      | `string[]` | **Required**. Recipients of the Mail|
| `cc`      | `string[]` |  Recipients of the Mail, but carbon copy |
| `bcc`      | `string[]` | Recipients of the Mail, but blind carbon copy |
| `from`      | `string` | **Required**. Sender |
| `subject`      | `string` | **Required**. Title of the Mail |
| `message`      | `string` | **Required**. Body of the Mail |

#### Reference for 3rd Party Vendor:
- [MailGun](https://documentation.mailgun.com/en/latest/api-sending.html#sending)
- [SendGrid](https://docs.sendgrid.com/api-reference/mail-send/mail-send?code-sample=code-v3-mail-send&code-language=Java&code-sdk-version=8.x#body)

`REQUEST`
```
{
    "recipients" : ["XXX@gmail.com","XXX@outlook.com"],
    "cc" : [""],
    "bcc": [""],
    "from":"XXX@gmail.com",
    "message":"testXXX",
    "subject":"subjXXX"
}    
```

`RESPONSE: 200 | SUCCESS`
```
{
    "result": true,
    "response": "Hello, from handler Success Mailgun",
    "statusCode": "200 OK"
}

{
    "result": true,
    "response": "Hello, from handler Success Sendgrid",
    "statusCode": "200 OK"
}
```

`RESPONSE: 400 | BAD REQUEST`

```
{
    "debugMessage": "Validation failed for argument [0] in public org.springframework.http.ResponseEntity<com.siteminder.mailservice.presentation.dto.ResponseWrapper<java.lang.Object>> com.siteminder.mailservice.presentation.rest.MailController.sendMail(com.siteminder.mailservice.core.dto.MailRequest) with 4 errors: [Field error in object 'mailRequest' on field 'message': rejected value []; codes [NotBlank.mailRequest.message,NotBlank.message,NotBlank.java.lang.String,NotBlank]; arguments [org.springframework.context.support.DefaultMessageSourceResolvable: codes [mailRequest.message,message]; arguments []; default message [message]]; default message [Must contain message body]] [Field error in object 'mailRequest' on field 'recipients[].email': rejected value []; codes [NotBlank.mailRequest.recipients[].email,NotBlank.mailRequest.recipients.email,NotBlank.recipients[].email,NotBlank.recipients.email,NotBlank.email,NotBlank]; arguments [org.springframework.context.support.DefaultMessageSourceResolvable: codes [mailRequest.recipients[].email,recipients[].email]; arguments []; default message [recipients[].email]]; default message [Email should not be blank]] [Field error in object 'mailRequest' on field 'subject': rejected value []; codes [NotBlank.mailRequest.subject,NotBlank.subject,NotBlank.java.lang.String,NotBlank]; arguments [org.springframework.context.support.DefaultMessageSourceResolvable: codes [mailRequest.subject,subject]; arguments []; default message [subject]]; default message [Must contain subject]] [Field error in object 'mailRequest' on field 'from': rejected value [null]; codes [NotBlank.mailRequest.from,NotBlank.from,NotBlank.java.lang.String,NotBlank]; arguments [org.springframework.context.support.DefaultMessageSourceResolvable: codes [mailRequest.from,from]; arguments []; default message [from]]; default message [Parameter \"from\" should not be empty]] ",
    "errorFields": [
        {
            "field": "message",
            "message": "Must contain message body"
        },
        {
            "field": "recipients[].email",
            "message": "Email should not be blank"
        },
        {
            "field": "subject",
            "message": "Must contain subject"
        },
        {
            "field": "from",
            "message": "Parameter \"from\" should not be empty"
        }
    ]
}
```

`RESPONSE: 500 | INTERNAL SERVER ERROR`

```
{
    "result": false,
    "response": "JSON parse error: Unexpected character ('}' (code 125)): was expecting double-quote to start field name; nested exception is com.fasterxml.jackson.core.JsonParseException: Unexpected character ('}' (code 125)): was expecting double-quote to start field name\n at [Source: (org.springframework.util.StreamUtils$NonClosingInputStream); line: 3, column: 2]",
    "statusCode": "001500"
}
// Gateway Error
{
    "result": false,
    "response": "Error Sendgrid unavailable",
    "statusCode": "001500"
}
```

## Adding Custom Error

In ```CustomError.java```, follow this format in adding a new field in the enum class:
```
DEFAULT_SERVER_ERROR("001500", "","Generic Server Error.")
```
+ ```100404``` - code

  First 3 digits will be defined by the team. The first digit denotes the feature the error code belongs to as follows:
    ```
        000 - Generic (Default)
        100 - Not related to any infrastructure connection; internal service handling
        200 - MailGun
        300 - SendGrid
    ```
  Last 3 digits is the HTTP status code, in this case, **404 - NOT FOUND**

  Guide on when to add gateway code
    ```
        UNKNOWN - Short Hand Message
    ```
+ ```Generic Server Error``` - message