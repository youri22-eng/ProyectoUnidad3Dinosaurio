public class HiloJuego implements Runnable {
    private VentanaJuego panel;
    private boolean enEjecucion;

    public HiloJuego(VentanaJuego panel) {
        this.panel = panel;
        this.enEjecucion = true;
    }

    @Override
    public void run() {
        while (enEjecucion) {
            panel.actualizarJuego();
            panel.repaint();

            try {
                Thread.sleep(15);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void detener() {
        enEjecucion = false;
    }
}