## Java. Spring Boot. Braintree payments processing
This repository is an example of how to use Braintree for 
payment processing with `braintree-java`

### Prerequisites
* Java 8+
* Maven ^3.6.0
* Braintree account on [Braintree Dashboard](https://sandbox.braintreegateway.com/login)

### Configuration
To use this code sample you need to change the following configuration properties in **__application.properties__** file according to your Braintree credentials:

* braintree.merchantId
* braintree.publicKey
* braintree.privateKey

### Install and run
* From IDEA: run the **__BraintreeApplication.class__**
* From CLI: run command `mvn spring-boot:run` 

### Resources
* [Braintree Documentation](https://developers.braintreepayments.com/guides/transactions/java)

### Community
* Please send us your suggestions on how we make this code even more useful for the development community or contribute to this repo!
* Check out our [blog](https://oril.co/blog) with more articles!
