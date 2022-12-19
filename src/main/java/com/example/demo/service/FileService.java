package com.example.demo.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import com.example.demo.entity.File;
import com.example.demo.repository.FileRepo;

@Service
public class FileService {
    @Autowired
    private FileRepo fileRepo;
    @Autowired
    StorageService storageService;
    
    @Transactional
    public File createFile(File File) {
        return fileRepo.save(File);
    }

    public List<File> all() {
        return this.fileRepo.findAll();
    }

    @Transactional
   public boolean deleteFile(Long id) {
       File File = fileRepo.getById(id);
       if (File.isFree() == true) {
           try {

               storageService.delete(File.getName());
           } catch (IOException e) {
               throw new RuntimeException(e);
           }
          File file2 = fileRepo.getById(id);
           fileRepo.delete(file2);
           return true;
       }
       return false;
   }

    public File show(long id) {
		return this.fileRepo.findById(id);
	}

    public File getFile(long id) {
		
		return fileRepo.findById(id);
	}

}
