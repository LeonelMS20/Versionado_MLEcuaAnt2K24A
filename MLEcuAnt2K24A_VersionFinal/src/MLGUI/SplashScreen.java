package MLGUI;

import javax.swing.*;
import java.awt.*;

public class SplashScreen extends JWindow {

    public SplashScreen() {
        // Configurar el tamaño y el layout del splash screen
        setSize(300, 400); // Ajusta el tamaño según sea necesario
        setLocationRelativeTo(null); // Centrar en la pantalla

        // Crear un panel para el contenido del splash screen
        JPanel content = new JPanel(new BorderLayout());
        content.setBackground(Color.WHITE); // Color de fondo

        // Cargar y ajustar la imagen
        ImageIcon splashIcon = new ImageIcon("src/MLGUI/Resource/MLSplash.png"); // Cambia la ruta de la imagen
        JLabel splashLabel = new JLabel(splashIcon);
        content.add(splashLabel, BorderLayout.CENTER);

        // Agregar el panel al JWindow
        setContentPane(content);
    }
}
