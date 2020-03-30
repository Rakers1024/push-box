package com.aldrich_lin.pushbox.repository;

import com.aldrich_lin.pushbox.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface UserRepository extends PagingAndSortingRepository<User,Integer> {

//    List<User> findByName(String name);
}
