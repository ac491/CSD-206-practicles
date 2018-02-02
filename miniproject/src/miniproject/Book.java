/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniproject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Book extends Item {
   String author;
   String edition;
   String volume;
   
   @Override
   public void getData() throws IOException{
       BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
       System.out.println("ENTER THE AUTHOR NAME");
       author=br.readLine();
       System.out.println("ENTER THE BOOK EDITION");
       edition=br.readLine();
       System.out.println("ENTER THE VOLUME NUMBER OF BOOK");
       volume=br.readLine();
       
       super.getData();
   }
   
   @Override
   public void display(){
       System.out.println("AUTHOR: "+author);
       System.out.println("EDITION: "+edition);
       System.out.println("VOLUME: "+volume);
       super.display();
   }
   
}
