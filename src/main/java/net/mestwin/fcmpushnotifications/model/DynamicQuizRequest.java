package net.mestwin.fcmpushnotifications.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DynamicQuizRequest {

    private String title;
    private String message;
    private Map <String, String> data;

}
