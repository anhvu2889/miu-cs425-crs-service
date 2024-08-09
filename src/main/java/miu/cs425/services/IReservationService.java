package miu.cs425.services;

import miu.cs425.models.Reservation;

import java.util.List;

public interface IReservationService {

    List<Reservation> getAllReservations();

    Reservation saveReservation(Reservation reservation);

    void deleteReservationById(Long id);
}
