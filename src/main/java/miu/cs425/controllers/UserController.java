package miu.cs425.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import miu.cs425.dtos.UserDto;
import miu.cs425.dtos.requests.DynamicFilterSortRequest;
import miu.cs425.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private IUserService userService;

    @Operation(summary = "Retrieve all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return list all users"),
            @ApiResponse(responseCode = "204", description = "There is empty users")
    })
    @GetMapping()
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> userDTOs = userService.getAllUsers();
        if (userDTOs.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(userDTOs, HttpStatus.OK);
    }

    @Operation(summary = "Retrieve all users by filters, sort and paging")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return list users with paging"),
            @ApiResponse(responseCode = "204", description = "There is empty users")
    })
    @PostMapping(value = "/filter", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<UserDto>> getUsersByFilter(@RequestBody DynamicFilterSortRequest dynamicFilterSortRequest) {
        Page<UserDto> userDtoPage = userService.getUsersByFilter(dynamicFilterSortRequest);
        if (userDtoPage.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(userDtoPage, HttpStatus.OK);
    }

    @Operation(summary = "Add new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created user successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        return new ResponseEntity<>(userService.saveUser(userDto), HttpStatus.CREATED);
    }
}
