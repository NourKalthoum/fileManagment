package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.QueryHints;

import java.util.Optional;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import com.example.demo.entity.File;
@Repository
public interface FileRepo extends JpaRepository<File, Long>{
    
    // @Lock(LockModeType.PESSIMISTIC_READ)
    // @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout",value = "3000" )})
    // @Override
    // default <S extends File> S save(S arg0) {
    //     return null;
    // }

    public File findById(long id);
}
