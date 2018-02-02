
package storage;

//import computer.Computer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class ExternalStorageDevices {
    
   public static ArrayList<External> external=new ArrayList<>();
   public static ArrayList<Integer> externalFlag=new ArrayList<>();
   public static ArrayList<Removable> USB=new ArrayList<>();
   public static ArrayList<Integer> USBFlag=new ArrayList<>();
   public static String connectedDev;
  
   
    
    
    static public External addExternal() throws IOException{
         BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
         External e=new External();
         System.out.println("ENTER STORAGE DEVICE ID ");
         e.sId=br.readLine();
         e.persistent_save();
         external.add(e);
         externalFlag.add(1);
         return e;
     }
     
    static public Removable addUSB() throws IOException{
          BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
         Removable r=new Removable();
         System.out.println("ENTER STORAGE DEVICE ID ");
         r.sId=br.readLine();
         r.persistent_save();
         USB.add(r);
         USBFlag.add(1);
         return r;
     }
    
    static public External readExternal() throws IOException{
         BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        System.out.println("ENTER THE ID OF THE EXTERNAL DEVICE");
        String id=br.readLine();
        int c=checkIfUsedExt(id);
        if(c==0){
           for(External e:external){
               if(e.sId.equalsIgnoreCase(id)){
                   e.d.displayData();
                   externalFlag.add(external.indexOf(e),1);
                   return e;
               }
           } 
        }
        else if(c==-1){
            System.out.println("DEVICE DOES NOT EXIST");
            return null;
        }
        return null;
    }
    static public Removable readUSB() throws IOException{
         BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        System.out.println("ENTER THE ID OF THE USB DEVICE");
        String id=br.readLine();
        int c=checkIfUsedUSB(id);
        if(c==0){
           for(Removable u:USB){
               if(u.sId.equalsIgnoreCase(id)){
                   u.d.displayData();
                   externalFlag.add(external.indexOf(u),1);
                   return u;
               }
           } 
        }
        else if(c==-1){
            System.out.println("DEVICE DOES NOT EXIST");
            return null;
        }
        return null;
    }
     static public External writeExternal(String id) throws IOException{
         //BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
       
        int c=checkIfUsedExt(id);
        if(c==0){
           for(External e:external){
               if(e.sId.equalsIgnoreCase(id)){
                   e.persistent_save();
                   externalFlag.add(external.indexOf(e),1);
                   return e;
               }
           } 
        }
        else if(c==-1){
            System.out.println("DEVICE DOES NOT EXIST");
            return null;
        }
        return null;
    }
    static public Removable writeUSB(String id) throws IOException{
         //BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        
        int c=checkIfUsedUSB(id);
        if(c==0){
           for(Removable u:USB){
               if(u.sId.equalsIgnoreCase(id)){
                   u.persistent_save();
                   externalFlag.add(external.indexOf(u),1);
                   return u;
               }
           } 
        }
        else if(c==-1){
            System.out.println("DEVICE DOES NOT EXIST");
            return null;
        }
           return null;
    }
    
  /*  public static void disconnectExt(String id,){
        for(External e:)
    }
    
    public static void disconnectUSB(String id){
        
    }*/
    
    public static int checkIfUsedExt(String Id){
        for(External e:external){
            if(e.sId.equalsIgnoreCase(Id)){
                if(externalFlag.get(external.indexOf(e))==1&&!e.connectedDev.contains(connectedDev)){
                    System.out.println("THE EXTERNAL DEVICE IS CURRENTLY BEING USED");
                    return 1;
                }
                else return 0;
            }
            
            }
         return -1;
        }
     public static int checkIfUsedUSB(String Id){
        for(Removable r:USB){
            if(r.sId.equalsIgnoreCase(Id)){
                if(externalFlag.get(external.indexOf(r))==1&&!r.connectedDev.contains(connectedDev)){
                    System.out.println("THE EXTERNAL DEVICE IS CURRENTLY BEING USED");
                    return 1;
                }
                else return 0;
            }
            
            }
         return -1;
        }
        
}
