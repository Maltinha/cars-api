package cars.service;

import cars.model.Car;
import cars.model.DTOAddCar;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CarServiceTests {

    /*
    The test should verify the state of the database after adding a car.
    */
    @Test
    void addCar_shouldStoreCarCorrectly() {
        //Arrange
        CarService carService = new CarService();
        DTOAddCar newcar = new DTOAddCar();
        newcar.setModel("ForFour");
        newcar.setBrand("Smart");
        //Act
        carService.addCar(newcar);
        //Assert
        assertEquals(5, carService.getAllCars().size());
        Car addedCar = carService.getCarById(4L);
        assertEquals("ForFour", addedCar.getModel());
        assertEquals("Smart", addedCar.getBrand());
    }

    @Test
    void addCar_shouldThrowException_whenCarIsNull() {
        //Arrange
        CarService carService = new CarService();
        //Act + Assert
        assertThrows(IllegalArgumentException.class, () -> carService.addCar(null));
    }

    @Test
    void addCar_shouldThrowException_whenCarIsInvalid() {
        //Arrange
        CarService carService = new CarService();
        DTOAddCar carWithNoModel = new DTOAddCar("", "BrandX");
        DTOAddCar carWithNoBrand = new DTOAddCar("ModelY", "");
        //Act + Assert
        assertThrows(IllegalArgumentException.class, () -> carService.addCar(carWithNoModel));
        assertThrows(IllegalArgumentException.class, () -> carService.addCar(carWithNoBrand));
    }

    @Test
    void deleteCar_shouldRemoveCarCorrectly() {
        //Arrange
        CarService carService = new CarService();
        //Act
        carService.deleteCar(1L);
        //Assert
        assertEquals(3, carService.getAllCars().size());
        assertThrows(IllegalArgumentException.class, () -> carService.getCarById(1L));
    }

    @Test
    void deleteCar_shouldThrowException_whenCArDoesNotExist() {
        //Arrange
        CarService carService = new CarService();
        //Act + Assert
        assertThrows(IllegalArgumentException.class, () -> carService.deleteCar(10L));
    }

    @Test
    void getCarById_shouldReturnCorrectCar() {
        //Arrange
        CarService carService = new CarService();
        //Act
        Car car = carService.getCarById(2L);
        //Assert
        assertEquals("Yaris", car.getModel());
        assertEquals("Toyota", car.getBrand());
    }

    @Test
    void getCarById_shouldThrowException_whenCarDoesNotExist() {
        //Arrange
        CarService carService = new CarService();
        //Act + Assert
        assertThrows(IllegalArgumentException.class, () -> carService.getCarById(10L));
    }

    @Test
    void getAllCars_RetreivesAllCars() {
        //Arrange
        CarService carService = new CarService();
        DTOAddCar newCar = new DTOAddCar("ForFour", "Smart");
        //Act1
        carService.getAllCars();
        //Assert1
        assertEquals(4, carService.getAllCars().size());
        //Act2
        carService.addCar(newCar);
        carService.getAllCars();
        //Assert2
        assertEquals(5, carService.getAllCars().size());
    }

    @Test
    void getAllCars_shouldThrowException_whenNoCarsAvailable() {
        //Arrange
        CarService carService = new CarService();
        //Act
        carService.carDatabase.clear();
        //Assert
        assertThrows(IllegalArgumentException.class, () -> carService.getAllCars());
    }


}