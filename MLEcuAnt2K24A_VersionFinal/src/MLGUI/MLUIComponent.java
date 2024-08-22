/**
     * Autor: Leonel Morales
     * Inicializa la interfaz gráfica EcuaAnta2K24A
     * @version 4.0
     * 
*/

package MLGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import MLBusinessLogic.Entity.MLGenoAlimento;
import MLBusinessLogic.Entity.MLX;
import MLBusinessLogic.Entity.MLXX;
import MLBusinessLogic.Entity.MLXY;

public class MLUIComponent extends JFrame {

    private JComboBox<MLGenoAlimento> genoCombo;
    private JComboBox<String> ingestaCombo;
    private DefaultTableModel model;
    private JButton alimentarButton;
    private JButton eliminarButton;
    private JButton guardarButton;
    private int secuencial; 

    /**
     * Constructor de la clase MLUIComponent.
     * Inicializa la interfaz gráfica y carga los datos desde un archivo CSV.
     */
    public MLUIComponent() {
        // Inicializar los controles y configuraciones
        customerControls();
        secuencial = loadDataFromCSV("MLData/MLHormiguero.csv"); // Cambia el path al archivo CSV
    }

    private void customerControls() {
        // Configuración del JFrame
        setTitle("MLEcuAnt 2K24A");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Colores personalizados
        Color backgroundColor = new Color(0x2C3E50); // Azul oscuro (fondo)
        Color buttonColor = new Color(0xE67E22); // Naranja cálido (botones)
        Color textColor = new Color(0xECF0F1); // Blanco suave (texto)

        // Panel principal
        JPanel mainPanel = new JPanel(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);

        // Título y logo
JPanel titlePanel = new JPanel();
titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
titlePanel.setBackground(backgroundColor);

// Carga la imagen original
ImageIcon frascoIcon = new ImageIcon("src/MLGUI/Resource/MLimageFrasco.png");

// Redimensiona la imagen
Image img = frascoIcon.getImage();
Image resizedImg = img.getScaledInstance(140, 80, Image.SCALE_SMOOTH); // Cambia 50, 50 a las dimensiones deseadas
ImageIcon resizedIcon = new ImageIcon(resizedImg);

JLabel iconLabel = new JLabel(resizedIcon);
iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
titlePanel.add(iconLabel);

JLabel titleLabel = new JLabel("Hormiguero Virtual");
titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
titleLabel.setForeground(textColor);
titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
titlePanel.add(titleLabel);

mainPanel.add(titlePanel, BorderLayout.NORTH);


        // Panel de Tabla de datos
        JPanel tablePanel = new JPanel(new BorderLayout());
        String[] columnNames = {"Registro Nro", "Tipo Hormiga", "Ubicación", "Sexo", "Ingesta Nativa", "GenoAlimento", "Estado"};
        model = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(model);
        table.setBackground(new Color(0x34495E)); // Azul más oscuro
        table.setForeground(textColor);
        table.setFont(new Font("Arial", Font.PLAIN, 16));
        JScrollPane tableScrollPane = new JScrollPane(table);
        tablePanel.add(tableScrollPane, BorderLayout.CENTER);
        mainPanel.add(tablePanel, BorderLayout.CENTER);

        // Listener para habilitar botones según la selección
        table.getSelectionModel().addListSelectionListener(e -> {
            boolean isSelected = table.getSelectedRow() != -1;
            alimentarButton.setEnabled(isSelected);
            eliminarButton.setEnabled(isSelected);
        });

        // Panel de Combos y Botones
        JPanel comboButtonPanel = new JPanel(new BorderLayout());
        comboButtonPanel.setBackground(backgroundColor);

        JPanel actionPanel = new JPanel(new GridBagLayout());
        actionPanel.setBackground(backgroundColor);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Etiqueta y ComboBox para GenoAlimento
        JLabel genoAlimentoLabel = new JLabel("Geno Alimento:");
        genoAlimentoLabel.setForeground(textColor);

        ArrayList<MLGenoAlimento> lstGenoAlimentos = new ArrayList<>();
        lstGenoAlimentos.add(new MLX("X"));
        lstGenoAlimentos.add(new MLXX("XX"));
        lstGenoAlimentos.add(new MLXY("XY"));

        genoCombo = new JComboBox<>(lstGenoAlimentos.toArray(new MLGenoAlimento[0]));

        // Etiqueta y ComboBox para IngestaNativa
        JLabel ingestaLabel = new JLabel("Ingesta Nativa:");
        ingestaLabel.setForeground(textColor);
        ingestaCombo = new JComboBox<>(new String[]{"Carnívoro", "Herbívoro", "Omnívoro", "Insectívoro", "Nectívoro"});

        // Agregar componentes al layout
        gbc.gridx = 0;
        gbc.gridy = 0;
        actionPanel.add(genoAlimentoLabel, gbc);

        gbc.gridx = 1;
        actionPanel.add(genoCombo, gbc);

        gbc.gridx = 2;
        actionPanel.add(ingestaLabel, gbc);

        gbc.gridx = 3;
        actionPanel.add(ingestaCombo, gbc);

        comboButtonPanel.add(actionPanel, BorderLayout.CENTER);

        // Panel para los botones de Crear, Alimentar, Eliminar, Guardar
        JPanel buttonPanel = new JPanel(new GridLayout(1, 4, 10, 10));
        buttonPanel.setBackground(new Color(0x1ABC9C)); // Verde azulado (fondo del panel de botones)
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Crear los botones
        JButton crearButton = new JButton("Crear Larva");
        alimentarButton = new JButton("Alimentar");
        eliminarButton = new JButton("Eliminar");
        guardarButton = new JButton("Guardar");

        // Deshabilitar botones de alimentar y eliminar hasta que se seleccione una hormiga
        alimentarButton.setEnabled(false);
        eliminarButton.setEnabled(false);

        // Añadir la acción de crear al botón
        crearButton.addActionListener(e -> {
            String tipoHormiga = "Larva";
            String ubicacion = "Aleatoria";  // Puedes cambiarlo a la ubicación deseada
            String sexo = "Asexual";
            String ingestaNativa = (String) ingestaCombo.getSelectedItem();
            String genoAlimento = ((MLGenoAlimento) genoCombo.getSelectedItem()).getTipo();
            String estado = "VIVA";

            // Añadir la nueva hormiga al modelo de la tabla
            model.addRow(new Object[]{secuencial++, tipoHormiga, ubicacion, sexo, ingestaNativa, genoAlimento, estado});
        });

        // Acción para alimentar hormiga
        alimentarButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                // Obtener datos actuales de la fila seleccionada
                String tipoHormiga = (String) model.getValueAt(selectedRow, 1);
                String ingestaNativa = (String) ingestaCombo.getSelectedItem();
                String genoAlimento = ((MLGenoAlimento) genoCombo.getSelectedItem()).getTipo();

                // Verificar si la combinación es XY y Nectívoro para evolución
                if (tipoHormiga.equals("Larva") && genoAlimento.equals("XY") && ingestaNativa.equals("Nectívoro")) {
                    tipoHormiga = "Zangano"; // Evolución a Zangano
                }

                // Actualizar el estado de la hormiga en la tabla
                model.setValueAt(ingestaNativa, selectedRow, 4);
                model.setValueAt(tipoHormiga, selectedRow, 1); // Actualizar el tipo de hormiga

                JOptionPane.showMessageDialog(this, "Larva ha sido alimentada y evolucionada a " + tipoHormiga + ".", "Alimentar", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Acción para eliminar hormiga
        eliminarButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                model.removeRow(selectedRow);
                JOptionPane.showMessageDialog(this, "Hormiga eliminada.", "Eliminar", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Acción para guardar datos
        guardarButton.addActionListener(e -> saveDataToCSV("MLData/MLHormiguero.csv"));

        // Personalizar botones
        Color buttonTextColor = Color.WHITE;

        crearButton.setBackground(buttonColor);
        crearButton.setForeground(buttonTextColor);

        alimentarButton.setBackground(buttonColor);
        alimentarButton.setForeground(buttonTextColor);

        eliminarButton.setBackground(buttonColor);
        eliminarButton.setForeground(buttonTextColor);

        guardarButton.setBackground(buttonColor);
        guardarButton.setForeground(buttonTextColor);

        // Agregar los botones al panel
        buttonPanel.add(crearButton);
        buttonPanel.add(alimentarButton);
        buttonPanel.add(eliminarButton);
        buttonPanel.add(guardarButton);

        comboButtonPanel.add(buttonPanel, BorderLayout.SOUTH);

        mainPanel.add(comboButtonPanel, BorderLayout.SOUTH);

        // Barra de estado
        JPanel statusBar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        statusBar.setBackground(backgroundColor); // Mantener coherencia en el color de fondo
        
        // Etiquetas de información personal
        JLabel programacionLabel = new JLabel("Programación II");
        JLabel cedulaLabel = new JLabel("Cédula: 1752137297");
        JLabel nombresLabel = new JLabel("Nombres: LEONEL MORALES");
        
        // Personalizar colores de las etiquetas
        programacionLabel.setForeground(textColor);
        cedulaLabel.setForeground(textColor);
        nombresLabel.setForeground(textColor);
        
        // Agregar etiquetas al status bar
        statusBar.add(programacionLabel);
        statusBar.add(new JLabel("               |"));
        statusBar.add(cedulaLabel);
        statusBar.add(new JLabel("               |"));
        statusBar.add(nombresLabel);
        
        add(statusBar, BorderLayout.SOUTH);
        
        // Mostrar la ventana
        setVisible(true);
    }

    private int loadDataFromCSV(String filePath) {
        int lastSecuencial = 1;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length > 0) {
                    try {
                        int sec = Integer.parseInt(data[0].trim());
                        if (sec >= lastSecuencial) {
                            lastSecuencial = sec + 1;
                        }
                    } catch (NumberFormatException e) {
                        // Ignorar líneas con formato incorrecto
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lastSecuencial;
    }

    private void saveDataToCSV(String filePath) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) { // 'true' para modo append
            for (int i = 0; i < model.getRowCount(); i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < model.getColumnCount(); j++) {
                    sb.append(model.getValueAt(i, j)).append(",");
                }
                sb.deleteCharAt(sb.length() - 1); // Eliminar la última coma
                bw.write(sb.toString());
                bw.newLine();
            }
            JOptionPane.showMessageDialog(this, "Datos guardados exitosamente.", "Guardar", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al guardar los datos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
     

