package org.copia.me.servicios;

import org.copia.me.algoritmosDeDeteccion.AlgoritmoDeDeteccion;
import org.copia.me.calidadesDelServicio.CalidadDelServicio;
import org.copia.me.documentos.Documento;
import org.copia.me.revisiones.GestorDeRevisores;
import org.copia.me.revisiones.Revision;

import java.util.ArrayList;
import java.util.List;

public abstract class Servicio {
  private int numero;
  private String mailUsuario;
  private String mailAdmin;
  private AlgoritmoDeDeteccion algoritmoDeDeteccion;
  private CalidadDelServicio calidadDelServicio;
  private final List<Documento> documentosARevisar = new ArrayList<>();
  private final List<Revision> revisionesPendientes = new ArrayList<>();
  private MailSender emailer;

  public Servicio (String mailUsuario, String mailAdmin, AlgoritmoDeDeteccion algoritmoDeDeteccion, CalidadDelServicio calidadDelServicio, List<Documento> documentos) {
    this.mailUsuario = mailUsuario;
    this.mailAdmin = mailAdmin;
    this.algoritmoDeDeteccion = algoritmoDeDeteccion;
    this.calidadDelServicio = calidadDelServicio;
    documentosARevisar.addAll(documentos);
  }

  abstract void generarRevisiones();

  private List<Revision> generarRevisionesManuales(int cantidad) {
    List<Revision> revisionesManuales = revisionesPendientes.subList(0, cantidad - 1).stream().map(Revision::clonar).toList();

    revisionesPendientes.addAll(revisionesManuales);

    return revisionesManuales;
  }

  public void agregarRevision(Revision revision) {
    revisionesPendientes.add(revision);
  }

  public void deteccionAutomatica() {
    algoritmoDeDeteccion.serEjecutado();
  }

  public void revisionManual(int porcentajeDeRevision) {
    GestorDeRevisores.repartir(generarRevisionesManuales(obtenerCantidad(porcentajeDeRevision)));
  }

  public void revisionCruzada(int porcentajeDeRevision) {
    // TODO
  }

  private int obtenerCantidad(int porcentajeDeRevision) {
    return porcentajeDeRevision * revisionesPendientes.size() / 100;
  }
}
