import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class Pajaro {
    private int x, y, ancho, alto;
    private int velocidad;

    private Image imagen;

    public Pajaro(int x, int velocidadInicial) {
        this.x = x;
        this.y = 260;
        this.ancho = 30;
        this.alto = 25;
        this.velocidad = velocidadInicial;

        try {
            imagen = new ImageIcon("imagenes/pajaro.png").getImage();
        } catch (Exception e) {
            System.out.println("No se pudo cargar pajaro.png: " + e.getMessage());
        }
    }

    public void dibujar(Graphics g) {
        if (imagen != null) {
            g.drawImage(imagen, x, y, ancho, alto, null);
        } else {
            g.setColor(Color.BLUE);
            g.fillRect(x, y, ancho, alto);
        }
    }

    public void actualizar() {
        x -= velocidad;
    }

    public int getX() {
        return x;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, ancho, alto);
    }
}