
package miniproject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class Order implements Serializable {
    Customers customer;
    double salesTax;
    double shippingFee;
    double total;
    String paymentMethod;
    String billId;
    String date;
    String time;
    static int billno=1;
    
    Order(Customers c){
        billno=readBillno();
        if(billno==0){
            billno=1;
        }
        this.customer=c;
        this.salesTax=Admin.SALESTAX;
        this.shippingFee=Admin.SHIPPINGFEE;
        billId="B0"+billno;
        billno++;
        writeBillno(billno);
        total=0;
    }
    public void getSysDate(){
            date = new SimpleDateFormat("yyyy/MM/dd").format(Calendar.getInstance().getTime());
            time=new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
            
         }
    public void process(){
         if(customer.cart.itemOrder.isEmpty()){
            System.out.println("YOU DO NOT HAVE ANY ITEMS IN CART!");
            return;
        }
        getSysDate();
        System.out.println("BILL: \n");
        System.out.println("DATE: "+date);
        System.out.println("TIME: "+time);
        customer.displayDetails();
        int a=1;
        //customer.cart.itemOrder=ShoppingCart.readOrders();
                System.out.format("%-16s%-16s%-40s%-25s%-25s%-30s%-16s%-16s%-16s%-16s\n\n","S.NO","ISBN","TITLE","AUTHOR", "PUBLISHER","YEAR PUBLISHED","VOLUME","EDITION","PRICE","QUANTITY");
        for(ItemOrder i:customer.cart.itemOrder){
           
            Book b=(Book)i.item;
            System.out.format("%-16s%-16s%-40s%-25s%-25s%-30s%-16s%-16s%-16s%-16s\n\n",a,i.item.ISBN,i.item.title,b.author,i.item.publisher,i.item.yearPublished,b.volume,b.edition,i.item.price,i.quantity);
            a++;
            total+=i.quantity*i.item.price;
        }
         System.out.format("%-16s%-16s%-40s%-25s%-25s%-30s%-16s%-16s%-16s%-16s\n\n","-","-","-","-", "-","-","-","-","-","-");
         System.out.format("%-16s%-16s%-40s%-25s%-25s%-30s%-16s%-16s%-16s%-16s\n\n","","","","", "","","","","TOTAL:",total);
         System.out.format("%-16s%-16s%-40s%-25s%-25s%-30s%-16s%-16s%-16s%-16s\n\n","-","-","-","-", "-","-","-","-","-","-");
         System.out.println("TOTAL=TOTAL+SALESTAX+SHIPPING FEE");
         System.out.println("TOTAL="+total+"+ ("+total+"*"+salesTax+")/100"+"+"+shippingFee);
         System.out.println("TOTAL: "+(total+(total*salesTax)/100+shippingFee));
        // Mail.sendMail(customer,total);
         
    }
    public static void writeBillno(int bill){
        OutputStream ops = null;
		ObjectOutputStream objOps = null;
		try {
			ops = new FileOutputStream("billno.txt",false);//to overrride
			objOps = new ObjectOutputStream(ops);
			objOps.writeObject(bill);
			objOps.flush();
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
                        System.out.println(e);
		} 
		
     }
      
       public static int readBillno(){
             InputStream fileIs = null;
        ObjectInputStream objIs = null;
        try {
            fileIs = new FileInputStream("billno.txt");
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
