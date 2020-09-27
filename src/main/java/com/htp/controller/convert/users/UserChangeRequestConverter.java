package com.htp.controller.convert.users;

import com.htp.controller.requests.users.UserUpdateRequest;
import com.htp.entity.users.Users;
import com.htp.exceptions.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static java.util.Optional.ofNullable;

@Component
@RequiredArgsConstructor
public class UserChangeRequestConverter extends UserRequestConverter<UserUpdateRequest, Users> {

    @Override
    public Users convert(UserUpdateRequest request) {
        Users user =
                ofNullable(entityManager.find(Users.class, request.getUserId()))
                        .orElseThrow(() -> new EntityNotFoundException(Users.class, request.getUserId()));
        user.setUsername(request.getUserName());
        user.setPassword(request.getPassword());
        user.setRole(request.getUsersRole());
        return doConvert(user, request);
    }
}
