/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniproject;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;



public class Admin implements Serializable
{
     String adminId;
     String adminPassword;
     ArrayList<String> messages;
     ArrayList<ItemOrder> itemsOrdered;
     static  final double SALESTAX=15; //these constants can be changed 
     static  final double SHIPPINGFEE=100;//""
     static int newMessage=0;
     
     
     Admin(String adminId,String password){
         this.adminId=adminId;
         this.adminPassword=BCrypt.hashpw(password, BCrypt.gensalt(10));
         
         itemsOrdered=new ArrayList<>();
         messages=new ArrayList<>();
        
         
     }
    
     
     public void messages(){
        newMessage=0;
       messages=readMessages();
       if(messages==null){
           messages=new ArrayList<>();
       }
       int a=1;
       if(messages.isEmpty()){
           System.out.println("YOU DO NOT HAVE ANY MESSAGES");
       }
       else{
       System.out.println("MESSAGES: ");
            for(String m:messages){   
                System.out.println(a+". "+m);
                a++;
                
            }
       }
     }
     
     public void removeAllMessages(){
         messages=readMessages();
         if(messages==null){
             messages=new ArrayList<>();
         }
         messages.clear();
         writeMessages(messages);
     }
     
     public void displayAllBooks(){
         int a=1;
         itemsOrdered=readItems();
         if(itemsOrdered==null){
             itemsOrdered=new ArrayList<>();
         }
         for(ItemOrder i:itemsOrdered){
             System.out.printf("\n");
             System.out.println("BOOK: "+a);
             i.display();
             System.out.println("QUANTITY: "+i.quantity);
             System.out.printf("\n");
             a++;
         }
     }
      
     public void addBooks() throws IOException{
         BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
         ItemOrder i=new ItemOrder();
         i.addItem();
         i.getQuantity();
         writeCSV(i.item.ISBN);
         writeCSV(",");
         writeBookName(i.item.title);
         System.out.println("ENTER THE GENRE OF THE BOOK");
         String genre=br.readLine();
         writeCSV(genre);
         writeCSV("\n");
         ArrayList<Item> items=OnlineBookStore.readItems();
         if(items==null){
             items=new ArrayList<>();
         }
         items.add(i.item);
         OnlineBookStore.writeItems(items);
         itemsOrdered=readItems();
         if(itemsOrdered==null){
             itemsOrdered=new ArrayList<>();
         }
         itemsOrdered.add(i);
         writeItems(itemsOrdered);
         
     }
     
     public void addQuantity(String isbn) throws IOException{
         int flag=0;
         itemsOrdered=readItems();
         if(itemsOrdered==null){
             itemsOrdered=new ArrayList<>();
         }
         for(ItemOrder i:itemsOrdered){
             if(i.item.ISBN.equalsIgnoreCase(isbn)){
                 i.getQuantity();
                 System.out.println("SUCCESSFULLY ADDED MORE BOOKS!!");
                 writeItems(itemsOrdered);
                 flag=1;
                 break;
             }
         }
         if(flag==0){
             System.out.println("BOOK NOT FOUND!!");
         }
     }
     
     
      public static void writeMessages(ArrayList<String> message){
        OutputStream ops = null;
		ObjectOutputStream objOps = null;
		try {
			ops = new FileOutputStream("message.txt",false);//to overrride
			objOps = new ObjectOutputStream(ops);
			objOps.writeObject(message);
			objOps.flush();
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
                        System.out.println(e);
		} 
		
     }
      
       public static ArrayList<String> readMessages()  {
             InputStream fileIs = null;
        ObjectInputStream objIs = null;
        try {
            fileIs = new FileInputStream("message.txt");
            objIs = new ObjectInputStream(fileIs);
            ArrayList<String> m = (ArrayList<String>) objIs.readObject();
            return m;
            
        } catch (FileNotFoundException e) {
           System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        
        }
             return null;
     }
       
