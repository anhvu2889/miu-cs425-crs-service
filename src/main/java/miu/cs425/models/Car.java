package miu.cs425.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long carId;

    @Column(name = "make")
    private String make;

    @Column(name = "model")
    private String model;

    @Column(name = "year")
    private Integer year;

    @Column(name = "license_plate_number", unique = true, nullable = false)
    private String licensePlateNumber;

    @Column(name = "rental_price")
    private Double rentalPrice;

    @Column(name = "is_available")
    private Boolean isAvailable;
}
