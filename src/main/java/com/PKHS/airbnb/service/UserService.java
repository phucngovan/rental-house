package com.PKHS.airbnb.service;

import com.PKHS.airbnb.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    Page<User> fillAll(Pageable pageable);

    void save(User user);

    void remove(Integer id);

    User findById(Integer id);

    Page<User> findByName(String name, Pageable pageable);

    Iterable<User> findAll();

    User findByUsername(String name);
}
