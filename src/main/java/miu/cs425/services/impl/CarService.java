package miu.cs425.services.impl;

import miu.cs425.models.Car;
import miu.cs425.repositories.ICarRepository;
import miu.cs425.services.ICarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CarService implements ICarService {

    @Autowired
    private ICarRepository carRepository;
    @Override
    public Car saveCar(Car car) {
        return carRepository.save(car);
    }
    @Override
    public List<Car> getAllCars(){
        return carRepository.findAll();
    }
}
