package org.primerParcial;

import java.util.ArrayList;
import java.util.List;

public class TransmisionesEnCurso {
  private static final TransmisionesEnCurso instancia = new TransmisionesEnCurso();
  private static final List<Transmision> transmisiones = new ArrayList<>();

  // --- Metodos ---
  public static TransmisionesEnCurso getInstancia() {
    return instancia;
  }

  public List<Transmision> getTransmisiones() {
    return new ArrayList<>(transmisiones);
  }

  public static void agregarTransmision(Transmision transmision) {
    transmisiones.add(transmision);
  }

  public static void eliminarTransmision(Transmision transmision) {
    transmisiones.remove(transmision);
  }

  public static void limpiarLista() {
    transmisiones.clear();
  }
}
