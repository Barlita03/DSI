package org.nefli.reproductores;

import java.util.List;

public interface Reproductor {
  List<Integer> resolucionesSoportadas();
  boolean play(int, int, int);
}
