/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicauca.proyectocurso.domain.entities;

/**
 *
 * @author ibell
 */
public class Message {

   private User sender; 
   private User receiver;
   private String content;
   

    public Message(User sender, User receiver, String content) {
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
    }
    
    public User getSender() {return sender;}
    public void setSender(User sender) {this.sender = sender;}

    public User getReceiver() {return receiver;}
    public void setReceiver(User receiver) {this.receiver = receiver;}

    public String getContent() {return content;}
    public void setContent(String content) {this.content = content;}


    
}
