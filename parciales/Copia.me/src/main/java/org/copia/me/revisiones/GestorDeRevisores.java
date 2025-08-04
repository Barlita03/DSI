package org.copia.me.revisiones;

import org.copia.me.servicios.MailSender;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class GestorDeRevisores {
  private static final GestorDeRevisores instancia = new GestorDeRevisores();
  private static final List<Revisor> revisores = new ArrayList<>();

  public static GestorDeRevisores getInstancia() {
    return instancia;
  }

  public static List<Revisor> getRevisoresDisponibles() {
    return revisores.stream().filter(Revisor::estaDisponible).toList();
  }

  public void agregarRevisor(Revisor revisor) {
    revisores.add(revisor);
  }

  public void removerRevisor(Revisor revisor) {
    revisores.remove(revisor);
  }

  public static void repartir(List<Revision> revisiones) {
    List<Revisor> revisoresDisponibles = getRevisoresDisponibles();
    int contador = 0;

    while (!revisiones.isEmpty() && contador < revisoresDisponibles.size()) {
      Revisor revisor = revisoresDisponibles.get(contador);
      int cantidad = revisor.cantidadDeRevisionesQuePuedeAceptar();

      List<Revision> revisionesAagregar = revisiones.subList(0, Math.min(cantidad, revisiones.size()));

      revisor.agregarRevisiones(revisionesAagregar);
      revisiones.removeAll(revisionesAagregar);
    }

    if (!revisiones.isEmpty()) {
      throw new RuntimeException("No hay suficientes revisores");
    }
  }

  public static void repartirCruzado(List<Revision> revisiones) {
    // TODO
  }
}
