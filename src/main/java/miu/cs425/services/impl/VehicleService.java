package miu.cs425.services.impl;

import jakarta.persistence.EntityNotFoundException;
import miu.cs425.dtos.requests.DynamicFilterSortRequest;
import miu.cs425.models.User;
import miu.cs425.models.Vehicle;
import miu.cs425.repositories.IVehicleRepository;
import miu.cs425.services.IVehicleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService implements IVehicleService {

    @Autowired
    private IVehicleRepository vehicleRepository;
    @Override
    public Vehicle saveVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }
    @Override
    public List<Vehicle> getAllVehicles(){
        return vehicleRepository.findAll();
    }

    @Override
    public Page<Vehicle> getVehiclesByFilter(DynamicFilterSortRequest dynamicFilterSortRequest) {
        Result<Vehicle> result = filterAndSort(dynamicFilterSortRequest);
        Page<Vehicle> vehiclePage = vehicleRepository.findAll(result.filter(), result.pageable());
        return new PageImpl<>(vehiclePage.getContent(), vehiclePage.getPageable(), vehiclePage.getTotalElements());
    }

    @Override
    public Vehicle getVehicleById(Long id){
        return vehicleRepository.findById(id).orElse(null);
    }

    @Override
    public Vehicle updateVehicle(Vehicle vehicle){
        Vehicle newVehicle = vehicleRepository.findById(vehicle.getVehicleId()).orElseThrow(() -> new EntityNotFoundException("Vehicle not found with id: " + vehicle.getVehicleId()));

        if (StringUtils.isNotEmpty(vehicle.getMake())) {
            newVehicle.setMake(vehicle.getMake());
        }
        if (StringUtils.isNotEmpty(vehicle.getModel())) {
            newVehicle.setModel(vehicle.getModel());
        }
        if (StringUtils.isNotEmpty(vehicle.getYear().toString())) {
            newVehicle.setMake(vehicle.getMake());
        }
        if (StringUtils.isNotEmpty(vehicle.getLicensePlateNumber())) {
            newVehicle.setLicensePlateNumber(vehicle.getLicensePlateNumber());
        }
        if (StringUtils.isNotEmpty(vehicle.getRentalPrice().toString())) {
            newVehicle.setRentalPrice(vehicle.getRentalPrice());
        }
        if (StringUtils.isNotEmpty(vehicle.getAvailableStatus())) {
            newVehicle.setAvailableStatus(vehicle.getAvailableStatus());
        }

        return vehicleRepository.save(newVehicle);
    }

    @Override
    public void deleteVehicleById(Long id){
        Vehicle vehicle = vehicleRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Vehicle not found with id: " + id));
        vehicleRepository.delete(vehicle);
    }
}
