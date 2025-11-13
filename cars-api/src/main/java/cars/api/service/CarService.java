package cars.api.service;

import cars.api.model.Car;
import cars.api.model.DTOAddCar;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CarService {

    Map<Long, Car> carDatabase = new HashMap<>();
    Long id = 0L;

    public void addCar(DTOAddCar car){
        if (car == null) {
            throw new IllegalArgumentException("Car cannot be null");
        } else {
            Car newCar = new Car(id, car.getModel(), car.getBrand());
            carDatabase.put(id, newCar);
            id++;
        }
    }

    public List<Car> getAllCars(){
        return carDatabase.values().stream().toList();
    }
}
