package com.htp.repository.users;

import com.htp.entity.users.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<Users, Long>, JpaRepository<Users,Long> {
}
