package Objects;

public class inheritance {
    public static void main(String[] args) {
        Car cr = new Car();

        cr.go();

        Bicycle bk = new Bicycle();

        bk.stop();

        System.out.println(cr.doors);
        System.out.println(bk.pedals);
    }
}

class Vehicle{
    double speed;
    
    void go(){
        System.out.println("This vehicle is moving");
    }
    void stop(){
        System.out.println("This vehicle has stopped");
    }
}

class Car extends Vehicle{
    int wheels = 4;
    int doors = 4;
}

class Bicycle extends Vehicle{
    int wheels = 2;
    int pedals = 2;
}
