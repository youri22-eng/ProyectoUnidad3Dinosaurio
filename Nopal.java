import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class Nopal {
    private int x, y, ancho, alto;
    private int velocidad = 6;

    private Image imagen;

    public Nopal(int x, int y) {
        this.x = x;
        this.y = y;
        this.ancho = 40;
        this.alto = 60;

        try {
            imagen = new ImageIcon("imagenes/cactus.png").getImage();
        } catch (Exception e) {
            System.out.println("No se pudo cargar cactus.png: " + e.getMessage());
        }
    }

    public void dibujar(Graphics g) {
        if (imagen != null) {
            g.drawImage(imagen, x, y, ancho, alto, null);
        } else {
            g.setColor(Color.GREEN);
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