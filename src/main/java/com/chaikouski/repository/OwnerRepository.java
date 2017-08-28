package com.chaikouski.repository;

import com.chaikouski.model.user.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

public interface OwnerRepository extends JpaRepository<Owner, Long> {

}
