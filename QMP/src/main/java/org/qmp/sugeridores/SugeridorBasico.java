package org.qmp.sugeridores;

import java.util.ArrayList;
import java.util.List;
import org.qmp.Atuendo;
import org.qmp.Usuario;
import org.qmp.prendas.Prenda;

public class SugeridorBasico implements Sugeridor {

  public List<Atuendo> generarSugerencias(Usuario usuario) {
    List<Prenda> prendasSuperiores = new ArrayList<Prenda>(usuario.getPrendasSuperiores());
    List<Prenda> prendasInferiores = new ArrayList<Prenda>(usuario.getPrendasInferiores());
    List<Prenda> calzados = new ArrayList<Prenda>(usuario.getCalzados());

    List<Atuendo> sugerencias = new ArrayList<Atuendo>();

    for (Prenda parteSuperior : prendasSuperiores) {
      for (Prenda parteInferior : prendasInferiores) {
        for (Prenda calzado : calzados) {
          sugerencias.add(new Atuendo(parteSuperior, parteInferior, calzado));
        }
      }
    }

    return sugerencias;
  }
}
