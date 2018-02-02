
package storage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;




public class Internal extends Storage{
   public Data d;
   
   public Internal(){
       d=new Data();
   }

    @Override
    public void persistent_save() throws IOException{
        
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
