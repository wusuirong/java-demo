package com.sherwin.examples.basics;

public class Switch {
    public static void main(String args[]) { 
        for(int i=0; i<6; i++) 
         switch(i) { //这里表达式的值必须为byte，short，int或char类型
           case 0: 
             System.out.println("i is zero."); 
             break; 
           case 1: 
             System.out.println("i is one."); 
            break; 
           case 2: 
             System.out.println("i is two."); 
            break; 
           case 3: 
              System.out.println("i is three."); 
              break; 
           default: 
             System.out.println("i is greater than 3."); 
         } 
      } 
}
