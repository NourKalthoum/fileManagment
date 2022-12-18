package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.File;
import com.example.demo.entity.Group;
import com.example.demo.entity.Reservation;
import com.example.demo.entity.User;
import com.example.demo.repository.GroupRepo;
import com.example.demo.repository.UserRepo;
import com.example.demo.service.FileService;
import com.example.demo.service.GroupService;
import com.example.demo.service.ReservationService;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/api/v1/reservation")
public class ReservationController {
    
    @Autowired
    GroupService groupService;
    @Autowired
    FileService fileService;
    @Autowired
    UserService userService;
    @Autowired
    ReservationService reservationService;

    @GetMapping(path = "/showFreeFilesOfMyGroup")
    public Object showFreeFilesOfMyGroup(@RequestParam(name = "idGroup") Long idGroup,@RequestParam(name = "idUser") Long idUser){
        try {
           Group groupEntity = groupService.getGroup(idGroup);
           User userEntity = userService.getUser(idUser);
           if(groupEntity.getUsers().contains(userEntity)== false)
            return "You are not a member of this group";
            return groupService.showFreeFilesOfGroup(idGroup);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping(path = "/cheek_in")
    public Object cheek_in(@RequestParam(name = "idFile") Long idFile,@RequestParam(name = "idUser") Long idUser) {
        try {
           File fileEntite = fileService.getFile(idFile);
           fileEntite.setFree(false);
            return reservationService.createReservationBooked(idFile,idUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping(path = "/cheek_out")
    public Object cheek_out(@RequestParam(name = "idFile") Long idFile,@RequestParam(name = "idUser") Long idUser) {
        try {
           File fileEntite = fileService.getFile(idFile);
           fileEntite.setFree(true);
            return reservationService.createReservationReleased(idFile,idUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping(path = "/deleteUserFromGroup")
   public boolean deleteUserFromGroup(@RequestParam(name = "idGroup") Long idGroup,@RequestParam(name = "idUser") Long idUser)
   { 
       Reservation reservation = reservationService.getReservation(idUser);
       if(reservation == null)
       return groupService.getGroup(idGroup).getUsers().remove(idUser);
       return false;
   }


}
