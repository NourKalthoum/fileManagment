package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Group;
@Repository
public interface GroupRepo extends JpaRepository<Group, Long> {
    
    public Group findByName(String name);
    public Group findById(int id);
    public Group findById(long id);
    @Query(value = "SELECT g FROM Group g where g.gowner.id = :id")
    public List<Group> findGroupsByGownerId(long id);
    
}
