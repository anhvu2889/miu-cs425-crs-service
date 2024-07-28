package miu.cs425.services.impl;

import miu.cs425.models.Car;
import miu.cs425.repositories.ICarRepository;
import miu.cs425.services.ICarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarService implements ICarService {

    @Autowired
    private ICarRepository carRepository;
    @Override
    public Car saveCar(Car car) {
        return carRepository.save(car);
    }
}
