package org.qmp.sugeridores;

import java.util.List;
import org.qmp.Atuendo;
import org.qmp.Usuario;

public interface Sugeridor {
  List<Atuendo> generarSugerencias(Usuario usuario);
}
