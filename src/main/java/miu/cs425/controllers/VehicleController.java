package miu.cs425.controllers;

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
    @PostMapping("/add")
    public ResponseEntity<Car> addCar (@RequestBody Car car){
        Car newCar = carService.saveCar(car);
        return new ResponseEntity<>(newCar, HttpStatus.CREATED);
    }
}
