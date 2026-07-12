import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class VentanaJuego extends JPanel implements KeyListener {

    private Dinosaurio dino;
    private Nopal cactus;
    private Pajaro pajaro;
    private boolean haypajaro;

    private int puntaje;
    private boolean juegoTerminado;

    private int velocidadActual = 6;
    private int obstaculosEsquivados = 0;

    private HiloJuego hiloJuego;
    private HiloPuntaje hiloPuntaje;

    public VentanaJuego() {
        setBackground(Color.WHITE);
        setFocusable(true);
        addKeyListener(this);

        dino = new Dinosaurio(50, 280);
        cactus = new Nopal(500, 280);
        pajaro = new Pajaro(900, velocidadActual);
        haypajaro = false;

        puntaje = 0;
        juegoTerminado = false;

        iniciarHilos();
    }

    private void iniciarHilos() {
        hiloJuego = new HiloJuego(this);
        hiloPuntaje = new HiloPuntaje(this);

        Thread t1 = new Thread(hiloJuego);
        Thread t2 = new Thread(hiloPuntaje);

        t1.start();
        t2.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.BLACK);
        g.drawLine(0, 340, 800, 340);

        dino.dibujar(g);

        if (haypajaro) {
            pajaro.dibujar(g);
        } else {
            cactus.dibujar(g);
        }

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 18));
        g.drawString("Puntaje: " + puntaje, 650, 30);

        if (juegoTerminado) {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 30));
            g.drawString("GAME OVER", 300, 150);
            g.setFont(new Font("Arial", Font.PLAIN, 16));
            g.drawString("Presiona ENTER para reiniciar", 270, 180);
        }
    }

    public void actualizarJuego() {
        if (juegoTerminado) return;

        dino.actualizar();

        if (haypajaro) {
            pajaro.actualizar();

            if (pajaro.getX() < -30) {
                obstaculosEsquivados++;
                aumentarVelocidad();
                generarSiguienteObstaculo();
            }

            if (dino.getBounds().intersects(pajaro.getBounds())) {
                terminarJuego();
            }

        } else {
            cactus.actualizar();

            if (cactus.getX() < -30) {
                obstaculosEsquivados++;
                aumentarVelocidad();
                generarSiguienteObstaculo();
            }

            if (dino.getBounds().intersects(cactus.getBounds())) {
                terminarJuego();
            }
        }
    }

    private void generarSiguienteObstaculo() {
        haypajaro = Math.random() < 0.5;

        if (haypajaro) {
            pajaro = new Pajaro(800, velocidadActual);
        } else {
            cactus = new Nopal(800, 280);
            cactus.setVelocidad(velocidadActual);
        }
    }

    private void aumentarVelocidad() {
        if (obstaculosEsquivados % 5 == 0) {
            velocidadActual++;
            cactus.setVelocidad(velocidadActual);
            pajaro.setVelocidad(velocidadActual);
        }
    }

    private void terminarJuego() {
        juegoTerminado = true;
        hiloJuego.detener();
        hiloPuntaje.detener();
    }

    public void incrementarPuntaje() {
        puntaje++;
    }

    private void reiniciarJuego() {
        dino = new Dinosaurio(50, 280);
        cactus = new Nopal(800, 280);
        pajaro = new Pajaro(900, 8);
        haypajaro = false;
        velocidadActual = 8;
        obstaculosEsquivados = 0;
        puntaje = 0;
        juegoTerminado = false;
        iniciarHilos();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_UP) {
            dino.saltar();
        }
        if (e.getKeyCode() == KeyEvent.VK_ENTER && juegoTerminado) {
            reiniciarJuego();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}