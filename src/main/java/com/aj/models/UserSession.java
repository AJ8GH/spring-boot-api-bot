package com.aj.models;

import javax.persistence.*;

@Entity
public class UserSession {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String status;
    private String token;
    private String appKey;
    private String product;
    private String error;

    public UserSession() {
    }

    public UserSession(String status, String token,
                       String appKey, String product, String error) {
        this.status = status;
        this.token = token;
        this.appKey = appKey;
        this.product = product;
        this.error = error;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "UserSession{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", token='" + token + '\'' +
                ", appKey='" + appKey + '\'' +
                ", product='" + product + '\'' +
                ", error='" + error + '\'' +
                '}';
    }
}
