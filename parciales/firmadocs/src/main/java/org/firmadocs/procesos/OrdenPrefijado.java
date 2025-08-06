package org.firmadocs.procesos;

import org.firmadocs.acciones.Firma;
import org.firmadocs.acciones.Lectura;
import org.firmadocs.usuarios.Usuario;

public class OrdenPrefijado extends Proceso {
  public OrdenPrefijado(BorradorProceso borrador) {
    super(borrador);
  }

  @Override
  public void enviarAlSiguienteLector(Lectura lectura) {
    lecturas.get(lecturas.indexOf(lectura) + 1).serEnviadaAUsuario();
  }

  @Override
  public void enviarAlSiguienteFirmante(Firma firma) {
    firmas.get(firmas.indexOf(firma) + 1).serEnviadaAUsuario();
  }
}
