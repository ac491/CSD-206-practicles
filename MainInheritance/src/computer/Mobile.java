
package computer;


public class Mobile extends Computer implements Charger{
    
        Mobile(String processor,String motherBoard,String RAM){
        super(processor,motherBoard,RAM);
    }
        
         @Override
    public void charge() {
       System.out.println("CHARGING MOBILE...");
    }
    
}
