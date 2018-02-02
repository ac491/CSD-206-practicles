
package miniproject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Customers implements Serializable {
   
    public String customerId;
    public String password;
    public String name;
    static int user=1;
    String emailId;
    Address shippingAddress;
    Address billingAddress;
    ShoppingCart cart;
    
    Customers(){
        user=readCust();
        if(user==0){
            user=1;
        }
        customerId="U0"+user;
        user++;
        writeCust(user);
        shippingAddress=new Address();
        billingAddress=new Address();
        cart=new ShoppingCart();
    }
    
    public void getData() throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        System.out.println("ENTER YOUR NAME:");
        name=br.readLine();
        System.out.println("ENTER YOUR EMAIL ID:");
        emailId=br.readLine();
        System.out.println("ENTER YOUR RESIDENTIAL ADDRESS DETAILS");
        billingAddress.getAddress();
        System.out.println("ENTER YOUR SHIPPING ADDRESS");
        shippingAddress.getAddress();
    }
    public void addCart() throws IOException{
        cart.cart();
    }
    
    
    
    public void displayDetails(){
        System.out.println("CUSTOMER ID: "+customerId);
        System.out.println("CUSTOMER NAME: "+name);
        System.out.println("ADDRESS: ");
        billingAddress.display();
        System.out.println("\n SHIPPING ADDRESS: ");
        shippingAddress.display();
    }
    
    
    
    
    
    
 public static void writeFile(ArrayList<Customers> cust){
        OutputStream ops = null;
		ObjectOutputStream objOps = null;
		try {
			ops = new FileOutputStream("users.txt",false);//to overrride
			objOps = new ObjectOutputStream(ops);
			objOps.writeObject(cust);
			objOps.flush();
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
                        System.out.println(e);
		} 
		
     }
      
       public static ArrayList<Customers> readFile()  {
             InputStream fileIs = null;
        ObjectInputStream objIs = null;
        try {
            fileIs = new FileInputStream("users.txt");
            objIs = new ObjectInputStream(fileIs);
            ArrayList<Customers> c = (ArrayList<Customers>) objIs.readObject();
            return c;
            
        } catch (FileNotFoundException e) {
           System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        
        }
             return null;
     }
public static void writeCust(int cust){
        OutputStream ops = null;
		ObjectOutputStream objOps = null;
		try {
			ops = new FileOutputStream("custno.txt",false);//to overrride
			objOps = new ObjectOutputStream(ops);
			objOps.writeObject(cust);
			objOps.flush();
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
                        System.out.println(e);
		} 
		
     }
      
       public static int readCust(){
             InputStream fileIs = null;
        ObjectInputStream objIs = null;
        try {
            fileIs = new FileInputStream("custno.txt");
            objIs = new ObjectInputStream(fileIs);
            int c = (int) objIs.readObject();
            return c;
            
        } catch (FileNotFoundException e) {
           System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        
        }
             return 0;
     }

 
}
