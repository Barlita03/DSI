package org.noodle.servicios;

import java.util.List;

public interface GuitabSDK {
  void crearRepositorioConAccesos(String nombre, List<String> usernames);
  void darAcceso(String nombreRepo, String username);
  void quitarAcceso(String nombreRepo, String username);
}
