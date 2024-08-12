package miu.cs425.repositories;

import miu.cs425.models.Reservation;
import miu.cs425.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("select r from Reservation r where r.userId = :userId")
    List<Reservation> findReservationByUserId(@Param("userId") Long userId);
}
