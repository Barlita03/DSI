package org.example.listasdecorreo;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.example.mensajes.Borrador;

public class MensajeModerado {
  @SuppressFBWarnings(value = "EI_EXPOSE_REP2")
  private final ListaDeCorreo lista;

  @SuppressFBWarnings(value = "EI_EXPOSE_REP2")
  private final Borrador borrador;

  // --- Constructor ---

  public MensajeModerado(ListaDeCorreo lista, Borrador borrador) {
    this.lista = lista;
    this.borrador = borrador;
  }

  // --- Getters ---

  @SuppressFBWarnings(value = "EI_EXPOSE_REP")
  public Borrador getBorrador() {
    return borrador;
  }

  // --- Metodos ---

  public void serAceptado() {
    lista.enviarTodosLosMiembros(borrador);
    lista.quitarMensajeDeLaEspera(this);
  }

  public void serRechazado() {
    lista.quitarMensajeDeLaEspera(this);
  }
}
