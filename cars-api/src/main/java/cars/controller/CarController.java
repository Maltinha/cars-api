package cars.controller;

import cars.model.Car;
import cars.model.DTOAddCar;
import cars.service.CarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping("")
    public ResponseEntity<String> addCar(@RequestBody DTOAddCar car){
        try {
            carService.addCar(car);
            return ResponseEntity.status(201).body("Car added successfully");
        }catch (Exception e){
            return ResponseEntity.status(400).body("Error adding car");
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCar(@PathVariable Long id){
        try {
            carService.deleteCar(id);
            return ResponseEntity.status(204).body("Car deleted successfully");
        }catch (Exception e){
            return ResponseEntity.status(404).body("Error deleting car");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable Long id){
        try {
            Car car = carService.getCarById(id);
            return ResponseEntity.ok(car);
        }catch (Exception e){
            return ResponseEntity.status(404).body(null);
        }

    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Car>> getAllCars(){
        try {
            List<Car> cars = carService.getAllCars();
            return ResponseEntity.ok(cars);
        }catch (Exception e){
            return ResponseEntity.status(404).body(null);
        }

    }

}
