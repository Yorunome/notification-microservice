package net.mestwin.fcmpushnotifications;

import net.mestwin.fcmpushnotifications.config.KafkaConfigQuora;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FcmPushNotificationsApplication {

	public static void main(String[] args) {
		SpringApplication.run(FcmPushNotificationsApplication.class, args);
	}

	//todo: remvoe dead code


}
