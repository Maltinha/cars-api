package cars.api.model;

import lombok.Data;

@Data
public class Car {
    private Long id;
    private String model;
    private String brand;

    public Car(Long id, String model, String brand){
        this.id = id;
        this.model = model;
        this.brand = brand;
    }
}
