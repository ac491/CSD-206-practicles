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
import java.util.ArrayList;


public class ShoppingCart implements Serializable {
  ArrayList<ItemOrder> itemOrder;
  
ShoppingCart(){
    itemOrder=new ArrayList<>();
}

public void cart() throws IOException{
    int ch;
    BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    System.out.println("SELECT YOUR CHOICE: 1.ADD ITEM TO CART  2.DISPLAY CART  3.REMOVE AN ITEM FROM CART 4.SAVE CART  5.CLEAR CART 6.EXIT");
    ch=Integer.parseInt(br.readLine());
    while(ch!=6){
        switch(ch){
            case 1:addItem();
                   break;
            case 2:displayCart();
                     break;
            case 3:System.out.println("ENTER THE UNIQUE ISBN OF THE BOOK");
                   String id=br.readLine();
                   removeItem(id);
                   break;
            case 4:saveCart();
                   break;
            case 5:clearCart();
                  System.out.println("CLEARED YOUR CART!!");
                  break;
            default:System.out.println("WRONG CHOICE");
        }
        System.out.println("SELECT YOUR CHOICE: 1.ADD ITEM TO CART  2.DISPLAY CART  3.REMOVE AN ITEM FROM CART 4.SAVE CART 5.CLEAR CART 6.EXIT");
        ch=Integer.parseInt(br.readLine());
    }
}

   public void addItem() throws IOException{
       String ch="y";
       BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
       while(ch.equalsIgnoreCase("y")){
       System.out.println("ENTER THE UNIQUE BOOK ID(ISBN) OF THE BOOK TO BE ADDED");
       String id=br.readLine();
       ArrayList<Item> items=OnlineBookStore.readItems();
       if(items==null){
           System.out.println("THERE ARE NO BOOKS IN THE SHOP.PLEASE COME BACK LATER!!");
           return;
       }
       int flag=0;
       for(Item i:items){
           if(i.ISBN.equalsIgnoreCase(id)){
               ItemOrder item=new ItemOrder();
               item.item=i;
               System.out.println("ENTER QUANTITY");
               item.quantity=Integer.parseInt(br.readLine());
               maintainStock(i,item.quantity,item); //to maintain the stock of items
              // itemOrder=readOrders();
               //if(itemOrder==null){
               //    itemOrder=new ArrayList<>();
              // }
               itemOrder.add(item);
              // storeCart(itemOrder);
               System.out.println("ITEM SUCCESSFULLY ADDED TO CART!!");
               flag=1;
               break;
           }
       }
       if(flag==0){
            System.out.println("BOOK DOES NOT EXIST!");
        }
       System.out.println("DO YOU ADD MORE ITEMS TO CART(y/n)");
       ch=br.readLine();
      }
       
   }
   
   public void maintainStock(Item i,int quantity,ItemOrder itemOrder){
       ArrayList<ItemOrder> itemsOrdered=Admin.readItems();
       for(ItemOrder item:itemsOrdered){
           if(i.ISBN.equalsIgnoreCase(item.item.ISBN)){
               if(quantity>item.quantity){
                   System.out.println("INSUFFICIENT STOCK!! CAN ADD MAXIMUM OF "+item.quantity+" TO CART");
                   itemOrder.quantity=item.quantity;
                   item.quantity=0;
                   Admin.writeItems(itemsOrdered);
                   ArrayList<String> messages=Admin.readMessages();
                   if(messages==null){
                       messages=new ArrayList<>();
                   }
                   messages.add("INSUFFICIENT STOCK!! ADD MORE BOOKS OF ISBN "+item.item.ISBN);
                   Admin.newMessage=1;
                   Admin.writeMessages(messages);
                   break;
               }
               else{
                   item.quantity-=quantity;
                   Admin.writeItems(itemsOrdered);
                   break;
               }
           }
       }
   }
   
   
   public void displayCart(){
       int a=1;
     //  itemOrder=readOrders();
       if(itemOrder.isEmpty()){
           System.out.println("EMPTY CART!!");
           return;
       }
       System.out.format("%-16s%-16s%-40s%-25s%-25s%-30s%-16s%-16s%-16s%-16s\n\n","S.NO","ISBN","TITLE","AUTHOR", "PUBLISHER","YEAR PUBLISHED","VOLUME","EDITION","PRICE","QUANTITY");
       for(ItemOrder i:itemOrder){
            Book b=(Book)i.item;
            System.out.format("%-16s%-16s%-40s%-25s%-25s%-30s%-16s%-16s%-16s%-16s\n\n",a,i.item.ISBN,i.item.title,b.author,i.item.publisher,i.item.yearPublished,b.volume,b.edition,i.item.price,i.quantity);
            a++;
       }
   }
   
   public void removeItem(String ISBN){
      // itemOrder=readOrders();
      // if(itemOrder==null){
       //    System.out.println("YOUR CART IS EMPTY");
       //    return;
      // }
       int flag=0;
       for(ItemOrder i:itemOrder){
           if(i.item.ISBN.equalsIgnoreCase(ISBN)){
               itemOrder.remove(i);
               flag=1;
               ArrayList<ItemOrder> itemsOrdered=Admin.readItems();
               for(ItemOrder item:itemsOrdered){             //TO ADD THE STOCK BACK
                  if(item.item.ISBN.equalsIgnoreCase(i.item.ISBN)){
                      item.quantity+=i.quantity;
                     Admin.writeItems(itemsOrdered);
                      break;
                  } 
               }
               System.out.println("REMOVED ITEM FROM CART");
               saveCart();
               break;
           }
       }
       if(flag==0){
           System.out.println("ITEM NOT FOUND!!");
       }
       
   }
   
   
   
   public void saveCart(){
      // storeCart(itemOrder); 
      System.out.println("SAVED YOUR CART!!");
   }
   
   public void clearCart(){
       itemOrder.clear();
   }
   
   /* public static void storeCart(ArrayList<ItemOrder> orders){
        OutputStream ops = null;
		ObjectOutputStream objOps = null;
		try {
			ops = new FileOutputStream("orders.txt",false);//to overrride
			objOps = new ObjectOutputStream(ops);
			objOps.writeObject(orders);
			objOps.flush();
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
                        System.out.println(e);
		} 
		
     }
      
       public static ArrayList<ItemOrder> readOrders()  {
             InputStream fileIs = null;
        ObjectInputStream objIs = null;
        try {
            fileIs = new FileInputStream("orders.txt");
            objIs = new ObjectInputStream(fileIs);
            ArrayList<ItemOrder> i = (ArrayList<ItemOrder>) objIs.readObject();
            return i;
            
        } catch (FileNotFoundException e) {
           System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        
        }
             return null;
     }*/
   
}
