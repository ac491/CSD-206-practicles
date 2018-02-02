
package maproutes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class Places extends JFrame implements ActionListener{
    static String username;
     private String source;
    private String destination;
    ArrayList<String> visited;
    JButton[] buttons;    
    JButton prev;
    JButton prevParent;
    JLabel label;
    JPanel panel;
    JButton recommend;
    ArrayList<String> places;
    HashMap<String,String> connectedPlaces;
    HashMap<String,String> attractions;
    //private int x;// coordinates of jbutton
   // private int y;
    JButton current;
    private int drawline=0;
    String[] currentConnected;
    String[] prevConnected;
    String path="";
   
    
    public Places(){
        super("PLACES");
        visited=new ArrayList<>();
        places=new ArrayList<>();
        this.source=Route.getSource();
        this.destination=Route.getDestination();
        prevParent=new JButton();
       // System.out.println(source);
        panel=new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (drawline==1) {
                    for(String cc:currentConnected){
                         for(JButton b:buttons){
                             if(b.getText().equalsIgnoreCase(cc)){
                                 g.setColor(Color.BLACK);
                                 g.drawLine((int) current.getX()+10,(int) current.getY()+10, b.getX()+10, b.getY()+10);
                             }
                         }
                   
                    }
                }
            }
        };
        label=new JLabel();
        panel.add(label);
        
        
        //label.setVisible(true);
       // label.setBounds(200,20,80,20);
       
        panel.setVisible(true);
        panel.setLayout(null);
        this.setContentPane(panel);
        super.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        super.setSize(new Dimension(500,500));
        super.setVisible(true);
        buttons=new JButton[5];
        connectedPlaces=new HashMap<>();
        attractions=new HashMap<>();
       
        
        createButtons();
         
     }
    
    
    public void createButtons(){
        JSONObject obj=Admin.read();
        JSONArray jsonArray=(JSONArray) obj.get("places");
        for(Object o:jsonArray){
            JSONObject jobj=(JSONObject)o;
            places.add((String) jobj.get("place"));
            String connected=(String) jobj.get("connected");
            connectedPlaces.put((String) jobj.get("place"),connected);
            attractions.put((String)jobj.get("place"),(String)jobj.get("data"));
            
            recommend=new JButton();
            panel.add(recommend);
            recommend.setText("RECOMMEND");
            recommend.setVisible(true);
            recommend.setBounds(5,5,180,30);
            recommend.addActionListener(this);
            
        }
        source=source.toLowerCase();
        places.remove(source); 
        for(int i=0;i<5;i++){
            JButton b=new JButton();
            if(i==0){
            b.setVisible(true);
            b.setText(source);
            buttons[i]=b;
            }
            else{
            b.setVisible(true);
            b.setText(places.get(i-1));
            buttons[i]=b;
            }
            b.setEnabled(false);
            b.addActionListener(this);
        }   
        buttons[0].setBounds(20,200,70,40);
        buttons[0].setEnabled(true);
        
        panel.add(buttons[0]);
        buttons[1].setBounds(200,200,70,40);
        panel.add(buttons[1]);
        buttons[2].setBounds(130,80,70,40);
        panel.add(buttons[2]);
        buttons[3].setBounds(100,300,70,40);
        panel.add(buttons[3]);
        buttons[4].setBounds(400,200,70,40);
        panel.add(buttons[4]);
        places.add(source);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
         currentConnected = null;
        
        String[] data=null;
      
        current=(JButton) e.getSource();
       visited.add(current.getText());
       current.setEnabled(false);
       for(String p:places){
           if(current.getText().equalsIgnoreCase(p)){
               currentConnected=connectedPlaces.get(p).split(",");
               data=attractions.get(p).split(",");
               break;
               }
           }
       
       /*if(Arrays.asList(connected).contains(destination)){
           for(JButton button:buttons){
               if(button.getText().equalsIgnoreCase(destination)){
                   button.setEnabled(true);
                   drawline=1;
                   JOptionPane.showMessageDialog(this,"THE ATTRACTIONS ARE: \n"+Arrays.toString(data));
                   repaint();
               }
           }
       
}else*/
       if(current.getText().equalsIgnoreCase("RECOMMEND")){
           Recommendation r=new Recommendation();
           r.setVisible(true);
           this.setVisible(false);
       }
       else if(current.getText().equalsIgnoreCase(destination)){
           JOptionPane.showMessageDialog(this,"CONGRATULATIONS! YOU HAVE REACHED YOUR DESTINATION");
            JOptionPane.showMessageDialog(this,"THE ATTRACTIONS ARE: \n"+Arrays.toString(data));
           for(JButton btn:buttons){
               if(!btn.equals(current))
               btn.setEnabled(false);
           }
             path=path.concat(current.getText());
             Admin.writePath(path,username);
           
       }
       else{  
             path=path.concat(current.getText());
             path=path.concat(",");
              repaint();
             JOptionPane.showMessageDialog(this,"THE ATTRACTIONS ARE: \n"+Arrays.toString(data));
              for(String cp:currentConnected){
                 for(JButton button:buttons){
                     
                     if(button.getText().equalsIgnoreCase(cp)&&!visited.contains(cp)){
                           button.setEnabled(true);
                            
                           //x=(int) button.getX();
                           //y=(int) button.getY();
                          // current.setEnabled(false);
                           drawline=1;
                           
                           //panel.repaint();
                          break;
                     }
                     
                 }
              }
              
              if(!current.getText().equalsIgnoreCase(source)){
                  for(String cp:prevConnected){
                      for(JButton b:buttons){
                          if(!cp.equalsIgnoreCase(current.getText())&&b.getText().equalsIgnoreCase(cp)&&!Arrays.asList(currentConnected).contains(b.getText())){
                              b.setEnabled(false);
                          }
                      }
                  }
              }
       }
       if(!current.getText().equalsIgnoreCase(source)&&!current.getText().equalsIgnoreCase(destination)){
           
           prev.setEnabled(true);
            if(!prev.getText().equalsIgnoreCase(source)&&!prev.getText().equalsIgnoreCase(destination)){
           
           prevParent.setEnabled(false);
           
           }
        }
      
      prevParent=prev;  
      prev=current;
      prevConnected=currentConnected;
     // prev.setEnabled(false);
       
    }

   /* @Override
    public void paintComponents(Graphics g) {
        panel.paintComponents(g);
       
        g.setColor(Color.BLACK);
        g.drawLine((int) current.getAlignmentX(),(int) current.getAlignmentY(), x, y);
         
    
    }*/
     
     
    
    
    
    
}
