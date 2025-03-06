/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicauca.figuras.domain.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author ibell
 */
public class Project {
    private UUID id;
    private String name;
    private String summary;
    private String objectives;
    private String description;
    private int maxMonths;
    private Double budget;
    private LocalDate date;
    private ProjectState state;
    private Company company;
    private List<String> comments;

    public Project(String name, String summary, String objectives, String description, 
                   int maxMonths, Double budget, Company company) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.summary = summary;
        this.objectives = objectives;
        this.description = description;
        this.maxMonths = maxMonths;
        this.budget = budget;
        this.date = LocalDate.now();
        this.state = ProjectState.RECEIVED;
        this.company = company;
        this.comments = new ArrayList<>();
    }

    public void addComment(String comment) {
        comments.add(comment);
    }

    public void changeState(ProjectState newState) {
        this.state = newState;
    }

    // Getters y setters
    public UUID getId() { return id; }
    public String getName() { return name; }
    public ProjectState getState() { return state; }
    // Resto de getters y setters
}


