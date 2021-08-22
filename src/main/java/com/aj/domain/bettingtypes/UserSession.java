package com.aj.domain.bettingtypes;

import com.aj.BetfairApiBot1Application;
import lombok.*;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Service
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class UserSession {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String status;
    private String token;
    private String appKey;
    private String esaAppKey;
    private String product;
    private String error;
    private String props = "application.properties";
    private static UserSession currentSession;

    public static UserSession getCurrentSession() {
        return currentSession;
    }

    public void loadAppKey() throws IOException {
        Properties properties = new Properties();
        InputStream inputStream = BetfairApiBot1Application.class
                .getClassLoader().getResourceAsStream(props);
        properties.load(inputStream);
        this.appKey = properties.getProperty("APP_KEY");
        this.esaAppKey = properties.getProperty("ESA_APP_KEY");
        UserSession.setCurrentSession(this);
    }

    private static void setCurrentSession(UserSession currentSession) {
        UserSession.currentSession = currentSession;
    }

}
