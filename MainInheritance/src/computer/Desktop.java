/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package computer;

import java.util.ArrayList;
import storage.External;
import storage.Removable;


public class Desktop extends Computer implements Charger {
    
     int numOfPorts=5;
     static ArrayList<External> connectedExt;
    static ArrayList<Removable> connectedUSBs;
    
    Desktop(String processor,String motherBoard,String RAM){
        super(processor,motherBoard,RAM);
        connectedExt=new ArrayList<>();
        connectedUSBs=new ArrayList<>();
    }

     @Override
    public void charge() {
       System.out.println("CHARGING DESKTOP...");
    }

    
}
