package com.client.musicOn.service;

import com.client.musicOn.dao.RoleRepository;
import com.client.musicOn.dao.UserRepository;
import com.client.musicOn.entity.Roles;
import com.client.musicOn.entity.Users;
import com.client.musicOn.model.UnregisteredUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    @Transactional
    public Users findUser(String userName) {
        Optional<Users> users = userRepository.findById(userName);
        Users user = new Users();
        if(users.isPresent())
            user= users.get();

        return user;
    }


    @Override
    @Transactional
    public void saveUser(UnregisteredUser newUser) {
            Users user = new Users();
            user.setUsername(newUser.getUserName());
            user.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
            user.setEmail(newUser.getEmail());
            Roles role = roleRepository.getOne(1);
        List<Roles> roles = new ArrayList<>();
        roles.add(role);
        user.setUser_roles(roles);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public List<Users> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        Users user = findUser(username);
        if(user == null){
            throw new UsernameNotFoundException("User does not exist");
        }
        
        return new User(user.getUsername(),user.getPassword(),mapRolesToAuthorities(user.getUser_roles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Roles> role){
            return role.stream().map(rol -> new SimpleGrantedAuthority(rol.getRole_name())).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteUser(String username) {
            Users user = findUser(username);
            if(user.getUsername()!=null){
                user.setUser_roles(null);
                userRepository.save(user);
                userRepository.delete(user);
            }
    }

    @Override
    @Transactional
    public void grantAdminAccess(String username) {
        Users user = findUser(username);
        Roles role = roleRepository.getOne(12);
        List<Roles> roles= user.getUser_roles();
        if(!roles.contains(role)){
            roles.add(role);
            user.setUser_roles(roles);
            userRepository.save(user);
        }
    }
}
