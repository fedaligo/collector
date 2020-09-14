package com.htp.service.users;

import com.htp.entity.users.Users;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UsersService usersService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        System.out.println("MyUserDetailService");
        //return new User("bo", "bob",  new ArrayList<>());
        Users myUser= usersService.findByUserName(userName);
        if (myUser == null) {
            System.out.println("unknown user");
            throw new UsernameNotFoundException("Unknown user: "+userName);
        }
        UserDetails user = new User(myUser.getUsername(), myUser.getPassword(),
                AuthorityUtils.commaSeparatedStringToAuthorityList(myUser.getRole()))/*User.builder()
                .username(myUser.getUsername())
                .password(myUser.getPassword())
                .roles(myUser.getRole())
                .build()*/;
        System.out.println(user);
        return user;
    }

    /*@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            Optional<HibernateUsers> searchResult = hibernateUsersRepository.findByLogin(username);
            if (searchResult.isPresent()) {
                HibernateUsers hibernateUsers = searchResult.get();
                return new org.springframework.security.core.userdetails.User(
                        hibernateUsers.getLogin(),
                        hibernateUsers.getPassword(),
                        AuthorityUtils.commaSeparatedStringToAuthorityList(hibernateUsers.getRole().stream().
                                map(HibernateRoles::getNameOfRole).collect(Collectors.joining(",")))
                );
            }
        } catch (Exception e) {
            throw new UsernameNotFoundException("User with this login not found");
        }
    }*/
}
