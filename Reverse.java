/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package reverse;

/**
 *
 * @author Guest-Student
 */
public class Reverse {

    public static String re(String word){
        String temp ="";
        for(int i=word.length()-1;i>=0;i--){
            temp+=word.charAt(i);
        }
        return temp;
    }
    
    public static void main(String[] args) {
        System.out.print(re("SILPAKORN"));
    }
    
}
