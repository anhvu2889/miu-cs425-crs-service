package miu.cs425.services.impl;

import miu.cs425.dtos.requests.DynamicFilterSortRequest;
import miu.cs425.models.Vehicle;
import miu.cs425.repositories.IVehicleRepository;
import miu.cs425.services.IVehicleService;
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
}
