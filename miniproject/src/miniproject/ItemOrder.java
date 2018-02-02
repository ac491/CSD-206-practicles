/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniproject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;


public class ItemOrder implements Serializable{
    Item item=null;
    int quantity;
    
    public void addItem() throws IOException{
       
         item=new Book();
         item.getData();
    }
         
    
         public void getQuantity() throws IOException{
              BufferedReader br=new BufferedReader(new InputStreamReader(System.in)); 
             System.out.println("ENTER THE QUANTITY :");
              quantity=Integer.parseInt(br.readLine());
         }
    
    public void display(){
        if(item==null){
            System.out.println("ADD ITEM FIRST");
        }
        else{
            item.display();
        }
    }
}
