package net.mestwin.fcmpushnotifications;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import net.mestwin.fcmpushnotifications.service.PushNotificationServiceImpl;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.util.MimeTypeUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootApplication()
public class FcmPushNotificationsApplication {

	//private Logger logger = LoggerFactory.getLogger(PushNotificationServiceImpl.class);

	public static void main(String[] args) {
		SpringApplication.run(FcmPushNotificationsApplication.class, args);
	}

//	public void run(final String... args) throws Exception {
//		System.out.println("PROJECT STARTED");
//
//		final CloseableHttpClient client = HttpClients.createDefault();
//		final HttpPost httpPost = new HttpPost("https://hooks.slack.com/services/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");
//
//		try {
//			final StringEntity entity = new StringEntity("{ \"text\" : \"Hello from Spring Boot!\" }");
//			httpPost.setEntity(entity);
//			httpPost.setHeader("Accept", MimeTypeUtils.APPLICATION_JSON_VALUE);
//			httpPost.setHeader("Content-Type", MimeTypeUtils.APPLICATION_JSON_VALUE);
//			httpPost.setHeader("Bearer", "<YOUR_OAUTH_TOKEN>");
//
//			client.execute(httpPost);
//			client.close();
//		} catch (final Exception e) {
//
//			logger.error("Error occured in SlackService: {}", e);
//		}
//
//	}


}
