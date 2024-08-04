package miu.cs425.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VehicleDto {
        private Long vehicleId;

        private String make;

        private String model;

        private String year;

        private String licensePlateNumber;

        private Double rentalPrice;

        private String availableStatus;
}
