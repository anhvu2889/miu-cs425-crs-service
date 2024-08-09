package miu.cs425.repositories;

import miu.cs425.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IReservationRepository extends JpaRepository<Reservation, Long> {

}
