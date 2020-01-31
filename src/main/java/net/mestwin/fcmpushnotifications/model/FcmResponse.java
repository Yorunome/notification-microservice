package net.mestwin.fcmpushnotifications.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.mestwin.fcmpushnotifications.entity.Notification;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FcmResponse {

    private List<Notification> fcmTokens;

    public List<Notification> getFcmTokens() {
        return fcmTokens;
    }

    public void setFcmTokens(List<Notification> fcmTokens) {
        this.fcmTokens = fcmTokens;
    }
}
