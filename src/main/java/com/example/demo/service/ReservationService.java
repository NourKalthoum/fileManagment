package com.example.demo.service;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.File;
import com.example.demo.entity.Reservation;
import com.example.demo.entity.User;
import com.example.demo.repository.ReservationRepo;

@Service
public class ReservationService {
   
    @Autowired
    ReservationRepo reservationRepo;
    @Autowired
    FileService fileService;
    @Autowired
    UserService userService;

   public Reservation createReservationBooked (Long idFile,Long idUser){
    File fileEntite = fileService.getFile(idFile);
    User userEntity = userService.getUser(idUser);
    Reservation reservationEntity = new Reservation();
    reservationEntity.setFile(fileEntite);
    reservationEntity.setUser(userEntity);
    reservationEntity.setType("booked");
    Date currentDate = Calendar.getInstance().getTime();
    reservationEntity.setCreateAt(currentDate);
    return reservationEntity;
   }

   public Reservation createReservationReleased (Long idFile,Long idUser){
    File fileEntite = fileService.getFile(idFile);
    User userEntity = userService.getUser(idUser);
    Reservation reservationEntity = new Reservation();
    reservationEntity.setFile(fileEntite);
    reservationEntity.setUser(userEntity);
    reservationEntity.setType("Released");
    reservationEntity.setCreateAt(null);
    return reservationEntity;
   }

   public Reservation getReservation(Long id){
    
    return reservationRepo.findByUser(id);

   }
}
