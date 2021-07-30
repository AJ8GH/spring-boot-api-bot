package com.aj.api;

import okhttp3.HttpUrl;
import org.springframework.stereotype.Service;

@Service
public class UrlBuilder implements UrlBuilderService {

    @Override
    public HttpUrl createLoginUrl(String username, String password) {
        String usernameParam = "username=" + username;
        String passwordParam = "password=" + password;
        String query = "?" + usernameParam + "&" + passwordParam;
        return HttpUrl.parse(LOGIN_ENDPOINT + query);
    }
}
