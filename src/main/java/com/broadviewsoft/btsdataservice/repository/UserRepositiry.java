package com.broadviewsoft.btsdataservice.repository;

import com.broadviewsoft.btsdataservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepositiry extends JpaRepository<User, Long> {
    User findByUserName(String username);

    User findByUserNameOrPassword(String username, String password);

    List<User> findAllByUserIdIsIn(List<Long> longList);
}
