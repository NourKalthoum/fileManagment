package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.File;
import com.example.demo.entity.Group;
import com.example.demo.entity.User;
import com.example.demo.repository.GroupRepo;
import com.example.demo.repository.UserRepo;

@Service
public class GroupService {

    @Autowired
    GroupRepo groupRepo;

    @Autowired
    UserRepo userRepo;

    public Group createGroup(Group group) {
        Group Group = groupRepo.findByName(group.getName());
        if (Group != null)
            return null;
        return groupRepo.save(group);
    }


   public boolean deleteGroup(Long id) {
       Group Group = groupRepo.getById(id);
       List<File> files = Group.getFiles();
       for (File File : files) {
           if (File.isFree() == false)
               return false;
       }
       groupRepo.delete(groupRepo.findById(id).orElseThrow());
       return groupRepo.findById(id).isEmpty();

   }

    public List<Group> showGroups() {
        return this.groupRepo.findAll();
    }

    public Group getGroup(long id) {
		
		return groupRepo.findById(id);
	}

    public Group saveGroup(Group Group){

        return groupRepo.save(Group);
    }

    // public List<Group> groupsOFUserOwner(Long id){

    //     Optional<User> user = userRepo.findById(id);
        
    //     List<Group> groups = user.get().getGroup();
    //     return groups;
    // }

    public List<Group> groupsOFUserOwner(Long id){
        
        return groupRepo.getGroup(id);
    }

    public List<File> showFilesOfGroup(Long id){

        Optional<Group> group = groupRepo.findById(id);
        List<File> files = group.get().getFiles();
        return files;
    }

    public List<File> showFreeFilesOfGroup(Long id){
        
        Optional<Group> group = groupRepo.findById(id);
        List<File> files = group.get().getFiles();
        for (File file : files) {
                if (file.isFree())
                   files.add(file);
                }
        return files;
    }
}
