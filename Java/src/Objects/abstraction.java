package Objects;

public class abstraction {
    public static void main(String[] args) {
        // Create a new object of type 'Dog'
        Dog dog = new Dog();
        // Call the 'bark' method
        dog.bark();
    }
}

abstract class Animal {
        // Abstract method
    public abstract void bark();
}

class Dog extends Animal {
    // Implement the abstract method
    public void bark() {
        System.out.println("Woof!");
    }
} 