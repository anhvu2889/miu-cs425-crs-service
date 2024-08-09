package miu.cs425.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.persistence.EntityNotFoundException;
import miu.cs425.dtos.UserDto;
import miu.cs425.dtos.requests.DynamicFilterSortRequest;
import miu.cs425.models.Vehicle;
import miu.cs425.services.IVehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vehicles")
public class VehicleController {

    @Autowired
    private IVehicleService vehicleService;

    @Operation(summary = "Add new vehicle")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created vehicle successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    @PostMapping()
    public ResponseEntity<Vehicle> addVehicle (@RequestBody Vehicle vehicle){
        Vehicle newVehicle = vehicleService.saveVehicle(vehicle);
        return new ResponseEntity<>(newVehicle, HttpStatus.CREATED);
    }

    @Operation(summary = "Retrieve all vehicles")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return list all vehicles"),
            @ApiResponse(responseCode = "204", description = "There is empty vehicles")
    })
    @GetMapping()
    public ResponseEntity<List<Vehicle>> getAllVehicles (){
        List<Vehicle> vehicles = vehicleService.getAllVehicles();
        if(vehicles.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(vehicles, HttpStatus.OK);
    }

    @Operation(summary = "Retrieve all vehicles by filters, sort and paging")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return list vehicles with paging"),
            @ApiResponse(responseCode = "204", description = "There is empty vehicles")
    })
    @PostMapping(value = "/filter", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<Vehicle>> getVehiclesByFilter(@RequestBody DynamicFilterSortRequest dynamicFilterSortRequest) {
        Page<Vehicle> vehiclePage = vehicleService.getVehiclesByFilter(dynamicFilterSortRequest);
        if (vehiclePage.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(vehiclePage, HttpStatus.OK);
    }

    @Operation(summary = "Update vehicle by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated vehicle successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request"),
            @ApiResponse(responseCode = "404", description = "Not found vehicle")
    })
    @PatchMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vehicle> createVehicle(@PathVariable Long id, @RequestBody Vehicle vehicle) {
        try {
            vehicle.setVehicleId(id);
            return new ResponseEntity<>(vehicleService.updateVehicle(vehicle), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Delete vehicle by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deleted vehicle successfully"),
            @ApiResponse(responseCode = "404", description = "Not found vehicle"),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Long id) {
        try {
            vehicleService.deleteVehicleById(id);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Get vehicle by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get vehicle information")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable Long id) {
        return new ResponseEntity<>(vehicleService.getVehicleById(id), HttpStatus.OK);
    }
}
