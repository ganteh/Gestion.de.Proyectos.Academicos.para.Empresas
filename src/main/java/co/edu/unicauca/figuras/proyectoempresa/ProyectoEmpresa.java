/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicauca.figuras.proyectoempresa;

import co.edu.unicauca.proyectocurso.presentation.GUIRegistrarUsuarios;
import javax.swing.SwingUtilities;

/**
 *
 * @author yeixongec
 */
public class ProyectoEmpresa {
    public static void main(String[] args) {
        System.out.println("Aplicación iniciada correctamente.");
        
        
        // Iniciar la GUI en el hilo de eventos de Swing
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GUIRegistrarUsuarios();
            }
        });
    }
}
    
