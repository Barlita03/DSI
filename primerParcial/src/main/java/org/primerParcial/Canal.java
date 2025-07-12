package org.primerParcial;

import java.util.List;

public class Canal {
  private List<Canal> suscripciones;
  private List<Byte> muestrasDeApoyo;
  private List<Transmision> historicoTransmisiones;

  // --- Metodos ---

  public void suscribirse(Canal canal) {
    suscripciones.add(canal);
  }

  public void darMuestraDeApoyo(Canal canal, Byte numero) {
    if(canal.equals(this)) {
      throw new RuntimeException("No podes enviarte muestras de apoyo a vos mismo");
    }

    canal.recibirApoyo(numero);
  }

  public void visualizarTransmision(Transmision transmision) {
    transmision.agregarVisualizador(this);
  }

  public void recibirApoyo(Byte numero) {
    muestrasDeApoyo.add(numero);
  }

  public void agregarAlHistorico(Transmision transmision) {
    historicoTransmisiones.add(transmision);
  }
}
