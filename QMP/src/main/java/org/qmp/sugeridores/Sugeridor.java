package org.qmp.sugeridores;

import java.util.List;
import org.qmp.Usuario;
import org.qmp.prendas.Atuendo;

public interface Sugeridor {
  List<Atuendo> generarSugerencias(Usuario usuario);
}
