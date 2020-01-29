package com.client.musicOn.service;


import com.client.musicOn.entity.Users;
import com.client.musicOn.model.UnregisteredUser;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
public interface UserService extends UserDetailsService {

    public Users findUser(String userName);

    public void saveUser(UnregisteredUser newUser);

    public List<Users> findAllUsers();

    public void deleteUser(String username);

    public void grantAdminAccess(String username);

}
