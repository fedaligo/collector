package com.htp.controller.users;

import com.htp.entity.users.Users;
import com.htp.service.users.UsersService;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(
        origins = {"http://localhost:4200"}
)
public class UsersController {
    private final UsersService usersService;

    @GetMapping({"/allusers"})
    public List<Users> getAllUsers() {
        return this.usersService.getAllUsers();
    }

    public UsersController(final UsersService usersService) {
        this.usersService = usersService;
    }
}
