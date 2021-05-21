package com.avinty.hr.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.avinty.hr.model.User;

@Repository
public interface UserRepo extends CrudRepository<User, Long> {

}
