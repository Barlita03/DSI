package org.firmadocs.procesos;

import org.firmadocs.acciones.Accion;
import org.firmadocs.acciones.Lectura;

public class OrdenIndistinto extends Proceso {
  public OrdenIndistinto(BorradorProceso borrador) {
    super(borrador);
    enviarATodos();
  }

  @Override
  public void enviarATodos() {
    firmas.forEach(Accion::serEnviadaAUsuario);
    lecturas.forEach(Accion::serEnviadaAUsuario);
  }
}
