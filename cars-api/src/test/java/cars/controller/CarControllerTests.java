package cars.controller;

import cars.model.Car;
import cars.model.DTOAddCar;
import cars.service.CarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

public class CarControllerTests {

    @InjectMocks
    CarController carController;

    @Mock
    CarService carService;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addCar_ShouldReturn201WhenSuccessful(){
        //Arrange
        DTOAddCar dtoAddCar = new DTOAddCar("Model S", "Tesla");
        //Act
        ResponseEntity<String> response = carController.addCar(dtoAddCar);
        //Assert
        assertEquals(response.getStatusCode().value(), 201);
        assertEquals(response.getBody(), "Car added successfully");
        verify(carService, times(1)).addCar(dtoAddCar);
    }

    @Test
    void addCar_ShouldReturn400WhenError(){
        //Arrange
        DTOAddCar dtoAddCar = new DTOAddCar("Model S", "Tesla");
        doThrow(new IllegalArgumentException("Error adding car")).when(carService).addCar(dtoAddCar);
        //Act
        ResponseEntity<String> response = carController.addCar(dtoAddCar);
        //Assert
        assertEquals(response.getStatusCode().value(), 400);
        assertEquals(response.getBody(), "Error adding car");
        verify(carService, times(1)).addCar(dtoAddCar);
    }

    @Test
    void getCarById_Return200WhenSuccessful(){
        //Arrange
        Long carId = 1L;
        when(carService.getCarById(carId)).thenReturn(new Car(carId, "C1", "Citroen"));
        //Act
        ResponseEntity<Car> response = carController.getCarById(carId);
        //Assert
        assertEquals(response.getStatusCode().value(), 200);
        assertEquals(response.getBody().getId(), carId);
        verify(carService, times(1)).getCarById(carId);
    }

    @Test
    void getCarById_Return404WhenError(){
        //Arrange
        Long carId = 1L;
        when(carService.getCarById(carId)).thenThrow(new IllegalArgumentException("Car with given ID does not exist"));
        //Act
        ResponseEntity<Car> response = carController.getCarById(carId);
        //Assert
        assertEquals(response.getStatusCode().value(), 404);
        assertNull(response.getBody());
        verify(carService, times(1)).getCarById(carId);
    }

    @Test
    void deleteCar_ShouldReturn204WhenSuccessful(){
        //Arrange
        Long carId = 1L;
        //Act
        ResponseEntity<String> response = carController.deleteCar(carId);
        //Assert
        assertEquals(response.getStatusCode().value(), 204);
        assertEquals(response.getBody(), "Car deleted successfully");
        verify(carService, times(1)).deleteCar(carId);
    }

    @Test
    void deleteCar_ShouldReturn404WhenError(){
        //Arrange
        Long carId = 1L;
        doThrow(new IllegalArgumentException("Car with given ID does not exist")).when(carService).deleteCar(carId);
        //Act
        ResponseEntity<String> response = carController.deleteCar(carId);
        //Assert
        assertEquals(response.getStatusCode().value(), 404);
        assertEquals(response.getBody(), "Error deleting car");
        verify(carService, times(1)).deleteCar(carId);
    }

    @Test
    void getAllCArs_ShouldReturn200WhenSuccessful(){
        //Arrange
        when(carService.getAllCars()).thenReturn(List.of(new Car(1L, "C1", "Citroen"), new Car(2L, "Jazz", "Honda")));
        //Act
        ResponseEntity<List<Car>> response = carController.getAllCars();
        //Assert
        assertEquals(response.getStatusCode().value(), 200);
        assertEquals(response.getBody().size(), 2);
        verify(carService, times(1)).getAllCars();
    }

    @Test
    void getAllCars_ShouldReturn404WhenError(){
        //Arrange
        when(carService.getAllCars()).thenThrow(new IllegalArgumentException("No cars available"));
        //Act
        ResponseEntity<List<Car>> response = carController.getAllCars();
        //Assert
        assertEquals(response.getStatusCode().value(), 404);
        assertNull(response.getBody());
        verify(carService, times(1)).getAllCars();
    }

}
