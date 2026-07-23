package com.example.mock.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginResponse {

    @Size(min = 5, max = 20)
    @NotNull
    private String login;
    @NotNull
    @Size(min = 5)
    private String password;
    private String date;

    public LoginResponse() {
    }

    public LoginResponse(String login, String password, String date) {
        this.login = login;
        this.password = password;
        this.date = date;
    }

/*    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

 */
}
