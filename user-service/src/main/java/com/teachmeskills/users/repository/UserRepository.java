package com.teachmeskills.users.repository;

import com.teachmeskills.users.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    @Override
    List<User> findAll();

    Optional<User> getUserByLogin(String login);
}
