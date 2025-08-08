package org.suenos.sueños.accionesPostSueño;

import org.suenos.Usuario;

import java.util.ArrayList;
import java.util.List;

public class MultiplicarBuenaPlata implements AccionPostSueño {
  private List<AccionPostSueño> acciones = new ArrayList<>();

  public MultiplicarBuenaPlata() {
    acciones.add(new GanaLoQueQuiere());
    acciones.add(new ViajaA("Rio"));
  }

  @Override
  public void serRealizada(Usuario usuario) {
    if (usuario.getCantidadDeHijos() < 3) {
      usuario.multiplicarBuenaPlata(2);
    } else {
      usuario.multiplicarBuenaPlata(3);
    }

    acciones.forEach(a -> a.serRealizada(usuario));
  }
}