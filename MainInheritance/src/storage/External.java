/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class External extends Storage {
     public Data d;
     public String sId; 
    public ArrayList<String> connectedDev=new ArrayList<>();
     
     public External(){
         d=new Data();
     }

    @Override
   public void persistent_save() throws IOException {
         BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String data;
        
        System.out.println("ENTER THE DATA:");
        data=br.readLine();
        d.data.add(data);
        
        
    }

    @Override
    void capacity() {
        
    }
}
