/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fibonacci;

/**
 *
 * @author Guest-Student
 */
public class Fibonacci {
    
    public static int fibonacci(int n1, int n2, int n3) {
if(n3==1)
return n1;
if(n3==2)
return n2;
return fibonacci(n1, n2, n3-2) + fibonacci(n1, n2, n3-1);
}
    
    public static void main(String[] args) {
        System.out.println( fibonacci(0,1,5));
    }
}
