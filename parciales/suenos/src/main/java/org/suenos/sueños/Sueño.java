package org.suenos.sueños;

import org.suenos.Usuario;
import org.suenos.sueños.accionesPostSueño.AccionPostSueño;

import java.util.List;

public abstract class Sueño {
  private EstadoSueño estado;
  private List<AccionPostSueño> acciones;

  public void serCumplido(Usuario usuario) {
    marcarCumplido();
  };

  public boolean estaPendiente() {
    return estado.equals(EstadoSueño.PENDIENTE);
  }

  private void marcarCumplido() {
    estado = EstadoSueño.CUMPLIDO;
  }

  public abstract int getFelicidad(Usuario usuario);
}