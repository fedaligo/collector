package com.htp.controller.convert.users;

import com.htp.controller.requests.users.UserCreateRequest;
import com.htp.entity.users.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserCreateRequestConverter extends UserRequestConverter<UserCreateRequest, Users> {

    @Override
    public Users convert(UserCreateRequest request) {
        Users user = new Users();
        user.setUsername(request.getUserName());
        user.setPassword(request.getPassword());
        user.setRole("ROLE_USER");
        return doConvert(user, request);
    }
}
