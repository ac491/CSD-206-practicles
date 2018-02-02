
package groceryshop;

import java.text.SimpleDateFormat;
import java.util.Calendar;



public class Bill {
String billId;
String date;
String time;
static int billNo=1;
String custName;
String custId;
String custAddress;
String custPhoneNumber;
CartItems cartItems;
double totalAmount=0;

         Bill(){
              billId="B"+billNo;
              billNo++;
         }
         public void getSysDate(){
            date = new SimpleDateFormat("yyyy/MM/dd").format(Calendar.getInstance().getTime());
            time=new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
            
         }
         
        public void setCart(CartItems cartItems){
            this.cartItems=cartItems;
           
        }

        
    void getdata(String custId, String custName, String custAddress, String custPhoneNo) {
       this.custId=custId;
       this.custName=custName;
       this.custAddress=custAddress;
       this.custPhoneNumber=custPhoneNo;
    }
    
     public void displayBill(){
         totalAmount=0;
         getSysDate();
         System.out.printf("\n\n BILL: \n\n");
         System.out.println("BILL ID: "+this.billId);
         System.out.println("DATE: "+this.date);
         System.out.println("TIME: "+this.time);
         System.out.println("CUSTOMER ID: "+this.custId);
         System.out.println("NAME: "+this.custName);
         System.out.println("ADDRESS: "+this.custAddress);
         System.out.println("PHONE NO :"+this.custPhoneNumber);
         
         System.out.format("%-16s%-16s%-16s%-16s%-25s%-30s%-16s%-16s\n\n","S.NO","ITEM ID","ITEM NAME","CATEGORY", "PRICE (INCLUDING GST)","DETAILS","QUANTITY","TOTAL");
         int a=1;
       
          for(Items ci:cartItems.item){
               System.out.format("%-16s%-16s%-16s%-16s%-25s%-30s%-16s%-16s\n",a,ci.itemId,ci.itemName,ci.category,(ci.price+(ci.price*getGST(ci.categoryId))/100),ci.price+"*("+getGST(ci.categoryId)/2+"% CGST+"+getGST(ci.categoryId)/2+"% SGST)",ci.quantity,(ci.price+(ci.price*getGST(ci.categoryId))/100)*ci.quantity);
              totalAmount+=(ci.price+(ci.price*getGST(ci.categoryId))/100)*ci.quantity;
               a++;
          }
         
          System.out.format("%-16s%-16s%-16s%-16s%-25s%-30s%-16s%-16s\n\n","","","","","","","---------------","-------------");
          System.out.format("%-16s%-16s%-16s%-16s%-25s%-30s%-16s%-16s\n\n","","","","","","", "TOTAL:",totalAmount);
          System.out.printf("\n\n\n\n");
           
     } 
     
     public double getGST(String catId){
         int flag=1;
         for(Category c1:GroceryShop.categories){
             if(c1.categoryId.equalsIgnoreCase(catId)){
                 flag=0;
                 return c1.GSTAmount;
             }
         }
         if(flag==1){
            System.out.println("CATEGORY NOT FOUND\n");
           
         }
         return 0;
     }
   
}
