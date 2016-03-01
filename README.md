Java API Client for https://onfido.com/
=====================================================

Usage
-----

The client is built and distributed with Maven
### Maven dependencies
Add the dependency to your pom:
```maven
<dependency>
    <groupId>com.youcruit</groupId>
    <artifactId>onfido-api</artifactId>
    <version>0.1</version>
</dependency>
```

or for gradle:
```gradle
    compile 'com.youcruit:onfido-api:0.1'
```

### Simple usage:

```java
OnfidoHttpClient httpClient = new OkHttpOnfidoClient();
ApplicantClient applicantClient = new ApplicantClient(httpClient);
ApplicantCreationRequest request = new ApplicantCreationRequest();

request.set..
request.set..
request.set..

ApplicantResponse applicant = applicantClient.createApplicant(request);

```

Building
--------

mvn clean install

Releasing
---------

The copy-paste way of setting up releasing is adding the following to the ```~/.m2/settings.xml```

```maven
	<servers>
		<server>
			<id>ossrh</id>
			<username>**nexus username**</username>
			<password>**nexus password**</password>
		</server>
	</servers>
	<profiles>
		<profile>
			<id>gpg</id>
			<properties>
				<gpg.passphrase>**gpg passphrase**</gpg.passphrase>
				<gpg.keyname>**gpg keyname**</gpg.keyname>
			</properties>
		</profile>
	</profiles>
```

```sh
mvn release:prepare release:perform -Pgpg
```
