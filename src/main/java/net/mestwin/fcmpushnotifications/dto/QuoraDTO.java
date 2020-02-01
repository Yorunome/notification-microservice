package net.mestwin.fcmpushnotifications.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuoraDTO {

    private String title;
    private String message;
    private List<String> userId;
    private String appId;


}
