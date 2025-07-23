package org.qmp.sugeridores;

import java.util.List;
import org.qmp.prendas.Atuendo;
import org.qmp.usuarios.Usuario;

public interface Sugeridor {
  List<Atuendo> generarSugerencias(Usuario usuario);
}
