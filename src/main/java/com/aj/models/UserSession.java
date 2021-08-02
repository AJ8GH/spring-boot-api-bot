package com.aj.models;

import com.aj.BetfairApiBot1Application;
import lombok.*;

import javax.persistence.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserSession {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String status;
    private String token;
    private String appKey;
    private String product;
    private String error;
    private String props = "application.properties";

    public void loadAppKey() throws IOException {
        Properties properties = new Properties();
        InputStream inputStream = BetfairApiBot1Application.class
                .getClassLoader().getResourceAsStream(props);
        properties.load(inputStream);

        setAppKey(properties.getProperty("APP_KEY"));
    }
}
