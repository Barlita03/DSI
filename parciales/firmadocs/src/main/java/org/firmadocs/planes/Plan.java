package org.firmadocs.planes;

import org.firmadocs.procesos.BorradorProceso;
import org.firmadocs.procesos.OrdenIndistinto;
import org.firmadocs.procesos.OrdenPrefijado;
import org.firmadocs.procesos.Proceso;

public abstract class Plan {
  protected int docsPorMes;
  protected int docsEsteMes = 0;

  public Proceso crearOrdenIndistinto(BorradorProceso borrador) {
    validarProcesosDisponibles();
    return new OrdenIndistinto(borrador);
  }

  public Proceso crearOrdenPrefijado(BorradorProceso borrador) {
    validarProcesosDisponibles();
    return new OrdenPrefijado(borrador);
  }

  private void validarProcesosDisponibles() {
    if(docsPorMes >= docsEsteMes) {
      throw new RuntimeException("Limite mensual superado");
    }
  }
}
