/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicauca.figuras.proyectoempresa;

import co.edu.unicauca.proyectocurso.access.DatabaseConnection;
import co.edu.unicauca.proyectocurso.access.ProjectRepositoryImpl;
import co.edu.unicauca.proyectocurso.access.StudentRepositoryImpl;
import co.edu.unicauca.proyectocurso.domain.entities.Company;
import co.edu.unicauca.proyectocurso.domain.entities.Project;
import co.edu.unicauca.proyectocurso.domain.entities.ProjectState;
import co.edu.unicauca.proyectocurso.domain.entities.Student;
import co.edu.unicauca.proyectocurso.presentation.GUICoordProyPendientes;
import co.edu.unicauca.proyectocurso.presentation.GUIRegistrarUsuarios;
import co.edu.unicauca.proyectocurso.presentation.GUILogin;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.UUID;
import javax.swing.SwingUtilities;

/**
 *
 * @author yeixongec
 */
public class ProyectoEmpresa {

    public static void main(String[] args) {
            java.awt.EventQueue.invokeLater(() -> new GUILogin().setVisible(true));
    }
}
