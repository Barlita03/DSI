package org.qmp.sugeridores;

import com.google.common.collect.Sets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.qmp.Usuario;
import org.qmp.prendas.Atuendo;
import org.qmp.prendas.Prenda;

public class SugeridorBasico implements Sugeridor {

  public List<Atuendo> generarSugerencias(Usuario usuario) {
    List<Prenda> prendasSuperiores = new ArrayList<Prenda>(usuario.getPrendasSuperiores());
    List<Prenda> prendasInferiores = new ArrayList<Prenda>(usuario.getPrendasInferiores());
    List<Prenda> calzados = new ArrayList<Prenda>(usuario.getCalzados());
    List<Prenda> accesorios = new ArrayList<Prenda>(usuario.getAccesorios());

    List<Atuendo> sugerencias = new ArrayList<Atuendo>();

    for (Prenda parteSuperior : prendasSuperiores) {
      for (Prenda parteInferior : prendasInferiores) {
        for (Prenda calzado : calzados) {
          for (Set<Prenda> combinacionAccesorios : Sets.powerSet(new HashSet<>(accesorios))) {
            sugerencias.add(
                new Atuendo(
                    parteSuperior,
                    parteInferior,
                    calzado,
                    new ArrayList<Prenda>(combinacionAccesorios)));
          }
        }
      }
    }

    return sugerencias;
  }
}
