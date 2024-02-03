package com.agil.admin.payload.response;

import java.util.List;

public class LoginResponse {
    private String token;
    private String message;
    private Boolean ok;
    private String tokenType = "Bearer";
    private Long expiresIn;
    private List<String> roles;
    private String email;
    


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LoginResponse(String token, String message, Boolean ok, String tokenType, Long expiresIn, List<String> roles, String email) {
        this.token = token;
        this.message = message;
        this.ok = ok;
        this.tokenType = tokenType;
        this.expiresIn=expiresIn;
        this.roles=roles;
        this.email=email;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public Long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getOk() {
        return ok;
    }

    public void setOk(Boolean ok) {
        this.ok = ok;
    }
}
