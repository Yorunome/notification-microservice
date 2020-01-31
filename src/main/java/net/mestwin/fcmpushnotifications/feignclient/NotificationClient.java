package net.mestwin.fcmpushnotifications.feignclient;

import net.mestwin.fcmpushnotifications.model.FcmResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

@FeignClient(name = "notification", url = "172.16.20.32:8080/notification")
@CrossOrigin(allowedHeaders = "*", origins = "*")
public interface NotificationClient {


    @GetMapping("/getFcmByUser/{id}")
    public FcmResponse getFCMDetails(@PathVariable(value = "id") Long id);

}
