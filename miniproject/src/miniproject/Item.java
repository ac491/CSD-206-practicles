
package miniproject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;


public class Item implements Serializable{
   String title;
   String publisher;
   String yearPublished;
   String ISBN;
   double price;
   
   public void getData() throws IOException{
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
       System.out.println("ENTER THE TITLE OF THE BOOK");
       title = br.readLine();
       System.out.println("ENTER THE NAME OF PUBLISHER");
       publisher=br.readLine();
       System.out.println("ENTER THE YEAR OF BOOK PUBLISHION");
       yearPublished=br.readLine();
       System.out.println("ENTER THE UNIQUE ISBN OF THE BOOK");
       ISBN=br.readLine();
       System.out.println("ENTER THE PRICE OF BOOK");
       price=Float.parseFloat(br.readLine());


   }
   public void display(){
       System.out.println("TITLE: "+title);
       System.out.println("PUBLISHER: "+publisher);
       System.out.println("YEAR PUBLISHED: "+yearPublished);
       System.out.println("ISBN: "+ISBN);
       System.out.println("PRICE: "+price);
   }
   
}
