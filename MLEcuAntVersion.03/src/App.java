import javax.swing.SwingUtilities;

import MLGUI.MLUIComponent;

public class App {
    public static void main(String[] args) throws Exception {
        SwingUtilities.invokeLater(() -> new MLUIComponent().setVisible(true));
    }
}