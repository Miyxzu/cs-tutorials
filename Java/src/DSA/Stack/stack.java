package DSA.Stack;

public class stack {
    public static void main(String[] args) {
        int stack[] = {0,0,0,0,0};

        // for (int i = 0; i < 4; i++) {
        //     push(stack, top, i);
        //     top++;
        // }

        push(stack, 6);
        System.out.println(Global.top);
        peek(stack);
        pop(stack);
    }
    //Copilot Methods
    static void push(int stack[], int data) {
        if (Global.top == stack.length - 1) {
            System.out.println("Stack Overflow");
        } else {
            Global.top++;
            stack[Global.top] = data;
            display(stack, Global.top);
            System.out.println();
        }
    }

    static void pop(int stack[]) {
        if (Global.top == -1) {
            System.out.println("Stack Underflow");
        } else {
            System.out.println(stack[Global.top]);
            Global.top--;
            stack[Global.top] = 0;
            display(stack, Global.top);
        }
    }

    static void peek(int stack[]) {
        if (Global.top == -1) {
            System.out.println("No Elements");
        } else {
            System.out.println(stack[Global.top]);
        }
    }

    static void display(int stack[], int top) {
        if (top == -1) {
            System.out.println("No Elements");
        } else {
            for (int i = 0; i <= top; i++) {
                System.out.print(stack[i] + " ");
            }
        }
    }
}

class Global {
    public static int top = -1;
}
