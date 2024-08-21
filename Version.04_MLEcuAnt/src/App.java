//Leonel Alexander Morales Sosapanta
//CEDULA: 1752137297
//CARRERA: INGENIERIA EN SOFTWARE
//FECHA DE CREACION: 2024-08-21
//lINK DE Git : https://github.com/LeonelMS20/Versionado_MLEcuaAnt2K24A.git

import javax.swing.SwingUtilities;

import MLGUI.MLUIComponent;
import MLGUI.SplashScreen;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Crear y mostrar el splash screen
            SplashScreen splash = new SplashScreen();
            splash.setVisible(true);

            // Crear un hilo para simular la carga de datos o inicialización
            new Thread(() -> {
                try {
                    // Simular una carga de datos
                    Thread.sleep(3000); // Espera 3 segundos (ajusta el tiempo según sea necesario)
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // Cerrar el splash screen y mostrar la interfaz principal
                SwingUtilities.invokeLater(() -> {
                    splash.setVisible(false); // Ocultar el splash screen
                    new MLUIComponent().setVisible(true); // Mostrar la interfaz principal
                });
            }).start();
        });
    }
}
