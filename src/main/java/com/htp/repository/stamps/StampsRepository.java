package com.htp.repository.stamps;

import com.htp.entity.stamps.Stamps;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface StampsRepository extends CrudRepository<Stamps, Long>, JpaRepository<Stamps,Long> {
}
