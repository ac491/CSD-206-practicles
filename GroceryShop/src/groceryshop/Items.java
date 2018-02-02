
package groceryshop;

public class Items {
   
    String itemId;
    String itemName;
    double price;
    int quantity;
    String category;
    String categoryId;
    
     Items(){
         //EMPTY CONSTRUCTOR
     }
     
     Items(String itemId,String itemName,double price,String category,String categoryId){
        this.itemId=itemId;
        this.itemName=itemName;
        this.price=price;
        this.category=category;
        this.categoryId=categoryId;
    }
     
    // public String retItemId(){
     //    return itemId;
    // }
    
     
    
}
