package ru.sidorov.mcq.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ru.sidorov.mcq.model.User;
@Repository
public interface UserRepo extends CrudRepository<User, Long>{
    User findByUsername(String username);
}
