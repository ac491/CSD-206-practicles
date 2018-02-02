/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maproutes;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class Admin {
    
    
   public static void writePath(String path,String user){
       JSONObject obj=readUser();
         JSONArray jArray=(JSONArray) obj.get("users");
         for(Object o:jArray){
             JSONObject jobj=(JSONObject) o;
             if(user.equalsIgnoreCase((String) jobj.get("username"))){
                 jobj.put("path",path);
                 jArray.add(jobj);
                 break;
             }
             
         }
         obj.put("users",jArray);
         
         try {
            FileWriter file=new FileWriter("user.json");
            file.write(obj.toString());
            file.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
   }
    
    public static JSONObject read(){
     JSONParser parser = new JSONParser();
        try {
            JSONObject obj=(JSONObject)parser.parse(new FileReader("places.json"));
            
            return obj;
            
            
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ParseException ex) {
           ex.printStackTrace();
        }
        return null;
    } 
     public static JSONObject readUser(){
     JSONParser parser = new JSONParser();
        try {
            JSONObject obj=(JSONObject)parser.parse(new FileReader("user.json"));
            
            return obj;
            
            
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ParseException ex) {
           ex.printStackTrace();
        }
        return null;
    } 
    
     public static void writeUser(String username,String pass){
         JSONObject obj=readUser();
         JSONArray jArray=(JSONArray) obj.get("users");
         
         JSONObject jobj=new JSONObject();
         jobj.put("username",username);
         jobj.put("pass",pass);
         jobj.put("path","");
         
         jArray.add(jobj);
         obj.put("users",jArray);
         
         try {
            FileWriter file=new FileWriter("user.json");
            file.write(obj.toString());
            file.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
     }
     
    
}
