

package computer;

import storage.ExternalStorageDevices;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import storage.External;
import storage.Removable;


public class Users {
    String userId;
    static int user=1;
    ArrayList<Computer> devices=new ArrayList<>();
   
        Users(){
        userId="U0"+user;
        user++;
    }

    void userInterface() throws IOException {
        int ch;
        Scanner sc=new Scanner(System.in);
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        System.out.println(" SELECT YOUR CHOICE: 1.ADD DEVICE 2.DISPLAY ALL DEVICES 3.DISPLAY DEVICES OF SAME CATEGORY 4.WRITE DATA TO DEVICE'S INTERNAL MEMORY 5.VIEW CONTENTS OF INTERNAL MEMORY 6.ACCESS EXTERNAL STORAGE DEVICE 7.CHARGE DEVICE 8.EXIT");
        ch=sc.nextInt();
        while(ch!=8){
            switch(ch){
                case 1: addDevice();
                         break;
                case 2: printAllDev();
                        break;
                case 3: System.out.println("ENTER THE DEVICE CATEGORY(eg laptop)");
                        String dev=br.readLine();
                        printDevices(dev);
                        break;
                        
                case 4: int flag=0;
                        System.out.println("ENTER DEVICE ID");
                        String devId=br.readLine();
                        for(Computer c:devices){
                            if(c.deviceId.equalsIgnoreCase(devId)){
                                c.i.persistent_save();
                                flag=1;
                                break;
                            }
                        }
                        if(flag==0){
                            System.out.println(" DEVICE NOT FOUND!");
                        }
                        break;
                case 5: flag=0;
                        System.out.println("ENTER DEVICE ID");
                        devId=br.readLine();
                        for(Computer c:devices){
                            if(c.deviceId.equalsIgnoreCase(devId)){
                                c.i.d.displayData();
                                flag=1;
                                break;
                            }
                        }
                        if(flag==0){
                            System.out.println(" DEVICE NOT FOUND!");
                        }
                        break;  
                case 6:System.out.println(" ENTER THE DEVICE ID OF THE DEVICE:");
                       devId=br.readLine();
                       ExternalDevice(devId);
                        break;
                        
                case 7: flag=0;
                        System.out.println(" ENTER THE DEVICE ID OF THE DEVICE TO BE CHARGED");
                         devId=br.readLine();
                        for(Computer c:devices){
                            if(c.deviceId.equalsIgnoreCase(devId)){
                               if(c instanceof Desktop){
                                   
                                   Desktop d=(Desktop)c;
                                   d.charge();
                                    
                               }
                               else if(c instanceof Laptop){
                                   Laptop l=(Laptop)c;
                                   l.charge();
                                    
                               }
                               else if(c instanceof Mobile){
                                   Mobile m=(Mobile)c;
                                   m.charge();
                                    
                               }
                               else if(c instanceof Tab){
                                   Tab t=(Tab)c;
                                   t.charge();
                                    
                               }
                               flag=1;
                                break;
                            }
                        }
                        if(flag==0){
                            System.out.println(" DEVICE NOT FOUND!");
                        }
                        break;
            }
            System.out.println(" SELECT YOUR CHOICE: 1.ADD DEVICE 2.DISPLAY ALL DEVICES 3.DISPLAY DEVICES OF SAME CATEGORY 4.WRITE DATA TO DEVICE'S INTERNAL MEMORY 5.VIEW CONTENTS OF INTERNAL MEMORY 6.ACCESS EXTERNAL STORAGE DEVICE 7.CHARGE DEVICE 8.EXIT");
            ch=sc.nextInt();
        }
        
        
    }
    public void ExternalDevice(String devId) throws IOException{
        int ch;
        Computer c1 = null;
        Scanner sc=new Scanner(System.in);
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        System.out.println(" SELECT YOUR CHOICE: 1.WRITE TO EXTERNAL DRIVE 2.READ FROM EXTERNAL DRIVE 3.WRITE TO USB 4.READ FROM USB 5.REMOVE STORAGE DEVICE");
        ch=sc.nextInt();
        ExternalStorageDevices.connectedDev=devId;
        for(Computer c:devices){
            if(c.deviceId.equalsIgnoreCase(devId)){
               c1=c; 
            }
        }
        if(c1==null){
               System.out.println("DEVICE NOT FOUND");
               return;
        }
        if(c1 instanceof Desktop)
                                {  if(((Desktop) c1).numOfPorts==0){
                                   
                                    System.out.println("ALL PORTS ARE FILLED PLEASE REMOVE SOME DEVICES");
                                    return;
                                }
                                }
                        else if(c1 instanceof Laptop){
                                   if(((Laptop) c1).numOfPorts==0){
                                   
                                    System.out.println("ALL PORTS ARE FILLED PLEASE REMOVE SOME DEVICES");
                                    return;
                                     }
                                 }
 
        
        
        switch(ch){
            
            case 1: System.out.println("ENTER YOUR CHOICE: 1.NEW DEVICE 2.EXISTING DEVICE");
                    ch=sc.nextInt();
                    switch(ch){
                        case 1: 
                            
                             
                                External external=ExternalStorageDevices.addExternal();
                                
                                if(c1 instanceof Desktop){
                                         
                                          if(external!=null){
                                         Desktop.connectedExt.add(external);
                                          external.connectedDev.add(devId);
                                        
                                         ((Desktop) c1).numOfPorts--;
                                         
                                         }
                                     }
                                     else if(c1 instanceof Laptop){
                                         if(external!=null){
                                         
                                         Laptop.connectedExt.add(external);
                                         external.connectedDev.add(devId);
                                         ((Laptop) c1).numOfPorts--;
                                         }
                                     }
                               
                               
                                break;
                        case 2: 
                              
                                System.out.println("ENTER THE ID OF THE EXTERNAL DEVICE");
                                String id=br.readLine();
                                External e= ExternalStorageDevices.writeExternal(id);
                                   
                                     if(c1 instanceof Desktop){
                                         
                                          if(e!=null){
                                         Desktop.connectedExt.add(e);
                                         if(!e.connectedDev.contains(devId)){
                                             e.connectedDev.add(devId);
                                         }
                                          int flag=0;
                                         for(External ex:Desktop.connectedExt){
                                             if(ex.sId.equalsIgnoreCase(e.sId)){
                                                 flag=1;
                                                 break;
                                             }
                                         }
                                         if(flag==0){
                                         ((Desktop) c1).numOfPorts--;
                                         }
                                         }
                                     }
                                     else if(c1 instanceof Laptop){
                                         if(e!=null){
                                         
                                         Laptop.connectedExt.add(e);
                                         if(!e.connectedDev.contains(devId)){
                                             e.connectedDev.add(devId);
                                         }
                                         int flag=0;
                                         for(External ex:Laptop.connectedExt){
                                             if(ex.sId.equalsIgnoreCase(e.sId)){
                                                 flag=1;
                                                 break;
                                             }
                                         }
                                         if(flag==0){
                                         ((Laptop) c1).numOfPorts--;
                                         }
                                          }
                                     }
                                 
                                 break;
                    }
                    break;
        case 2: External e=ExternalStorageDevices.readExternal();
                     if(c1 instanceof Desktop){
                                         
                                          if(e!=null){
                                         Desktop.connectedExt.add(e);
                                         if(!e.connectedDev.contains(devId)){
                                             e.connectedDev.add(devId);
                                         }
                                         int flag=0;
                                         for(External ex:Desktop.connectedExt){
                                             if(ex.sId.equalsIgnoreCase(e.sId)){
                                                 flag=1;
                                                 break;
                                             }
                                         }
                                         if(flag==0){
                                         ((Desktop) c1).numOfPorts--;
                                         }
                                         }
                                     }
                                     else if(c1 instanceof Laptop){
                                         if(e!=null){
                                        
                                         Laptop.connectedExt.add(e);
                                         if(!e.connectedDev.contains(devId)){
                                             e.connectedDev.add(devId);
                                         }
                                         int flag=0;
                                         for(External ex:Laptop.connectedExt){
                                             if(ex.sId.equalsIgnoreCase(e.sId)){
                                                 flag=1;
                                                 break;
                                             }
                                         }
                                         if(flag==0){
                                         ((Laptop) c1).numOfPorts--;
                                         }
                                          }
                                     }
                                break;   
            case 3:System.out.println("ENTER YOUR CHOICE: 1.NEW USB 2.EXISTING USB");
                    ch=sc.nextInt();
                    switch(ch){
                        case 1: 
                               Removable r=ExternalStorageDevices.addUSB();
                               if(c1 instanceof Desktop){
                                         
                                          if(r!=null){
                                         Desktop.connectedUSBs.add(r);
                                          r.connectedDev.add(devId);
                                        
                                         ((Desktop) c1).numOfPorts--;
                                         
                                         }
                                     }
                                     else if(c1 instanceof Laptop){
                                         if(r!=null){
                                         
                                         Laptop.connectedUSBs.add(r);
                                         r.connectedDev.add(devId);
                                         ((Laptop) c1).numOfPorts--;
                                         }
                                     }
                               
                                break;
                        case 2: System.out.println("ENTER THE ID OF THE USB DEVICE");
                                String id=br.readLine();
                                Removable u= ExternalStorageDevices.writeUSB(id);
                                   
                                     if(c1 instanceof Desktop){
                                         
                                          if(u!=null){
                                         Desktop.connectedUSBs.add(u);
                                         if(!u.connectedDev.contains(devId)){
                                             u.connectedDev.add(devId);
                                         }
                                          int flag=0;
                                         for(Removable r1:Desktop.connectedUSBs){
                                             if(r1.sId.equalsIgnoreCase(u.sId)){
                                                 flag=1;
                                                 break;
                                             }
                                         }
                                         if(flag==0){
                                         ((Desktop) c1).numOfPorts--;
                                         }
                                         }
                                     }
                                     else if(c1 instanceof Laptop){
                                         if(u!=null){
                                         
                                         Laptop.connectedUSBs.add(u);
                                         if(!u.connectedDev.contains(devId)){
                                             u.connectedDev.add(devId);
                                         }
                                         int flag=0;
                                         for(Removable r2:Laptop.connectedUSBs){
                                             if(r2.sId.equalsIgnoreCase(u.sId)){
                                                 flag=1;
                                                 break;
                                             }
                                         }
                                         if(flag==0){
                                         ((Laptop) c1).numOfPorts--;
                                         }
                                          }
                                     }
                                break;
                                 
                    }
                    break;
            case 4: Removable usb=ExternalStorageDevices.readUSB();
                     if(c1 instanceof Desktop){
                                         
                                          if(usb!=null){
                                         Desktop.connectedUSBs.add(usb);
                                          if(!usb.connectedDev.contains(devId)){
                                             usb.connectedDev.add(devId);
                                         }
                                         int flag=0;
                                         for(Removable r3:Desktop.connectedUSBs){
                                             if(r3.sId.equalsIgnoreCase(usb.sId)){
                                                 flag=1;
                                                 break;
                                             }
                                         }
                                         if(flag==0){
                                         ((Desktop) c1).numOfPorts--;
                                         }
                                         }
                                     }
                                     else if(c1 instanceof Laptop){
                                         if(usb!=null){
                                        
                                         Laptop.connectedUSBs.add(usb);
                                          if(!usb.connectedDev.contains(devId)){
                                             usb.connectedDev.add(devId);
                                         }
                                         int flag=0;
                                         for(Removable r4:Laptop.connectedUSBs){
                                             if(r4.sId.equalsIgnoreCase(usb.sId)){
                                                 flag=1;
                                                 break;
                                             }
                                         }
                                         if(flag==0){
                                         ((Laptop) c1).numOfPorts--;
                                         }
                                          }
                                     }
                                break; 
            case 5:System.out.println("REMOVE : 1.EXTERNAL STORAGE 2.USB DEVICE") ;
                   int a=Integer.parseInt(br.readLine());
                   System.out.println("ENTER THE STORAGE DEVICE ID");
                   String id=br.readLine();
                   switch(a){
                       case 1: if(c1 instanceof Desktop){
                           int flag=0;
                               for(External ext:Desktop.connectedExt){
                                   if(ext.sId.equalsIgnoreCase(id)){
                                       ext.connectedDev.remove(devId);
                                       Desktop.connectedExt.remove(ext);
                                       flag=1;
                                       ((Desktop) c1).numOfPorts++;
                                       ExternalStorageDevices.externalFlag.set(ExternalStorageDevices.external.indexOf(ext),0);
                                       System.out.println("DEVICE SUCCESSFULLY REMOVED");
                                       break;
                                   }
                                   
                               }
                               if(flag==0){
                                   System.out.println("DEVICE NOT FOUND");
                               }
                       }
                       else if(c1 instanceof Laptop){
                            int flag=0;
                               for(External ext:Laptop.connectedExt){
                                   if(ext.sId.equalsIgnoreCase(id)){
                                       ext.connectedDev.remove(devId);
                                       Laptop.connectedExt.remove(ext);
                                       flag=1;
                                       ((Laptop) c1).numOfPorts++;
                                        ExternalStorageDevices.externalFlag.set(ExternalStorageDevices.external.indexOf(ext),0);
                                       System.out.println("DEVICE SUCCESSFULLY REMOVED");
                                       break;
                                   }
                                   
                               }
                               if(flag==0){
                                   System.out.println("DEVICE NOT FOUND");
                               }
                       }
                            break;
                       case 2:if(c1 instanceof Desktop){
                           int flag=0;
                               for(Removable rem:Desktop.connectedUSBs){
                                   if(rem.sId.equalsIgnoreCase(id)){
                                       rem.connectedDev.remove(devId);
                                       Desktop.connectedUSBs.remove(rem);
                                       flag=1;
                                       ((Desktop) c1).numOfPorts++;
                                        ExternalStorageDevices.USBFlag.set(ExternalStorageDevices.external.indexOf(rem),0);
                                       System.out.println("DEVICE SUCCESSFULLY REMOVED");
                                       break;
                                   }
                                   
                               }
                               if(flag==0){
                                   System.out.println("DEVICE NOT FOUND");
                               }
                       }
                       else if(c1 instanceof Laptop){
                            int flag=0;
                               for(Removable rem:Laptop.connectedUSBs){
                                   if(rem.sId.equalsIgnoreCase(id)){
                                       rem.connectedDev.remove(devId);
                                       Laptop.connectedUSBs.remove(rem);
                                       flag=1;
                                       ((Laptop) c1).numOfPorts++;
                                        ExternalStorageDevices.USBFlag.set(ExternalStorageDevices.external.indexOf(rem),0);
                                       System.out.println("DEVICE SUCCESSFULLY REMOVED");
                                       break;
                                   }
                                   
                               }
                               if(flag==0){
                                   System.out.println("DEVICE NOT FOUND");
                               }
                       }
                   }
             }
    }  
    
