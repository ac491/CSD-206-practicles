
package groceryshop;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Customers {
     String custId;
    String custName;
    String custAddress;
    String custPhoneNo;
    static int custNo=1;
   // ArrayList<Items> cart=new ArrayList<>();
     CartItems cart;
  //  ArrayList<Double> GSTPrices=new ArrayList<>();
    Scanner sc=new Scanner(System.in);
   // ArrayList<ArrayList<Items>> bills=new ArrayList<>();           //to save the cart of each customer
    // ArrayList<ArrayList<String>> bills=new ArrayList<>();
     ArrayList<Bill> bills=new ArrayList<>();
    
     
     Customers(){
        cart=new CartItems();
     }
     
    public void getCustData(){
      
        custId="C"+custNo;
        custNo++;
        System.out.println("ENTER YOUR NAME");
        custName=sc.nextLine();
        System.out.println("ENTER YOUR ADDRESS");
        custAddress=sc.nextLine();
        System.out.println("ENTER YOUR PHONE NO");
        custPhoneNo=sc.nextLine();
        
        System.out.println("CUSTOMER RECORD SUCCESSFULLY CREATED");
        System.out.println("YOUR CUSTOMER ID IS:"+custId);
        
    }
    
    public void selectItems() throws IOException{
         //  ArrayList<String> bill=new ArrayList<>();
         
           cart=new CartItems();
       // GSTPrices.clear();
        
          Bill b=new Bill();
        b.getdata(custId,custName,custAddress,custPhoneNo);
        System.out.println("WELCOME "+this.custName);
        String ch="y";
        System.out.println("LIST OF AVAILABLE ITEMS:");
        GroceryShop.displayItems();
        
         while(ch.equals("y")||ch.equals("Y")){
               //String Id;
               //System.out.println("ENTER THE ITEM ID OF THE ITEM TO BE ADDED TO CART");
               //Id=sc.next();
              // Items item=new Items();
               //item=getItem(Id);
               
              // if(item == null){    //for the case if item id does not exists
              //     continue;
             //  }
               
             //  System.out.println("ENTER THE QUANTITY");
             //  item.quantity=sc.nextInt();
             //  cart.add(item);
            // CartItems cI=new CartItems();
             cart.getData();
             
             
               System.out.println("DO YOU WISH TO BUY MORE ITEMS");
               ch=sc.next();
         }
         b.setCart(cart);
                          //here give the cart to the bill class
         System.out.println("YOUR CART:");
         int a=1;
         //int count=0;
          System.out.format("%-16s%-16s%-16s%-16s%-25s\n\n","S.NO","ITEM ID","ITEM NAME","CATEGORY", "PRICE (INCLUDING GST) PER PACKET");
          for(Items ci:cart.item){
               System.out.format("%-16s%-16s%-16s%-16s%-16s\n",a,ci.itemId,ci.itemName,ci.category,(ci.price+(ci.price*b.getGST(ci.categoryId))/100));
     //          GSTPrices.add(ci.quantity*(ci.item.price+(ci.item.price*b.getGST(ci.item.category))/100));
             //  bill.add(a+" "+i.itemId+" "+i.itemName+" "+i.category+" "+(i.price+(i.price*getGST(i.categoryId))/100)+" ");
               a++;
               //count++;
          }
          // bills.add(cart); //add the cart arraylist
         // bills.add(bill);
         bills.add(b);
        
    }
    
   /* public  Items getItem(String id){
        
        int flag=0;
        int index = 0;
        
        for(Category c1:GroceryShop.categories){
            for(Items i:c1.items){
                if(id.equalsIgnoreCase(i.itemId)){
                    flag=1;
                    return i;
                   
                }
            }
        }
        if(flag==0){
            System.out.println("ITEM ID DOES NOT EXISTS");
            return null;
        }
        return null;
    }*/
    
    /* public double getGST(String catId){
        for(Category c1:GroceryShop.categories){
            if(c1.categoryId.equalsIgnoreCase(catId)){
                return c1.GSTAmount;
            }
        }
        return 0;
     }*/
   
    /* public void displayBill(){
         double bill=0;
         ArrayList<String> billList=new ArrayList<>();
         System.out.printf("\n\n BILL: \n\n");
         System.out.println("CUSTOMER ID: "+this.custId);
         System.out.println("NAME: "+this.custName);
         System.out.println("ADDRESS: "+this.custAddress);
         System.out.println("PHONE NO :"+this.custPhoneNo);
         
         System.out.format("%-16s%-16s%-16s%-16s%-25s%-30s%-16s%-16s\n\n","S.NO","ITEM ID","ITEM NAME","CATEGORY", "PRICE (INCLUDING GST)","DETAILS","QUANTITY","TOTAL");
         int a=1;
        // double sum=0;
          for(CartItems ci:cart){
               System.out.format("%-16s%-16s%-16s%-16s%-25s%-30s%-16s%-16s\n",a,ci.item.itemId,ci.item.itemName,ci.item.category,(ci.item.price+(ci.item.price*getGST(ci.item.categoryId))/100),ci.item.price+"*("+getGST(ci.item.categoryId)/2+"% CGST+"+getGST(ci.item.categoryId)/2+"% SGST)",ci.quantity,GSTPrices.get(a-1));
               billList.add(a+"@"+ci.item.itemId+"@"+ci.item.itemName+"@"+ci.item.category+"@"+(ci.item.price+(ci.item.price*getGST(ci.item.categoryId))/100)+"@"+ci.quantity+"@"+GSTPrices.get(a-1));
             //  sum+=GSTPrices.get(a-1);
               a++;
          }
         //  billList.add(sum+"");
          System.out.format("%-16s%-16s%-16s%-16s%-25s%-30s%-16s%-16s\n\n","","","","","","","---------------","-------------");
          for(Double g1:GSTPrices){
              bill+=g1;
             
          }
           System.out.format("%-16s%-16s%-16s%-16s%-25s%-30s%-16s%-16s\n\n","","","","","","", "TOTAL:",bill);
           bills.add(billList);
     } */
   
}
