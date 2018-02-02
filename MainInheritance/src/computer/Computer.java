
package computer;

import storage.Internal;


public class Computer {
    private String processor;
    private String RAM;
    private String motherBoard;
    
    
    
    public String deviceId;
    Internal i;
    
    
    Computer(String processor,String motherBoard,String RAM){
        this.processor=processor;
        this.motherBoard=motherBoard;
        this.RAM=RAM;
        i=new Internal();
    }
    
     void printData(){
        
        System.out.printf("\n\n");
        System.out.println(" DETAILS:  ");
        System.out.println("DEVICE ID: "+this.deviceId);
        System.out.println("PROCESSOR: "+this.processor);
        System.out.println("RAM "+this.RAM);
        System.out.println("MOTHERBOARD "+this.motherBoard);
        System.out.printf("\n\n");
    }
     
    
}
