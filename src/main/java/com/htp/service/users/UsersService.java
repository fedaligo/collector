package com.htp.service.users;

import com.htp.entity.users.Users;
import com.htp.repository.users.UsersRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UsersService /*implements ReactiveUserDetailsService*/ {
    private final UsersRepository usersRepository;

    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    public Users register(Users users){
        users.setPassword(users.getPassword());
        users.setRole("ROLE_USER");
        return usersRepository.save(users);
    }

    public Users findByUserName(String userName){
        return usersRepository.findByUsername(userName);
    }

    public Users findById(Long id){
        return usersRepository.findById(id).orElse(null);
    }

    public void deleteUser(Long id){
        usersRepository.deleteById(id);
    }
}
