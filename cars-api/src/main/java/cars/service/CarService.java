package cars.service;

import cars.model.Car;
import cars.model.DTOAddCar;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CarService {

    Map<Long, Car> carDatabase = new HashMap<>();
    Long id = 0L;

    public CarService(){
        addCar(new DTOAddCar("C1", "Citroen"));
        addCar(new DTOAddCar("Jazz", "Honda"));
        addCar(new DTOAddCar("Yaris", "Toyota"));
        addCar(new DTOAddCar("Lupo", "Volkswagen"));
    }

    public void addCar(DTOAddCar car){
        if (car == null) {
            throw new IllegalArgumentException("Car cannot be null");
        } else if(car.getModel() == null || car.getModel().isEmpty()){
            throw new IllegalArgumentException("Car model cannot be null or empty");
        } else if(car.getBrand() == null || car.getBrand().isEmpty()){
            throw new IllegalArgumentException("Car brand cannot be null or empty");
        } else {
        Car newCar = new Car(id, car.getModel(), car.getBrand());
        carDatabase.put(id, newCar);
        id++;
        }
    }

    public void deleteCar(Long id){
        if(carDatabase.containsKey(id)){
            carDatabase.remove(id);
        } else {
            throw new IllegalArgumentException("Car with given ID does not exist");
        }
    }

    public Car getCarById(Long id){
        if(!carDatabase.containsKey(id)){
            throw new IllegalArgumentException("Car with given ID does not exist");
        }
        return carDatabase.get(id);
    }

    public List<Car> getAllCars(){
        if(carDatabase.isEmpty()){
            throw new IllegalArgumentException("No cars available");
        }
        return carDatabase.values().stream().toList();
    }

}
