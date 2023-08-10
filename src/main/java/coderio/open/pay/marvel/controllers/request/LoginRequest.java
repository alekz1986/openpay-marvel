package coderio.open.pay.marvel.controllers.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class LoginRequest {

    @NotBlank(message = "The user cannot be empty.")
    private String user;

    @NotBlank(message = "You must enter a password.")
    private String password;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
