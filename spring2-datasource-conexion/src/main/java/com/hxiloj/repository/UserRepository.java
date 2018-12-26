package com.hxiloj.repository;

import org.springframework.data.repository.CrudRepository;

import com.hxiloj.model.User;

public interface UserRepository extends CrudRepository<User, Long>{

}