    public void addDevice() throws IOException{
        Scanner sc=new Scanner(System.in);
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in)); 
        Computer c;
           int ch;
           
        System.out.println("SELECT YOUR CHOICE TO ADD: 1.DESKTOP 2.LAPTOP 3.MOBILE 4.TAB ");
        ch=sc.nextInt(); 
        System.out.println("ENTER DEVICE ID");
        String deviceId=br.readLine();
        
        System.out.println("ENTER DEVICE'S PROCESSOR DETAIL:");
        String processor=br.readLine();
		
        System.out.println("ENTER YOUR DEVICE'S MOTHERBOARD DETAILS");
        String motherboard=br.readLine();

        System.out.println("ENTER YOUR DEVICE'S RAM");
        String ram=br.readLine();

     
                      switch(ch){
                            case 1:  c=new Desktop(processor,motherboard,ram);
                                     c.deviceId=deviceId;
                                     devices.add(c);
                                     break;
                            case 2:  c=new Laptop(processor,motherboard,ram);
                                     c.deviceId=deviceId;
                                     devices.add(c);
                                     break;
                            case 3:  c=new Mobile(processor,motherboard,ram);
                                     c.deviceId=deviceId;
                                     devices.add(c);
                                     break;     
                            case 4:  c=new Tab(processor,motherboard,ram);
                                     c.deviceId=deviceId;
                                     devices.add(c);
                                     break;
                    
                            default:System.out.println("WRONG CHOICE");
                    
                     }
       
    }
    public void printAllDev(){
        int a=1;
            for(Computer c:devices){
                if(c instanceof Desktop ){
                    System.out.println("DESKTOP "+a);
                    c.printData();
                    a++;  
                }
            }
            a=1;
            for(Computer c:devices){
                if(c instanceof Laptop ){
                    System.out.println("LAPTOP "+a);
                    c.printData();
                    a++;  
                }
            }
            a=1;
            for(Computer c:devices){
                if(c instanceof Mobile ){
                    System.out.println("MOBILE "+a);
                    c.printData();
                    a++;  
                }
            }
            a=1;
            for(Computer c:devices){
                if(c instanceof Tab ){
                    System.out.println("TAB "+a);
                    c.printData();
                    a++;  
                }
            }
                
         
    }
    
    public void printDevices(String device){
        int a,flag=0;
        if(device.equalsIgnoreCase("desktop")){
            a=1;flag=0;
            for(Computer c:devices){
                if(c instanceof Desktop ){
                    System.out.println("DESKTOP "+a);
                    c.printData();
                    a++;
                    flag=1;
                    
                }
            }
                if (flag==0){
                    System.out.println("YOU DO NOT HAVE ANY DESKTOPS");
                }
            
        }
        else if(device.equalsIgnoreCase("laptop")){
             a=1;flag=0;
            for(Computer c:devices){
                if(c instanceof Laptop ){
                    System.out.println("LAPTOP "+a);
                    c.printData();
                    a++;
                    flag=1;
                    
                }
            }
                if (flag==0){
                    System.out.println("YOU DO NOT HAVE ANY LAPTOPS");
                }
            
         }
         else if(device.equalsIgnoreCase("mobile")){
             a=1;flag=0;
            for(Computer c:devices){
                if(c instanceof Mobile ){
                    System.out.println("MOBILE "+a);
                    c.printData();
                    a++;
                    flag=1;
                    
                }
            }
                if (flag==0){
                    System.out.println("YOU DO NOT HAVE ANY MOBILE PHONES");
                }
            
         }
         else if(device.equalsIgnoreCase("tab")){
             a=1;flag=0;
            for(Computer c:devices){
                if(c instanceof Tab ){
                    System.out.println("TAB "+a);
                    c.printData();
                    a++;
                    flag=1;
                    
                }
            }
                if (flag==0){
                    System.out.println("YOU DO NOT HAVE ANY TABS");
                }
            
         }
      
         else{
              System.out.println("YOU DO NOT HAVE ANY SUCH DEVICE!!");
          }
         
    }
        
        
    
}
