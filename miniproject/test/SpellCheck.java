/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import org.apache.lucene.search.spell.PlainTextDictionary;
import org.apache.lucene.search.spell.SpellChecker;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.IndexInput;
import org.apache.lucene.store.IndexOutput;


public class SpellCheck {

    
    
    public static void check(String search) throws IOException,NoSuchMethodException{
        //Directory spellIndexDirectory = new Directory();
        final int numOfSuggestions=5;
       // Directory spellIndexDirectory = null;
        // File file = new File("C:\\Users\\sneha\\Documents\\NetBeansProjects\\miniproject");
       // Directory directory = FSDirectory.open(new Path("C:\\Users\\sneha\\Documents\\NetBeansProjects\\miniproject"));

        final SpellChecker sp = new SpellChecker(directory);
        
        sp.indexDictionary(new PlainTextDictionary(new File("C:\\Users\\sneha\\Documents\\NetBeansProjects\\miniproject\\bookname.txt")));
        
        String[] suggestions=sp.suggestSimilar(search, numOfSuggestions);
        
        System.out.println("DID YOU MEAN:\n");
        ArrayList<ItemOrder> items=Admin.readItems();
        for(ItemOrder i:items){
            for(String s:suggestions){
                if(i.item.title.equalsIgnoreCase(s)){
                    i.display();
                }
            }
        }
 
    }
}
