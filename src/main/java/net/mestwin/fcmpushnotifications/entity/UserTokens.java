package net.mestwin.fcmpushnotifications.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Document(value = "UserTokens")
public class UserTokens {


    @Id
    private String userId;
    private String FCMTokens;

}
