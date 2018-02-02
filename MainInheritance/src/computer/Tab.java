/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package computer;

/**
 *
 * @author Sneha
 */
public class Tab extends Computer implements Charger {
    
        Tab(String processor,String motherBoard,String RAM){
        super(processor,motherBoard,RAM);
    }
        
         @Override
    public void charge() {
       System.out.println("CHARGING TAB...");
    }
    
    
}
