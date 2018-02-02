/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniproject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;





class OnlineBookStore implements SearchResult {
     ArrayList<Customers> custList;
     ArrayList<Item> items;
     ArrayList<Order> orders;
    
    static int loggedin=0;
    Customers loggedinCust=null;
    OnlineBookStore(){
        custList=new ArrayList<>();
        items=new ArrayList<>();
        orders=new ArrayList<>();
    }
    public void menu() throws IOException{
        int ch;
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        System.out.printf("\n");
        System.out.println("WELCOME TO ONLINE BOOK SHOPPING\n");
      System.out.println("SELECT YOUR CHOICE: \n 1.LOGIN \n2.SEARCH FOR BOOKS \n3.DISPLAY ALL BOOKS \n4.ADD ITEMS TO CART  \n5.GENERATE BILL \n6.VIEW PREVIOUS BILLS  \n7.LOGOUT \n8.GIVE FEEDBACK \n9.EXIT");
        try{ch=Integer.parseInt(br.readLine());}
        catch(InputMismatchException e){
            System.out.println("PLEASE ENTER AN INTEGER VALUE");
            ch=Integer.parseInt(br.readLine());
        }
        while(ch!=9){
            switch(ch){
                
                case 1:login();
                       break;
                case 2:search();
                       break;
                case 3:displayAllItems();
                      break;
                case 4:if(loggedin==1&&loggedinCust!=null){
                         loggedinCust.addCart();
                         }
                        else{
                            System.out.println("PLEASE LOG IN FIRST!");
                            login();
                          }
                        break;
                case 5: if(loggedin==1&&loggedinCust!=null){
                          Order o=new Order(loggedinCust);
                          orders=readOrders();
                          if(orders==null){
                              orders=new ArrayList<>();
                          }
                          System.out.println("ENTER MODE OF PAYMENT(COD/NET BANKING)");
                          o.paymentMethod=br.readLine();
                          o.process();
                          Mail.sendMail(loggedinCust,o.total,o.paymentMethod);
                          orders.add(o);
                          writeOrders(orders);
                          recommend(o.customer.cart.itemOrder.get(0).item.ISBN);
                          loggedinCust.cart.clearCart();  
                         }
                        else{
                            System.out.println("PLEASE LOG IN FIRST!");
                            login();
                          }
                        break;
                case 6:viewPreviousBills();
                       break;
                case 7:logout();
                       break;
                case 8:System.out.println("PLEASE GIVE YOUR FEEDBACK!!");
                        String message=br.readLine();
                        ArrayList<String> messages=Admin.readMessages();
                        if(messages==null){
                            messages=new ArrayList<>();
                        }
                        messages.add(message);
                        Admin.newMessage=1;
                        Admin.writeMessages(messages);
                        break;
                default:System.out.println("WRONG CHOICE!!");
                
            }
            System.out.printf("\n\n");
           System.out.println("SELECT YOUR CHOICE: \n 1.LOGIN \n2.SEARCH FOR BOOKS \n3.DISPLAY ALL BOOKS \n4.ADD ITEMS TO CART  \n5.GENERATE BILL \n6.VIEW PREVIOUS BILLS  \n7.LOGOUT \n8.GIVE FEEDBACK \n9.EXIT");            
           try{ch=Integer.parseInt(br.readLine());}
           catch(InputMismatchException e){
            System.out.println("PLEASE ENTER AN INTEGER VALUE");
            ch=Integer.parseInt(br.readLine());
        }
        
        }
        logout();
    
   }
   
    public void viewPreviousBills() throws IOException{
        if(loggedin==0&&loggedinCust==null){
            System.out.println("LOG IN FIRST");
            login();
        }
        orders=readOrders();
        if(orders==null){
            System.out.println("THERE ARE NO RECORDS OF PREVIOUS BILLS");
            orders=new ArrayList<>();
            return;
        }
        int flag=0,a=1;
        for(Order o:orders){
            if(o.customer.customerId.equalsIgnoreCase(loggedinCust.customerId)){
                System.out.printf("\n");
                System.out.println("ORDER: "+a);
                o.process();
                System.out.printf("\n");
                a++;
                flag=1;
            }
        }
        if(flag==0){
            System.out.println("NO ORDER RECORDS WERE FOUND!");
        }
    }
    
    
    
    
    public void login() throws IOException{
        int ch;
        Scanner sc=new Scanner(System.in);
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        if(loggedin==1){
            System.out.println(" ALREADY LOGGED IN");
            return;
        }
      System.out.println("SELECT YOUR CHOICE: 1.NEW CUSTOMER  2.EXISTING CUSTOMER");
     try{ ch=sc.nextInt();}
     catch(InputMismatchException e){
         System.out.println("PLEASE ENTER AN INTEGER VALUE(1-2)");
         ch=Integer.parseInt(br.readLine());
     }
      switch(ch){
          case 1:Customers c=new Customers();
                 c.getData();
                 System.out.println("YOUR USER ID TO LOG IN IS: "+c.customerId);
                 System.out.println("ENTER YOUR PASSWORD FOR YOUR ACCOUNT:");
                 c.password=br.readLine();
        {
            custList=Customers.readFile();
        }
        if(custList==null){
            custList=new ArrayList<>();
        }
                 custList.add(c);
                 loggedinCust=c;
                 Customers.writeFile(custList);
                 System.out.println("SUCCESSFULLY CREATED ACCOUNT AND LOGGED YOU IN!!");
                 loggedin=1;
                 break;
                 
          case 2:{
              custList=Customers.readFile();
                 }
          if(custList==null){
              custList=new ArrayList<>();
          }
                int flag=0;
                 System.out.println("ENTER YOUR CUSTOMER ID:");
                 String id=br.readLine();
                 for(Customers cust:custList){
                     if(id.equalsIgnoreCase(cust.customerId)){
                         flag=1;
                         System.out.println("ENTER YOUR PASSWORD");
                         String pass=br.readLine();
                         if(pass.equals(cust.password)){
                             System.out.println("SUCCESSFULLY LOGGED IN");
                             loggedin=1;
                             loggedinCust=cust;
                             break;
                         }
                         else{
                             System.out.println("INCORRECT PASSWORD!!");
                             break;
                         }
                     }
                 }
                 if(flag==0){
                    System.out.println("CUSTOMER ID DOES NOT EXIST"); 
                 }
         }
      System.out.printf("\n");
    }
    
