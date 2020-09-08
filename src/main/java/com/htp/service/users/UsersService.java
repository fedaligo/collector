package com.htp.service.users;

import com.htp.entity.users.Users;
import com.htp.repository.users.UsersRepository;
import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UsersService {
    private final UsersRepository usersRepository;

    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }
}
