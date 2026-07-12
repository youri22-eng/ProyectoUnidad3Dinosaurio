import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class Dinosaurio {
    private int x, y, ancho, alto;
    private int velY;
    private boolean saltando;
    // CAMBIOS EN EL SALTO
    private final int GRAVEDAD = 1;
    private final int FUERZA_SALTO = -20;
    //private final int SUELO_Y = 300;
    private final int SUELO_Y = 280;
    private Image imagen;

    public Dinosaurio(int x, int y) {
        this.x = x;
        this.y = y;
        //this.ancho = 40;
        //this.alto = 40;
        this.ancho = 60;
        this.alto = 60;
        this.velY = 0;
        this.saltando = false;

        try {
            imagen = new ImageIcon("imagenes/dino.png").getImage();
        } catch (Exception e) {
            System.out.println("No se pudo cargar dino.png: " + e.getMessage());
        }
    }

    public void dibujar(Graphics g) {
        if (imagen != null) {
            g.drawImage(imagen, x, y, ancho, alto, null);
        } else {
            g.setColor(Color.BLACK);
            g.fillRect(x, y, ancho, alto);
        }
    }

    public void saltar() {
        if (!saltando) {
            velY = FUERZA_SALTO;
            saltando = true;
        }
    }

    public void actualizar() {
        if (saltando) {
            y += velY;
            velY += GRAVEDAD;
            if (y >= SUELO_Y) {
                y = SUELO_Y;
                velY = 0;
                saltando = false;
            }
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, ancho, alto);
    }
}