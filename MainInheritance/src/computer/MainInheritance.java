/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package computer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;


public class MainInheritance {
   static ArrayList<Users> users =new ArrayList<>();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        
        int ch;String uid;
       BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
       Scanner sc =new Scanner(System.in);
        System.out.println("ENTER YOUR CHOICE: 1.EXISTING USER  2.NEW USER  3.EXIT");
        ch=sc.nextInt();
        while(ch!=3){
        switch(ch){
            
            case 1 :int flag=0;
                    System.out.println("ENTER YOUR USER ID");
                    uid=br.readLine();
                   for (Users user : users) {
                          if(user.userId.equalsIgnoreCase(uid)){
                              System.out.println("WELCOME BACK!!");
                              user.userInterface();
                              flag=1;
                              break;
                          }
                   }
                   if(flag==0){
                       System.out.println("USER ID DOES NOT EXISTS");
                   }
                     break;
                   
            case 2:   Users u=new Users();
                      System.out.println("WELCOME , YOUR USER ID IS: "+u.userId);
                      u.userInterface();
                      users.add(u);
                      break;
                    //  u.selectDevices();       
                    
        }
         System.out.println("ENTER YOUR CHOICE: 1.EXISTING USER  2.NEW USER  3.EXIT");
         ch=sc.nextInt();
      }
   
    }
    
}
