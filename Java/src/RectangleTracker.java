package Unit4;

import java.util.Scanner;

public class RectangleTracker {
    private Rectangle[] rect;
    private int index;

    public RectangleTracker(int length) {
        rect = new Rectangle[length];
    }

    public boolean addRectangle(Rectangle rec) {
        if(index < rect.length){
            rect[index] = rec;
            index++;
            return true;
        }
        return false;
    }

    public void printRectangle() {
        for(Rectangle i : rect){
            i.toString();
            System.out.println();
        }
    }

    public Rectangle getLargestRectangle() {
        Rectangle highest = rect[0];

        for(int i = 1; i < rect.length; i++){
            if(rect[i].getArea() > highest.getArea()){
                highest = rect[i];
            }
        }
        return highest;
    }

    public Rectangle getSmallestRectangle() {
        Rectangle lowest = rect[0];

        for(int i = 1; i < rect.length; i++){
            if(rect[i].getArea() < lowest.getArea()){
                lowest = rect[i];
            }
        }
        return lowest;
    }

    public void editRectangle(int index, double length, double width) {
        rect[index].setLength(length);
        rect[index].setWidth(width);
    }

    public void printRectanglesWithSameWidth() {
        int[] sameWidth = new int[10];
        Integer something = 0;

        for(int i = 0; i < rect.length; i++){
            for(int j = 0; j < rect.length; j++){
                if(rect[i].getWidth() == rect[j].getWidth()){
                    sameWidth[something] = i;
                }
            }
        }
    }
}

class rectMain{
    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)){
            System.out.print("How many rectangles do you have?: ");
            var num = in.nextInt();

            RectangleTracker rect = new RectangleTracker(num);

            var length = 0.0;
            while(length != -1){
                System.out.print("Enter rectangle length or -1 to finish: ");
                length = in.nextDouble();
                if(length != -1){
                    System.out.print("Enter rectangle width: ");
                    var width = in.nextDouble();
                    var add = rect.addRectangle(new Rectangle(length, width));
                    if(add == false){
                        System.out.println("You have run out of space");
                        length = -1;
                    }
                }
            }
        }
    }
}