     public static void writeFile(Admin a){
        OutputStream ops = null;
		ObjectOutputStream objOps = null;
		try {
			ops = new FileOutputStream("admin.txt",false);//to overrride
			objOps = new ObjectOutputStream(ops);
			objOps.writeObject(a);
			objOps.flush();
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
                        System.out.println(e);
		} 
		
     }
      public static void writeItems(ArrayList<ItemOrder> i){
        OutputStream ops = null;
		ObjectOutputStream objOps = null;
		try {
			ops = new FileOutputStream("itemOrder.txt",false);//to overrride
			objOps = new ObjectOutputStream(ops);
			objOps.writeObject(i);
			objOps.flush();
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
                        System.out.println(e);
		} 
		
     }
             
     public static Admin readFile()  {
             InputStream fileIs = null;
        ObjectInputStream objIs = null;
        try {
            fileIs = new FileInputStream("admin.txt");
            objIs = new ObjectInputStream(fileIs);
            Admin a = (Admin) objIs.readObject();
            return a;
            
        } catch (FileNotFoundException e) {
           System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        
        }
             return null;
     }
     
     public static ArrayList<ItemOrder> readItems()  {
             InputStream fileIs = null;
        ObjectInputStream objIs = null;
        try {
            fileIs = new FileInputStream("itemOrder.txt");
            objIs = new ObjectInputStream(fileIs);
            ArrayList<ItemOrder> a = (ArrayList<ItemOrder>) objIs.readObject();
            return a;
            
        } catch (FileNotFoundException e) {
           System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        
        }
             return null;
     }
     
     public static void writeCSV(String content) throws IOException{
         BufferedWriter fileWriter = null;
         try {
             fileWriter = new BufferedWriter(new FileWriter("C:\\Users\\sneha\\Documents\\NetBeansProjects\\miniproject\\books.txt",true));
             fileWriter.write(content);
             if(!content.equalsIgnoreCase("\n")){
             fileWriter.write(",");
             }
             
             
         } catch (IOException ex) {
             System.out.println(ex);
         }
         

     }
     public static void writeBookName(String content) throws IOException{
         BufferedWriter fileWriter = null;
         try {
             fileWriter = new BufferedWriter(new FileWriter("bookname.txt",true));
             fileWriter.write(content);
             fileWriter.newLine();
             
             
         } catch (IOException ex) {
             System.out.println(ex);
         }
         

     }
     
     
     public void adminInterface() throws IOException{
         int ch;
         Scanner sc=new Scanner(System.in);
         BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
         if(newMessage==1){
          System.out.println("SELECT YOUR CHOICE: \n1.ADD ITEMS \n2.DISPLAY ALL BOOKS \n3.INCREASE THE STOCK OF A PARTICULAR BOOK \n4.READ MESSAGES ("+messages.size()+" new messages) \n5.DELETE ALL MESSAGES \n6.EXIT");   
         }
         else{
         System.out.println("SELECT YOUR CHOICE: \n1.ADD ITEMS \n2.DISPLAY ALL BOOKS \n3.INCREASE THE STOCK OF A PARTICULAR BOOK \n4.READ MESSAGES  \n5.DELETE ALL MESSAGES \n6.EXIT");
         }
         ch=sc.nextInt();
         while(ch!=6){
             switch(ch){
                 case 1:addBooks();
                         break;
                 case 2:displayAllBooks();
                        break;
                 case 3:System.out.println("ENTER THE UNIQUE ISBN OF THE BOOK");
                        String isbn=br.readLine();
                        addQuantity(isbn);
                         break;
                         
                 case 4:messages();
                        break;
                 case 5:removeAllMessages();
                        break;
                 default:System.out.println("WRONG CHOICE");
                 
             }
              if(newMessage==1){
          System.out.println("SELECT YOUR CHOICE: \n1.ADD ITEMS \n2.DISPLAY ALL BOOKS \n3.INCREASE THE STOCK OF A PARTICULAR BOOK \n4.READ MESSAGES ("+messages.size()+" new messages) \n5.DELETE ALL MESSAGES \n6.EXIT");   
         }
         else{
         System.out.println("SELECT YOUR CHOICE: \n1.ADD ITEMS \n2.DISPLAY ALL BOOKS \n3.INCREASE THE STOCK OF A PARTICULAR BOOK \n4.READ MESSAGES  \n5.DELETE ALL MESSAGES \n6.EXIT");
         }
              ch=sc.nextInt();
         }
    
     }
}
