
package storage;


import java.util.ArrayList;


public class Data {
      ArrayList<String> data=new ArrayList<>();
    
   
    
    public void displayData(){
        
        System.out.println("STORED DATA:\n ");
       
        for(String d:data){
            System.out.println(d);
        }
    }
    
}
