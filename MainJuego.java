import javax.swing.JFrame;

public class MainJuego {
    public static void main(String[] args) {
        JFrame ventana = new JFrame("Juego del Dinosaurio");
        VentanaJuego panel = new VentanaJuego();

        ventana.add(panel);
        ventana.setSize(800, 400);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setResizable(false);
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);

        panel.requestFocusInWindow();
    }
}