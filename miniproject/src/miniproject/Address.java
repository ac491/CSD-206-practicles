/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniproject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;


public class Address implements Serializable{
    String state;
    String city;
    String postalCode;
    String country;
    String street;
    
    
    public void getAddress() throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        System.out.println("ENTER THE NAME OF STREET:");
        street=br.readLine();
        System.out.println("ENTER THE NAME OF CITY:");
        city=br.readLine();
        System.out.println("ENTER THE NAME OF STATE:");
        state=br.readLine();
        System.out.println("ENTER THE NAME OF COUNTRY:");
        country=br.readLine();
        System.out.println("ENTER THE NAME OF POSTAL CODE:");
        postalCode=br.readLine();
    }
    
    public void display(){
         System.out.println("ADDRESS: ");
         System.out.println(" STREET: "+street);
         System.out.println(" CITY: "+city);
         System.out.println(" STATE: "+state);
         System.out.println(" COUNTRY: "+country);
         System.out.println(" POSTAL CODE: "+postalCode);
    }
    
    
}
