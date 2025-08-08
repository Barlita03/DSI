package org.suenos.sueños.accionesPostSueño;

import org.suenos.Usuario;

import java.util.ArrayList;
import java.util.List;

public class SumarFelicidonios implements AccionPostSueño {
  private int cantidad;
  private final List<AccionPostSueño> acciones = new ArrayList<>();

  public SumarFelicidonios(int cuantos) {
    cantidad = cuantos;

    acciones.add(new ResignarSueñoMasImportante());
  }

  @Override
  public void serRealizada(Usuario usuario) {
    usuario.sumarFelicidonios(cantidad);
    acciones.forEach(a -> a.serRealizada(usuario));
  }
}
