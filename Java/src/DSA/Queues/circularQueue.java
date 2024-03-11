package DSA.Queues;

public class circularQueue {

    public static void main(String[] args) {
        circularQueue cq = new circularQueue(7);

        for (int i = 0; i < 7; i++) {
            cq.enqueue(0);
        }
    }

    int size;
    int front;
    int rear;
    int[] arr;

    public circularQueue(int size) {
        this.size = size;
        this.front = this.rear = -1;
        this.arr = new int[size];
    }

    //Copilot Methods
    public int enqueue(int value) {
        if ((rear + 1) % size == front) {
            System.out.println("Queue Overflow");
            return -1;
        } else if (front == -1 && rear == -1) {
            front = rear = 0;
            arr[rear] = value;
        } else {
            rear = (rear + 1) % size;
            arr[rear] = value;
        }
        return value;
    }

    public int dequeue() {
        if (front == -1) {
            System.out.println("Queue Underflow");
            return -1;
        } else if (front == rear) {
            int temp = arr[front];
            front = rear = -1;
            return temp;
        } else {
            int temp = arr[front];
            front = (front + 1) % size;
            return temp;
        }
    }

    //Original Methods
    public int enqueuePsuedo(int value) {
        if (rear + 1 == front) { //If rear is just behind front
            System.out.println("Queue Overflow");
            return -1;
        } else if (front == 0 && rear == size - 1) { //If rear is at the end and front is at the start
            System.out.println("Queue Overflow");
            return -1;
        } else if (front == -1) { //If queue is empty
            front = rear = 0;
            arr[rear] = value;
        } else if (rear == size - 1 && front != 0) { //If rear is at the end and front is not at the start
            rear = 0;
            arr[rear] = value;
        } else { //If rear is not at the end
            rear = rear + 1;
            arr[rear] = value;
        }
        return value;
    }

    public int dequeuePsuedo() {
        if(front == -1) { //If queue is empty
            System.out.println("Queue Underflow");
            return -1;
        } else if(front == rear) { //If there is only one element in the queue
            int temp = arr[front];
            arr[front] = 0;
            front = rear = -1;
            return temp;
        } else if(front == size - 1 && rear != 0) { //If front is at the end and rear is not at the start
            int temp = arr[front];
            arr[front] = 0;
            front = 0; //Move front to the start
            return temp;
        } else { //If front is not at the end
            int temp = arr[front];
            arr[front] = 0;
            front = front + 1; //Move front to the next element
            return temp;
        }
    }

    public void display() {
        if (front == -1) {
            System.out.println("Queue is empty");
        } else if (front <= rear) { //If front is before rear
            for (int i = front; i <= rear; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
        } else { //If rear is before front
            for (int i = front; i < size; i++) { //Print from front to the end
                System.out.print(arr[i] + " ");
            }
            for (int i = 0; i <= rear; i++) { //Print from start to rear
                System.out.print(arr[i] + " ");
            }
            System.out.println();
        }
    }
}
