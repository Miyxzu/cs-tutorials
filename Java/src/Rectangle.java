package Unit4;

public class Rectangle {

    private double length;
    private double width;
 
    public Rectangle(double l, double w) {
        length = l;
        width = w;
    }
    //some setters and getters not shown
    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }


    public double getWidth() {
        return width;
    }


    public void setWidth(double width) {
        this.width = width;
    }


    public double getArea() {
        return getLength() * getWidth();
    }

    public double getPerimeter() {
        return 2 * (getLength() + getWidth());
    }

    public void printAreaPerimeter() {
        System.out.println("Area: " + getArea());
        System.out.println("Perimeter: " + getPerimeter());
    }

    @Override
    public String toString() {
        return "Length: " + length + " Width: " + width;
    }
 }
 