package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.ERole;
import com.example.demo.entity.File;
import com.example.demo.entity.Group;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.FileRepo;
import com.example.demo.service.FileService;
import com.example.demo.service.GroupService;
import com.example.demo.service.UserService;

import static org.springframework.http.ResponseEntity.status;


@RestController
@RequestMapping("/api/auth")
public class GroupController {

    @Autowired
    GroupService groupService;
    @Autowired
    FileService fileService;
    @Autowired
    FileRepo fileRepo;
    @Autowired
    public CacheManager cacheManager;
    @Autowired
    UserService userService;

    @PostMapping(path = "/createGroup")
    public Object createGroup(@RequestParam(name = "name") String name,@RequestParam(name = "user_id") Long user_id) {
        try {
            return groupService.createGroup(name,user_id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping(path = "/deleteGroup")
    public boolean deleteGroup(@RequestParam(name = "id") Long id) {
        return groupService.deleteGroup(id);
    }

    @GetMapping(path = "/showGroups")
    public ResponseEntity<List<Group>> showGroups() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        String username = userDetails.getUsername();
        User user = userService.getUserByUsername(username);
        Role admin = new Role(2, ERole.ROLE_ADMIN);
        if (user.getRoles().contains(admin))
            return status(HttpStatus.OK).body(this.groupService.showGroups());
            return null;
    }

    @CachePut(value = "file")
    @PutMapping(path = "/addFileToGroup")
    public Object addFileToGroup(@RequestParam(name = "id_group") Long id_group,
            @RequestParam(name = "id_file") Long id_file) {
        try {
            File File = this.fileService.show(id_file);
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                    .getPrincipal();
            String username = userDetails.getUsername();
            if (File.getFowner().getUsername().matches(username) == false) {
                return false;
            }
            Group Group = groupService.getGroup(id_group);
            Group.getFiles().add(File);
            groupService.saveGroup(Group);
            return true;
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @CacheEvict(value = "file", key = "#id")
    @DeleteMapping(path = "/deleteFileFromGroup")
    public boolean deleteFileFromGroup(@RequestParam(name = "id_group") Long id_group,
            @RequestParam(name = "id_file") Long id_file) {
        try {
            File File = this.fileService.show(id_file);
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                    .getPrincipal();
            String username = userDetails.getUsername();
            if (File.getFowner().getUsername().matches(username) == false) {
                return false;
            }
            Group Group = groupService.getGroup(id_group);
            Group.getFiles().remove(File);
            groupService.saveGroup(Group);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @PutMapping(path = "/addUserToGroup")
    public Object addUserToGroup(@RequestParam(name = "idGroup") Long idGroup,@RequestParam(name = "idUser") Long idUser) {
        try {
            Group Group = groupService.getGroup(idGroup);
            User user = userService.getUser(idUser);
            Group.getUsers().add(user);
            groupService.saveGroup(Group);
            return true;
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    
    @GetMapping(path = "/groupsOFUserOwner")
    public ResponseEntity<List<Group>> groupsOFUserOwner(@RequestParam(name = "id") Long id) {
        return status(HttpStatus.OK).body(this.groupService.groupsOFUserOwner(id));
    }

    @Cacheable("file")
    @GetMapping(path = "/showFilesOfGroup")
    public ResponseEntity<List<File>> showFilesOfGroup(@RequestParam(name = "id") Long id) {
        return status(HttpStatus.OK).body(this.groupService.showFilesOfGroup(id));
    }

}
