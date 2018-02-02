
package groceryshop;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;


public class CartItems {
    String itemId;
    int quantity;
    ArrayList<Items> item=new ArrayList<>();
    
    public void getData() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Scanner sc=new Scanner(System.in);
        System.out.println("ENTER THE ITEM ID OF THE ITEM TO BE ADDED TO CART");
        itemId=br.readLine();
        item.add(getItem(itemId));
        if(item==null){
           return;
        }
        System.out.println("ENTER THE QUANTITY");
        quantity=sc.nextInt(); 
        item.get(item.size()-1).quantity=quantity;
    }
    
   public Items getItem(String itemId){
         int flag=0;
        
        
        for(Items i:Category.items){
                if(itemId.equalsIgnoreCase(i.itemId)){
                    flag=1;
                    return i;
                
                }
        }
        if(flag==0){
            System.out.println("ITEM ID DOES NOT EXISTS");
            return null;
        }
        return null;
   
   }  
}