    @Override
    public void search() throws IOException{
        int a=1;
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        System.out.println("ENTER THE NAME (OR PART OF THE NAME) OF THE BOOK OR AUTHOR NAME");
        String name=br.readLine();
        System.out.println("BOOK(S) MATCHING YOUR SEARCH QUERY:");
        items=readItems();
        if(items==null){
            items=new ArrayList<>();
        }
        int flag=0;
        for(Item i:items){
            Book b=(Book)i;
            if(checkChar(i.title,name)==1 || checkChar(b.author,name)==1){
                System.out.printf("\n");
                System.out.println("BOOK: "+a);
                i.display();
                System.out.printf("\n");
                a++;
                flag=1;
            }
        }
        if(flag==0){
          /*  try {
                SpellCheck.check(name);
            } catch (NoSuchMethodException ex) {
                Logger.getLogger(OnlineBookStore.class.getName()).log(Level.SEVERE, null, ex);
            }*/
        }
    }
    
    public int checkChar(String name,String user){
        name=name.toLowerCase();
        user=user.toLowerCase();
    Set<Character> n = new HashSet<>();
    Set<Character> u = new HashSet<>();
         for (char c : name.toCharArray()) {
          n.add(c);
         }
          for (char c : user.toCharArray()) {
          u.add(c);
         }
          
          if(n.containsAll(u)||u.containsAll(n)){
              return 1;
          }
          return 0;
    }
    
    
    public void displayAllItems(){
        System.out.println("BOOKS: ");
        int a=1;
        items=readItems();
        if(items==null){
            items=new ArrayList<>();
        }
        if(items.isEmpty()){
            System.out.println("THERE ARE CURRENTLY NO ITEMS");
        }
        else{
           for(Item i:items){
               System.out.printf("\n");
              System.out.println("BOOK "+a);
              i.display();
               System.out.printf("\n");
              a++;
            }
        }
    }
    
    public void logout(){
        if(loggedin==1){
            System.out.println("SUCCESSFULLY LOGGED OUT");
            loggedin=0;
            loggedinCust=null;
            Customers.writeFile(custList);
        }
        else{
            System.out.println(" YOU ARE NOT LOGGED IN");
        }
    }
    public static void writeItems(ArrayList<Item> i){
        OutputStream ops = null;
		ObjectOutputStream objOps = null;
		try {
			ops = new FileOutputStream("item.txt",false);//to overrride
			objOps = new ObjectOutputStream(ops);
			objOps.writeObject(i);
			objOps.flush();
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
                        System.out.println(e);
		} 
		
     }
    public static void writeOrders(ArrayList<Order> o){
        OutputStream ops = null;
		ObjectOutputStream objOps = null;
		try {
			ops = new FileOutputStream("orders.txt",false);//to overrride
			objOps = new ObjectOutputStream(ops);
			objOps.writeObject(o);
			objOps.flush();
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
                        System.out.println(e);
		} 
		
     }
    public static ArrayList<Item> readItems()  {
             InputStream fileIs = null;
        ObjectInputStream objIs = null;
        try {
            fileIs = new FileInputStream("item.txt");
            objIs = new ObjectInputStream(fileIs);
            ArrayList<Item> a = (ArrayList<Item>) objIs.readObject();
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
     public static ArrayList<Order> readOrders()  {
             InputStream fileIs = null;
        ObjectInputStream objIs = null;
        try {
            fileIs = new FileInputStream("orders.txt");
            objIs = new ObjectInputStream(fileIs);
            ArrayList<Order> o = (ArrayList<Order>) objIs.readObject();
            return o;
            
        } catch (FileNotFoundException e) {
           System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        
        }
             return null;
     }
     
     public void recommend(String ISBN) throws IOException{
         
         System.out.printf("\n");
         System.out.println("USERS ALSO BOUGHT: ");
         BufferedReader br=null;
         try{
             String line,genre = null;
             br=new BufferedReader(new FileReader("books.txt"));
             while((line=br.readLine())!=null){
                 String[] tokens=line.split(",");
                 if(tokens[0].equalsIgnoreCase(ISBN)){
                     genre=tokens[1];
                    // System.out.println(genre);
                     break;
                   }
                 }
             
              for(Item i:items){
                  if(!i.ISBN.equalsIgnoreCase(ISBN)){
                       br=new BufferedReader(new FileReader("C:\\Users\\sneha\\Documents\\NetBeansProjects\\miniproject\\books.txt"));
                         while((line=br.readLine())!=null){
                              String[] tokens=line.split(",");
                              if(i.ISBN.equalsIgnoreCase(tokens[0])){
                                  if(tokens[1].equalsIgnoreCase(genre)){
                                      System.out.printf("\n");
                                      System.out.println("BOOK:");
                                      i.display();
                                      System.out.printf("\n");
                                      break;
                                  }
                              }
                           }
                  }
           }
             
         } catch (FileNotFoundException ex) {
                System.out.println(ex);
        }
         
     }
    
}
