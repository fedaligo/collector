package com.htp.controller.requests.users;

import com.htp.entity.users.UsersRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateRequest {

    @NotNull
    @NotEmpty
    @Size(min = 1, max = 20)
    private String userName;

    @NotNull
    @NotEmpty
    @Size(min = 1, max = 50)
    private String password;

    @NotNull
    @Email
    @Size(min = 1, max = 50)
    private String mail;

    private String usersRole;
}
