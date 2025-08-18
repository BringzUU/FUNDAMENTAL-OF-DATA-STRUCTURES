/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Guest-Student
 */
public class Factorial {
 
public class Fac {
    
    public static int fac(int n) {
if(n==1)
return 1;
return n*fac(n-1);
}
    
    public static void main(String[] args) {
        System.out.println( fac(5));
    }
}

}
