package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import com.example.demo.entity.File;
@Repository
public interface FileRepo extends JpaRepository<File, Long>{
    
    // @Query(value = "SELECT f FROM File f where f.free = :f")
    // public List<File> findFilesByFree(Boolean f);

    public File findById(long id);
}
