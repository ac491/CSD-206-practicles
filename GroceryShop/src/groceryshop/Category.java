
package groceryshop;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Category {
    static ArrayList<Items> items=new ArrayList<>();
    String categoryName;
    double GSTAmount;
    String categoryId;
    
    Category(String category,double GST,String Id){
        this.categoryName=category;
        this.GSTAmount=GST;
        this.categoryId=Id;
    }
    
    Category(){
        //empty constructor
    }

   
    public void addItems() throws IOException{
        Scanner sc=new Scanner(System.in);
         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
     String itemId,name,category,categoryId;
     double price;
    System.out.println("ENTER THE ITEM ID:");
    itemId=br.readLine();
    System.out.println("ENTER THE ITEM NAME:");
    name=br.readLine();
    System.out.println("ENTER THE PRICE OF THE ITEM");
    price=sc.nextDouble();
    System.out.println("ENTER THE ITEM CATEGORY:");
    category=br.readLine();
    System.out.println("ENTER THE CATEGORY ID:");
    categoryId=br.readLine();
    Items item=new Items(itemId,name,price,category,categoryId);
    items.add(item);
    
    }
    
   
    
    //public static double getGST(String catName){
    //    if(catName.equalsIgnoreCase(this.categoryName)){
    //        return GSTAmount;
    //    }
    //    return 0;
   // }
  
}
