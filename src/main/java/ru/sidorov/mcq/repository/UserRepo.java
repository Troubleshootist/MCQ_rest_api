package ru.sidorov.mcq.repository;

import org.springframework.data.repository.CrudRepository;

import ru.sidorov.mcq.model.User;

public interface UserRepo extends CrudRepository<User, Long>{
    User findByUsername(String username);
}
