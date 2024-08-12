package miu.cs425.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import miu.cs425.models.Reservation;
import miu.cs425.services.IReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reservations")
public class ReservationController {

    @Autowired
    private IReservationService reservationService;

    @Operation(summary = "Add new reservation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created reservation successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    @PostMapping()
    public ResponseEntity<Reservation> createReservation(@Valid @RequestBody Reservation reservation) {
        Reservation newReservation = reservationService.saveReservation(reservation);
        return new ResponseEntity<>(newReservation, HttpStatus.CREATED);
    }

    @Operation(summary = "Retrieve all reservations")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return list all reservations"),
            @ApiResponse(responseCode = "204", description = "There is empty reservations")
    })
    @GetMapping()
    public ResponseEntity<List<Reservation>> getAllReservations() {
        List<Reservation> reservations = reservationService.getAllReservations();
        if(reservations.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    @Operation(summary = "Delete reservation by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deleted reservation successfully"),
            @ApiResponse(responseCode = "404", description = "Not found reservation"),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservationById(@PathVariable Long id) {
        try {
            reservationService.deleteReservationById(id);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Get reservation by user Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get reservation information")
    })
    @GetMapping("/user/{id}")
    public ResponseEntity<List<Reservation>> getReservationsByUserId(@PathVariable Long id) {
        List<Reservation> reservationsByUserId = reservationService.findReservationByUserId(id);
        return new ResponseEntity<>(reservationsByUserId, HttpStatus.OK);
    }
}
