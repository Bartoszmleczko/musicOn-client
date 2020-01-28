package com.client.musicOn.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="roles")
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="role_id")
    private int role_id;

    @Column(name="role_name")
    private String role_name;

    @ManyToMany(mappedBy = "users_roles",fetch = FetchType.EAGER)
    private List<Users> users;

    public Roles(String role_name) {
        this.role_name = role_name;
    }

    public Roles() {
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public List<Users> getUsers() {
        return users;
    }

    public void setUsers(List<Users> users) {
        this.users = users;
    }
}
