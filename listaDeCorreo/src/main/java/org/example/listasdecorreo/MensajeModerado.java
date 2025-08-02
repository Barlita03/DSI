package org.example.listasdecorreo;

import org.example.mensajes.Borrador;

public class MensajeModerado {
  private final ListaDeCorreo lista;
  private final Borrador borrador;

  // --- Constructor ---

  public MensajeModerado(ListaDeCorreo lista, Borrador borrador) {
    this.lista = lista;
    this.borrador = borrador;
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
