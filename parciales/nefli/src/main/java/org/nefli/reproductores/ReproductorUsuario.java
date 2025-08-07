package org.nefli.reproductores;

import org.nefli.usuario.Usuario;

public abstract class ReproductorUsuario {
  protected Reproductor reproductor;
  protected Usuario usuario;
  protected Contenido contenido;
  protected int tiempoMaximo;

  public abstract void reproducir();

  public abstract void pausar();

  public abstract void finalizar();

  public int getResolucion(Contenido contenido) {
    return 0; // Obtengo el maximo en comun entre la resolucion del reproductor y el del contenido
  }
}
