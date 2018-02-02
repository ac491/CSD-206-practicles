/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniproject;

import java.util.ArrayList;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class Mail {

   static public void sendMail(Customers c,double total,String mop){
              final String username = "ac491@snu.edu.in"; 
		final String password = "Dhirenjolly1";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });

		try {
                       StringBuilder s=new StringBuilder();
                       int a=1;
                       s.append("<table><tr><td><B>SNO</B></td><td><B>ISBN</B></td><td><B>TITLE</B></td><td><B>AUTHOR</B></td><td><B>PUBLISHER</B></td><td><B>YEAR PUBLISHED</B></td><td><B>VOLUME</B></td><td><B>EDITION</B></td><td><B>PRICE</B></td><td><B>QUANTITY</B></td></tr><br>");
                       for(ItemOrder i:c.cart.itemOrder){
                           Book book=(Book)i.item;
                           s.append("<tr><td>"+a+"</td><td>"+i.item.ISBN+"</td><td>"+i.item.title+"</td><td>"+book.author+"</td><td>"+i.item.publisher+"</td><td>"+i.item.yearPublished+"</td><td>"+book.yearPublished+"</td><td>"+book.edition+"</td><td>"+i.item.price+"</td><td>"+i.quantity+"</td></tr>");
                           a++;
                       }
                       s.append("</table>");
                       
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("ac491@snu.edu.in")); 
			message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(c.emailId));
			message.setSubject("ORDER PLACED");
			message.setContent("DEAR "+c.name
				+ "<br><br>YOUR ORDER HAS BEEN PLACED AND WILL BE DELIVERED IN 2-3 BUSINESS DAYS<br>MODE OF PAYMENT: "+mop+"<br><br>"+s+"<br><br>TOTAL="+total+"<br>BILL AMOUNT=TOTAL+SALES TAX+SHIPPING FEE<br>AMOUNT PAYABLE="+(total+(total*15)/100+100)+"<br><br>THANK YOU FOR SHOPPING WITH US.","text/html");

			Transport.send(message);

			

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
   
     
}
