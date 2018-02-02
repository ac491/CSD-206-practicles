
package groceryshop;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;


public class BillGeneration { 
    
      static ArrayList<Customers> custList=new ArrayList<>();
         Scanner sc =new Scanner(System.in);
    
    public void generateBill() throws IOException{
           
         int ch;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String choice;
        System.out.println("WELCOME TO ONLINE GROCERY SHOPPING");
        System.out.println("LOGIN FOR: 1.EXISTING CUSTOMER  2.NEW CUSTOMER 3.EXIT");
        ch=sc.nextInt();
        
     while(ch!=3){
        
        switch(ch){
            case 1: oldCust();
                   break;
                     
                
            case 2:  Customers c=new Customers();
                     c.getCustData();
                     c.selectItems();
                     System.out.println("WOULD YOU LIKE TO CONTINUE TO GENERATE THE BILL?(Y/N)");
                     choice=br.readLine();
                     if(!choice.equalsIgnoreCase("y")){
                         custList.add(c);
                         break;
                     }
                     c.bills.get(c.bills.size()-1).displayBill();
                     custList.add(c);
                     break;
                     
        }
          
        System.out.println("WELCOME TO ONLINE GROCERY SHOPPING");
        System.out.println("LOGIN FOR: 1.EXISTING CUSTOMER  2.NEW CUSTOMER 3.EXIT");
        ch=sc.nextInt();
    }
   }
    
    public void oldCust() throws IOException{
        String[] prevBill;
        String id;
           int index=0,flag=0;
        System.out.println("ENTER YOUR CUSTOMER ID");
                    id=sc.next();
                   for (Customers custList1 : custList) {
                       if(id.equalsIgnoreCase(custList1.custId)){
                           index=custList.indexOf(custList1);
                           flag=1;
                           break;
                       }
                   }
                   if(flag==0){
                       System.out.println("CUSTOMER ID DOES NOT EXIST");
                   }
                   else{
                       int ch,a=1;
                       System.out.println(" ENTER YOUR CHOICE: 1.VIEW PREVIOUS BILLS  2.BUY MORE ITEMS ");
                       ch=sc.nextInt();
                       switch(ch){
                           case 1:
                               int m=0;
                              
                               for(Bill b:custList.get(index).bills){
                                   b.displayBill();
                               }
                               
                               
                               
                  // System.out.format("%-16s%-16s%-16s%-16s%-40s%-16s\n\n","S.NO","ITEM ID","ITEM NAME","CATEGORY", "PRICE (INCLUDING GST) PER PACKET","QUANTITY");
                             /*  for(Bill bill:custList.get(index).bills)
                               {   System.out.println("BILL: "+(m+1));
                                   m++;
                                   System.out.format("%-16s%-16s%-16s%-16s%-16s%-16s%-16s\n\n","S.NO","ITEM ID","ITEM NAME","CATEGORY", "PRICE ","QUANTITY","TOTAL");
                                        // int p=0;
                                   for(int j=0;j<bill.size();j++){
                                   prevBill=bill.get(j).split("@");
                                   for(String b1:prevBill){
                                       
                                       System.out.printf("%-16s",b1);
                                   }
                             //      System.out.printf("%-16s%-16s%-16s%-16s%-16s%-16s%-16s\n","","","","","","","-------------");
                              //     System.out.printf("%-16s%-16s%-16s%-16s%-16s%-16s%-16s\n","","","","","","TOTAL:",bill.get(j));
                                   System.out.printf("\n");
                                 }
                                   System.out.printf("\n");
                             //  for( Items i:custList.get(index).bills.get(m)){
                                    //for(Items i:item){ 
                                    
                                   // System.out.println(p);
                                   // p++;
                                 //   System.out.format("%-16s%-16s%-16s%-16s%-45s%-16s\n",a,i.itemId,i.itemName,i.category,(i.price+(i.price*custList.get(index).getGST(i.category))/100),i.quantity);
                                 //   a++;
                                      //}
                              }*/
                               break;
                           case 2:custList.get(index).selectItems();
                                  custList.get(index).bills.get(custList.get(index).bills.size()-1).displayBill();
                                  break;
                       }
                       }
                      // custList.get(index).selectItems();
                      // custList.get(index).displayBill();
                   
                   
    }
}
