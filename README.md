### Overview
This project is a text subscription service that allows businesses to  send deals to those negatively affected by COVID-19.


#### Tech Stack
- SpringBoot
- Java 8
- Twilio API
- Redis Data Dependency
- Redis Server



### Prerequisites
- Twilio phone number and API tokens
- Redis Server
- Java 8
- Maven
- Ngrok

1. Ngrok: expose localhost:8080

```
ngrok http 8080
```

2. Configure Twilio Webhook (add tokens and number to SmsSender.java)

Instructions on configuring Twilio webhook with ngrok:  https://www.twilio.com/blog/2013/10/test-your-webhooks-locally-with-ngrok.html


### How to run

2. launch Redis Server 

3. In project directory:

```
mvn clean install
mvn spring-boot:run
```

Navigate to `localhost:8080`
You may also use postman



