package miu.cs425.services;

import miu.cs425.dtos.requests.DynamicFilterSortRequest;
import miu.cs425.models.Vehicle;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IVehicleService extends IGenericService<Vehicle>{
    Vehicle saveVehicle(Vehicle vehicle);

    List<Vehicle> getAllVehicles();
    Page<Vehicle> getVehiclesByFilter(DynamicFilterSortRequest dynamicFilterSortRequest);
}
