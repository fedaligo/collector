package com.htp.repository.roles;

import com.htp.entity.roles.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface RolesRepository extends CrudRepository<Roles, Long>, JpaRepository<Roles,Long> {
}
