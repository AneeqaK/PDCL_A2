package Q2_PacketSymmetry;
public class RequestQueue{
    private char[] queue;
    private int front;
    private int rear;
    private int size;
    private int capacity;
    public RequestQueue(int capacity){
        this.capacity = capacity;
        queue = new char[capacity];
        front = 0;
        rear = -1;
        size = 0;
    }
    public void enqueue(char item){
        if (size == capacity) {
            System.out.println("Queue is full.");
            return;
        }
        rear = (rear + 1) % capacity;
        queue[rear] = item;
        size++;
    }
    public char dequeue(){
        if (isEmpty()) {
            return '\0';
        }
        char item = queue[front];
        front = (front + 1) % capacity;
        size--;
        return item;
    }
    public boolean isEmpty(){
        return size == 0;
    }
}