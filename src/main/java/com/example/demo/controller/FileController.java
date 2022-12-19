package com.example.demo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.entity.ERole;
import com.example.demo.entity.File;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.service.FileService;
import com.example.demo.service.StorageService;
import com.example.demo.service.UserService;

import static org.springframework.http.ResponseEntity.status;


import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class FileController {

    @Autowired
    private FileService fileService;
    @Autowired
    private StorageService storageService;
    @Autowired
    UserService userService;
    
    @PostMapping(path ="/createFile")
	public ResponseEntity<?> createFile(@RequestParam("txt") MultipartFile txt, File file){ 
          try {
            String fileName = this.storageService.save(txt);
            file.setName(fileName);
            file.setFree(true);
            this.fileService.createFile(file);
            return ResponseEntity.accepted().body(file);
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
	}

   @DeleteMapping(path = "/deleteFile")
   public boolean deleteFile(@RequestParam(name = "id") Long id) throws InterruptedException
   {
       return this.fileService.deleteFile(id);
   }

  
    @GetMapping(path ="/showAll")
    public ResponseEntity<List<File>> showAll(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                       .getPrincipal();
       String username = userDetails.getUsername();
       User user = userService.getUserByUsername(username);
       Role admin = new Role(2, ERole.ROLE_ADMIN);
       if(user.getRoles().contains(admin))
        return status(HttpStatus.OK).body(this.fileService.all());
        return null;
    }

    @GetMapping(path ="/show")
    public File show(@RequestParam(name = "id") Long id){
        return this.fileService.show(id);
    }
    
}
