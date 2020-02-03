package net.mestwin.fcmpushnotifications;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableFeignClients
public class FcmPushNotificationsApplication {

	public static void main(String[] args) {
		SpringApplication.run(FcmPushNotificationsApplication.class, args);
	}

	//todo: remvoe dead code

	public void run(final String... args) throws Exception {
		System.out.println("PROJECT STARTED");
		final RestTemplate restTemplate = new RestTemplate();
		final String fooResourceUrl = "https://hooks.slack.com/services/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";
		final HttpEntity entity = new HttpEntity<String>("{ \"text\" : \"Hello from Spring Boot!\" }");
		entity.getHeaders().add("Accept", MimeTypeUtils.APPLICATION_JSON_VALUE);
		entity.getHeaders().add("Content-Type", MimeTypeUtils.APPLICATION_JSON_VALUE);
		entity.getHeaders().add("Bearer", "xoxb-");
		final ResponseEntity<String> response = restTemplate.postForObject(fooResourceUrl, entity,
				ResponseEntity.class);
	}


}
