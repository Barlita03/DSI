package org.firmadocs.procesos;

import org.firmadocs.acciones.Accion;
import org.firmadocs.acciones.Firma;
import org.firmadocs.acciones.Lectura;
import org.firmadocs.usuarios.Usuario;

import java.util.LinkedList;
import java.util.List;

public abstract class Proceso {
  protected final String documento;
  protected final Usuario creador;
  protected EstadoProceso estado = EstadoProceso.EN_PROCESO;
  protected final LinkedList<Firma> firmas = new LinkedList<>();
  protected final LinkedList<Lectura> lecturas = new LinkedList<>();

  public Proceso (BorradorProceso borrador) {
    this.documento = borrador.getDocumento();
    this.creador = borrador.getCreador();

    this.firmas.addAll(crearAccionesFirmas(borrador.getFirmadores()));
    this.lecturas.addAll(crearAccionesLecturas(borrador.getLectores()));
  }

  private LinkedList<Firma> crearAccionesFirmas(LinkedList<Usuario> firmantes) {
    LinkedList<Firma> acciones = new LinkedList<>();

    for (Usuario firmante : firmantes) {
      acciones.add(new Firma(firmante, this));
    }

    return acciones;
  }

  private LinkedList<Lectura> crearAccionesLecturas(LinkedList<Usuario> lectores) {
    LinkedList<Lectura> acciones = new LinkedList<>();

    for (Usuario lector : lectores) {
      acciones.add(new Lectura(lector, this));
    }

    return acciones;
  }

  private void finalizarProceso() {
    this.firmas.clear();
    this.lecturas.clear();
    setEstado(EstadoProceso.FINALIZADO);
  }

  public void setEstado(EstadoProceso estado) {
    this.estado = estado;
  }

  private boolean todosCumplen() {
    return firmas.stream().allMatch(Accion::estaRealizada) &&
        lecturas.stream().allMatch(Accion::estaRealizada);
  }

  public void notificarAlCreador(String mensaje) {
    creador.recibirNotificacion(mensaje);
  }

  public List<Usuario> getLectoresActuales() {
    return lecturas.stream().filter(Accion::estaRealizada).map(Accion::getUsuario).toList();
  }

  public List<Usuario> getFirmantesActuales() {
    return firmas.stream().filter(Accion::estaRealizada).map(Accion::getUsuario).toList();
  }

  public void serAnulado() {
    finalizarProceso();
    setEstado(EstadoProceso.ANULADO);
  }

  public void recibirAccionCompleta() {
    if(todosCumplen()) {
      finalizarProceso();
    }
  }

  public void enviarATodos() {}
  public void enviarAlSiguienteLector(Lectura lectura) {}
  public void enviarAlSiguienteFirmante(Firma firma) {}
}
