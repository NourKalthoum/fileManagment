package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Reservation;

@Repository
public interface ReservationRepo extends JpaRepository<Reservation, Long>{
    @Query(value = "SELECT r FROM Reservation r where r.type = booked and r.user = ?1")
    public Reservation findByUser(long id);

    public List<Reservation> findAllByOrderByIdDesc();
}
