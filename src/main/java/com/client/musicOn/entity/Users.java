package com.client.musicOn.entity;



import javax.persistence.*;
import java.util.List;


@Entity
@Table(name="users")
public class Users {

    @Id
    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;

    @Column(name="email")
    private String email;

    @Column(name = "enabled")
    private boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER,cascade = { CascadeType.ALL })
    @JoinTable(
        name = "users_roles",
        joinColumns = {@JoinColumn(name="username")},
            inverseJoinColumns = {@JoinColumn(name="role_id")}
    )
    private List<Roles> users_roles;

    public Users(String userName, String password, String email, boolean enabled) {
        this.username = userName;
        this.password = password;
        this.email = email;
        this.enabled = enabled;
    }

    public Users() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Roles> getUser_roles() {
        return users_roles;
    }

    public void setUser_roles(List<Roles> user_roles) {
        this.users_roles = user_roles;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
