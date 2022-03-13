package es.uniovi.miw.ws.usersws.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @NotNull
    private String username;
    @NotBlank
    @NotNull
    private String password;
    @NotBlank
    @NotNull
    private String address;
    @NotNull
    private boolean isAnonymous;
    public User(){

    }

    public User(String username, String password, String address, boolean isAnonymous) {
        this.username = username;
        this.password = password;
        this.address = address;
        this.isAnonymous = isAnonymous;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isAnonymous() {
        return isAnonymous;
    }

    public void setAnonymous(boolean anonymous) {
        isAnonymous = anonymous;
    }
}
