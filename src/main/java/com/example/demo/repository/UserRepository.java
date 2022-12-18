package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    
    User findByPassword(String password);

    Boolean existsByUsername(String username);

    public User findById(long id);
    public User findById(int id);

    public List<User> findAllByOrderByIdDesc();
    
}
