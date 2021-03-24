package com.tcoveney.sbhibsandbox.repositories;

import org.springframework.data.repository.CrudRepository;

import com.tcoveney.sbhibsandbox.models.User;

public interface UserRepository extends CrudRepository<User, Long> {

}
