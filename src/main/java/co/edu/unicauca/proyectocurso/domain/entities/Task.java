/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicauca.proyectocurso.domain.entities;
import java.util.*;
import java.time.LocalDate;
/**
 *
 * @author Lenovo pc
 */
public class Task {
    private UUID id;
    private String name;
    private String description;
    private LocalDate date;
    private Date finalizationDate;
    private TaskState state;
    
    public Task(String name, String description, Date finalizationDate){
        this.id = UUID.randomUUID();
        this.name = name;
        this.description = description;
        this.date = LocalDate.now();
        this.finalizationDate = finalizationDate;
        this.state = TaskState.INPROCESS;
    }
    
     public void changeState(TaskState newState) {
         this.setState(newState);
    }
     
    //Getters y setters
    public UUID getId() {return id;}
     
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}
    
    public Date getFinalizationDate() {return finalizationDate;}
    public void setFinalizationDate(Date finalizationDate) {this.finalizationDate = finalizationDate;}
    
    public TaskState getState() {return state;}
    public void setState(TaskState state) {this.state = state;}
    
    public LocalDate getDate() {return date;}
}
