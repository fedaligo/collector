package com.htp.controller.convert.users;

import com.htp.controller.convert.EntityConverter;
import com.htp.controller.requests.users.UserCreateRequest;
import com.htp.entity.users.Users;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class UserRequestConverter<S, T> extends EntityConverter<S, T> {

    protected Users doConvert(Users user, UserCreateRequest request) {
        user.setMail(request.getMail());
        return user;
    }
}
