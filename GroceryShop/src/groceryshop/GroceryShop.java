
package groceryshop;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;


public class GroceryShop {
    
     
     static ArrayList<Category> categories=new ArrayList<>();
   
    public static void main(String[] args) throws IOException {
        
                      Category c1=new Category("Pulses",5.0,"P");
                      Category c2=new Category("Grains",5.0,"G");
                      Category c3=new Category("Snacks",12.0,"S");
                      Items i1=new Items("P01","Urad Dal",114,"Pulses","P");
                      Items i2=new Items("P02","Rajma",149,"Pulses","P");
                      Items i3=new Items("G01","Rice",60,"Grains","G");
                      Items i4=new Items("G02","Wheat",50,"Grains","G");
                      Items i5=new Items("S01","Chips",20,"Snacks","S");
                      Items i6=new Items("S02","Cold Drinks",35,"Snacks","S");
                      Category.items.add(i1);
                      Category.items.add(i2);
                      Category.items.add(i3);
                      Category.items.add(i4);
                      Category.items.add(i5);
                      Category.items.add(i6);
                     categories.add(c1);
                     categories.add(c2);
                     categories.add(c3);
                     
        Scanner sc =new Scanner(System.in);
        int ch;
        System.out.println("SELECT YOUR LOGIN CHOICE: 1.ADMIN  2.CUSTOMER 3.EXIT");
        ch=sc.nextInt();
        while(ch!=3){
          switch(ch){
              case 1: addNewItems();
                     //displayItems();
                     break;
              case 2: BillGeneration bill=new BillGeneration();
                     bill.generateBill();
          }
          System.out.println("SELECT YOUR LOGIN CHOICE: 1.ADMIN  2.CUSTOMER 3.EXIT");
          ch=sc.nextInt();
        }   
    }
    
    public static void displayItems(){
        int a=1;
          System.out.format("%-16s%-16s%-16s%-16s%-25s\n\n","S.NO","ITEM ID","ITEM NAME","CATEGORY", "PRICE (INCLUDING GST)");
        //  for(Category c1:categories){
              for(Items item:Category.items){
                 for(Category c1:categories){
                     if(c1.categoryId.equalsIgnoreCase(item.categoryId)){
                           System.out.format("%-16s%-16s%-16s%-16s%-16s\n",a,item.itemId,item.itemName,item.category,(item.price+(item.price*c1.GSTAmount)/100));
                           a++;
                     }     
                  }
             }         
       // }
    }
    
    public static void addNewItems() throws IOException{
        int ch,flag=0;
        String id;
        Scanner sc=new Scanner(System.in);
         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("ENTER YOUR CHOICE: 1.ADD ITEMS TO NEW CATEGORY  2.ADD ITEMS TO EXISTING CATEGORY 3.DELETE A CATEGORY 4.DELETE AN ITEM FROM A CATEGORY 5.Exit");
        ch=sc.nextInt();
        
        while(ch!=5){
           switch(ch){
               case 1: Category c=new Category();
                       System.out.println("ENTER NEW CATEGORY NAME ");
                       c.categoryName=br.readLine();
                       System.out.println("ENTER CATEGORY ID");
                       c.categoryId=br.readLine();
                       System.out.println("ENTER GST AMOUNT");
                       c.GSTAmount=sc.nextDouble();
                       c.addItems();
                       categories.add(c);
                       break;
                       
               case 2: System.out.println("ENTER CATEGORY ID:");
                       id=sc.next();
                       for(Category c1:categories){
                           if(c1.categoryId.equalsIgnoreCase(id)){
                               c1.addItems();
                               flag=1;
                               break;
                           }
                       }
                       if(flag==0){
                           System.out.println("CATEGORY NOT FOUND");
                       }
                       break;
                       
               case 3:   System.out.println("ENTER CATEGORY ID:");
                       id=sc.next();
                       for(Category c1:categories){
                           if(c1.categoryId.equalsIgnoreCase(id)){
                               System.out.println("CATEGORY "+c1.categoryName+" DELETED");
                               categories.remove(categories.indexOf(c1));
                               flag=1;
                               break;
                           }
                       }
                       if(flag==0){
                           System.out.println("CATEGORY NOT FOUND");
                       }
                       break;    
                       
               case 4:   System.out.println("ENTER CATEGORY ID:");
                       id=sc.next();
                       System.out.println("ENTER ITEM ID");
                       String itemId=sc.next();
                       for(Category c1:categories){
                           if(c1.categoryId.equalsIgnoreCase(id)){
                               for(Items i:c1.items){
                                   if(i.itemId.equalsIgnoreCase(itemId)){
                                       System.out.println("ITEM "+i.itemName+" DELETED");
                                          c1.items.remove(i);
                                          flag=1;
                                          break; 
                                   }
                               }
                             
                           }
                       }
                       if(flag==0){
                           System.out.println("ITEM NOT FOUND");
                       }
                       break;    
                            
           }
          System.out.println("ENTER YOUR CHOICE: 1.ADD ITEMS TO NEW CATEGORY  2.ADD ITEMS TO EXISTING CATEGORY 3. DELETE A CATEGORY 4.DELETE AN ITEM FROM A CATEGORY 5.Exit");
           ch=sc.nextInt();
        } 
    }
    
}
