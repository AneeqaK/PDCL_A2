package Q2_PacketSymmetry;
public class TransactionStack{
    private char[] stack;
    private int top;
    private int capacity;
    public TransactionStack(int capacity){
        this.capacity = capacity;
        stack = new char[capacity];
        top = -1;
    }
    public void push(char item){
        if (top == capacity - 1){
            System.out.println("Stack is full.");
            return;
        }
        stack[++top] = item;
    }
    public char pop(){
        if (isEmpty()){
            return '\0';
        }
        return stack[top--];
    }
    public char peek(){
        if (isEmpty()){
            return '\0';
        }
        return stack[top];
    }
    public boolean isEmpty(){
        return top == -1;
    }
}

