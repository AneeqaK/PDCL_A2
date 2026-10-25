package Q2_PacketSymmetry;
import java.util.Scanner;
public class PacketSymmetryAuditor{
    public static boolean isSymmetric(String data){
        TransactionStack stack = new TransactionStack(data.length());
        RequestQueue queue = new RequestQueue(data.length());
        for (int i = 0; i < data.length(); i++){
            char ch = data.charAt(i);
            stack.push(ch);
            queue.enqueue(ch);
        }
        while (!stack.isEmpty() && !queue.isEmpty()){
            char stackChar = stack.pop();
            char queueChar = queue.dequeue();

            if (stackChar != queueChar){
                return false;  
            }
        }
        return true;
    }
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        System.out.print("Enter packet data: ");
        String data = input.nextLine();
        if (isSymmetric(data)) {
            System.out.println("Result: Packet data is symmetric.");
        } else {
            System.out.println("Result: Packet data is not symmetric.");
        }
        input.close();
    }
}

