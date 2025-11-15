package cars.model;

import lombok.Data;

@Data
public class DTOAddCar {
    private String model;
    private String brand;

    public DTOAddCar(String model, String brand){
        this.model = model;
        this.brand = brand;
    }

    public DTOAddCar(){}
}
