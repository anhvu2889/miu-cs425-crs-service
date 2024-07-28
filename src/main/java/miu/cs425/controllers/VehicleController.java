package miu.cs425.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import miu.cs425.models.Car;
import miu.cs425.services.ICarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/vehicles")
public class VehicleController {

    @Autowired
    private ICarService carService;

    @Operation(summary = "Add new car")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created car successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    @PostMapping()
    public ResponseEntity<Car> addCar (@RequestBody Car car){
        Car newCar = carService.saveCar(car);
        return new ResponseEntity<>(newCar, HttpStatus.CREATED);
    }
}
