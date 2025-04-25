/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicauca.proyectocurso.domain.entities;

import java.util.UUID;

/**
 *
 * @author Lenovo pc
 */

public class StudentProject {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    
    private int id;
    private String studentID;
    private String projectID;
    private Student student;
    private Project project;
    private Status status = Status.RECEIVED;

    // Constructor con parámetros
    public StudentProject(int id, String studentID, String projectID, Status status) {
        this.id = id;
        this.studentID = studentID;
        this.projectID = projectID;
        this.student = new Student(UUID.fromString(studentID), id);
        this.project = new Project(UUID.fromString(projectID));
        this.status = status;
    }
    
    public StudentProject(){}

    // Getters y Setters

    
    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getProjectID() {
        return projectID;
    }

    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    // Enum para el estado del proyecto
    public enum Status {
        RECEIVED, ACCEPTED, REJECTED, IN_EXECUTION, CLOSED
    }

    @Override
    public String toString() {
        return "StudentProject{" +
                "id=" + getId() +
                ", studentID=" + getStudentID() +
                ", projectID=" + getProjectID() +
                ", status=" + status +
                '}';
    }
}