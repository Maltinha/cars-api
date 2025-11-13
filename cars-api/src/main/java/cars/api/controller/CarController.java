package cars.api.controller;

import cars.api.model.Car;
import cars.api.model.DTOAddCar;
import cars.api.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {

    @Autowired
    CarService carService;

    @PostMapping("")
    public ResponseEntity<String> addCar(@RequestBody DTOAddCar car){
        try {
            carService.addCar(car);
            return ResponseEntity.ok("Car added successfully");
        }catch (Exception e){
            return ResponseEntity.status(400).body("Error adding car");
        }

    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Car>> getAllCars(){
        try {
            List<Car> cars = carService.getAllCars();
            return ResponseEntity.ok(cars);
        }catch (Exception e){
            return ResponseEntity.status(400).body(null);
        }

    }

}
