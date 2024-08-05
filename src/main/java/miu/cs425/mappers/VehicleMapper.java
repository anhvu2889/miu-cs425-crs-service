package miu.cs425.mappers;

import miu.cs425.dtos.UserDto;
import miu.cs425.dtos.VehicleDto;
import miu.cs425.models.User;
import miu.cs425.models.Vehicle;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class VehicleMapper {
    @Autowired
    private ModelMapper modelMapper;

    public VehicleDto toVehicleDto(Vehicle vehicle) {
        return modelMapper.map(vehicle, VehicleDto.class);
    }

    public List<VehicleDto> toVehicleDtos(List<Vehicle> vehicles) {
        List<VehicleDto> vehicleDtos = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            vehicleDtos.add(toVehicleDto(vehicle));
        }
        return vehicleDtos;
    }
}
