/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniproject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Miniproject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Scanner sc=new Scanner(System.in);
        //Mail.sendMail();
        BufferedReader b=new BufferedReader(new InputStreamReader(System.in));
        int ch;
        System.out.println("SELECT YOUR CHOICE FOR LOGIN : 1.ADMIN  2.CUSTOMER 3.EXIT");
        
        try{ch=sc.nextInt();}
        catch(InputMismatchException e){
            System.out.println("PLEASE ENTER AN INTEGER VALUE");
            ch=Integer.parseInt(b.readLine());
        }
        while(ch!=3){
            switch(ch){
                case 1:BufferedReader br = null;     
                       try {
                             br = new BufferedReader(new FileReader("admin.txt"));
                           } catch (FileNotFoundException ex) {
                                System.out.println(ex);
                           }
                       
                       if (br.readLine() == null) {
                           br=new BufferedReader(new InputStreamReader(System.in));
                           System.out.println("SIGN UP:");
                        System.out.println("ENTER YOUR ADMIN ID FOR LOGIN");
                        String id=br.readLine();
                        System.out.println("ENTER YOUR PASSWORD");
                        String pass=br.readLine();
                        Admin a=new Admin(id,pass);
                        a.adminInterface();
                        Admin.writeFile(a);
                       }
                      else{
                              Admin a=null;
                            br=new BufferedReader(new InputStreamReader(System.in));
                            a=Admin.readFile();
                            System.out.println("ENTER YOUR ADMIN ID");
                            String id=br.readLine();
                            System.out.println("ENTER YOUR PASSWORD TO LOGIN:");
                            String pass=br.readLine();
                            if(BCrypt.checkpw(pass, a.adminPassword)&&id.equals(a.adminId)){
                                System.out.println("WELCOME BACK!!");
                                a.adminInterface();
                                Admin.writeFile(a);
                            }
                            else{
                                System.out.println(" INCORRECT ID OR/AND PASSWORD");
                            }
                            
                        }
                       break;
                       
                case 2:   OnlineBookStore obs=new OnlineBookStore();
                          obs.menu();
                          break;
            }
            System.out.println("SELECT YOUR CHOICE FOR LOGIN : 1.ADMIN  2.CUSTOMER 3.EXIT");
            ch=Integer.parseInt(b.readLine());
        }
    }
    
}
