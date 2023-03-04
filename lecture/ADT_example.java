class Car {
    String make;
    String model;
    int mileage;
    String serial_num;

    Car() {
        make = "Ford";
        model = "Taurus";
    }
    Car(String Make, String Model, int Mileage, String SerialNumber) {
        make = Make;
        model = Model;
        mileage = Mileage;
        serial_num = SerialNumber;
    }

    void AddMiles(int miles) {
        mileage += miles;
    }
}
public class ADT_example {
    public static void main(String[] args) {
        Car MyDefaultCar = new Car();
        Car MyCar = new Car("Toyota", "Avalon", 203, "ABC");
        System.out.println(MyDefaultCar.make);
        MyCar.AddMiles(20);
        System.out.println(MyCar.mileage);
    }
}
