public class HiloPuntaje implements Runnable {
    private VentanaJuego panel;
    private boolean enEjecucion;

    public HiloPuntaje(VentanaJuego panel) {
        this.panel = panel;
        this.enEjecucion = true;
    }

    @Override
    public void run() {
        while (enEjecucion) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            panel.incrementarPuntaje();
        }
    }

    public void detener() {
        enEjecucion = false;
    }
}