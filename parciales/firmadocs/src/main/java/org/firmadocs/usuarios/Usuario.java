package org.firmadocs.usuarios;

import org.firmadocs.acciones.Accion;
import org.firmadocs.generadorDeFirmas.GeneradorDeFirmasInterface;
import org.firmadocs.notificadores.Notificador;
import org.firmadocs.planes.Plan;
import org.firmadocs.procesos.BorradorProceso;
import org.firmadocs.procesos.Proceso;

import java.util.List;

public class Usuario {
  private String nombre;
  private String apellido;
  private String email;
  private String telefono;
  private Plan plan;
  private List<Accion> acciones;
  private List<Notificador> notificadores;
  private GeneradorDeFirmasInterface generadorDeFirma;

  public boolean tieneAccionesPendientes() {
    return acciones.stream().anyMatch(a -> !a.estaRealizada());
  }

  public List<Proceso> procesosDeFirmaCompartidos() {
    return acciones.stream().map(Accion::getProceso).toList();
  }

  public void recibirNotificacion(String mensaje) {
    notificadores.forEach(n -> n.notificar(this, mensaje));
  }

  public String generarFirma() {
    return generadorDeFirma.generarFirma(this);
  }

  public Proceso crearOrdenIndistinto(BorradorProceso borrador) {
    return plan.crearOrdenIndistinto(borrador);
  }

  public Proceso crearOrdenPrefijado(BorradorProceso borrador) {
    return plan.crearOrdenPrefijado(borrador);
  }

  public String getNombre() {
    return nombre;
  }

  public String getApellido() {
    return apellido;
  }

  public String getMail() {
    return email;
  }

  public String getTelefono() {
    return telefono;
  }
}
