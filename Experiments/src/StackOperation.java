import java.util.Scanner;
import java.util.Stack;

public class StackOperation {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        Stack<Integer> s = new Stack<>();
        StackOperation op = new StackOperation();
        while(size-- > 0){
            s.push(sc.nextInt());
        }

        s = op.sort(s);
        s.forEach(System.out::println);
    }

    public Stack<Integer> sort(Stack<Integer> s)
    {
        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();

        while(!s.empty()){
            Integer top = s.pop();
            if(!s2.empty() && top > s2.peek()){
                while(!s2.empty() && s2.peek() < top){
                    s1.push(s2.pop());
                }
                s2.push(top);
            }else{
                while(!s1.empty() && s1.peek() > top){
                    s2.push(s1.pop());
                }
                s1.push(top);
            }
        }
        while(!s2.empty()){
            s1.push(s2.pop());
        }
        return s1;
    }
}
