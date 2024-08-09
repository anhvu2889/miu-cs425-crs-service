package miu.cs425.services.impl;

import jakarta.persistence.EntityNotFoundException;
import miu.cs425.models.Reservation;
import miu.cs425.models.Vehicle;
import miu.cs425.repositories.IReservationRepository;
import miu.cs425.services.IReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService implements IReservationService {

    @Autowired
    private IReservationRepository reservationRepository;

    @Override
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation saveReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public void deleteReservationById(Long id) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Reservation not found with id: " + id));
        reservationRepository.delete(reservation);
    }

}
