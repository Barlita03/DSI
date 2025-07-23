package org.qmp;

import org.junit.jupiter.api.Test;
import org.qmp.sugeridores.Sugeridor;
import org.qmp.sugeridores.SugeridorBasico;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class GuardarropasTest {
  Sugeridor sugeridor = new SugeridorBasico();
  Usuario usuario1 = new Usuario(21, sugeridor);
  Usuario usuario2 = new Usuario(21, sugeridor);

  @Test
  public void unGuardarropasPuedeSerCompartidoEntreVariosUsuarios() {
    Guardarropa guardarropa = new Guardarropa("Criterio", List.of(usuario1, usuario2));

    assertTrue(usuario1.getGuardarropas().contains(guardarropa) && usuario2.getGuardarropas().contains(guardarropa));
  }

  @Test
  public void sePuedeAgregarAUnUsuarioAlGuardarropa() {
    Guardarropa guardarropa = new Guardarropa("Criterio", List.of(usuario1));

    assertTrue(usuario1.getGuardarropas().contains(guardarropa) && !usuario2.getGuardarropas().contains(guardarropa));

    guardarropa.agregarUsuario(usuario2);

    assertTrue(usuario1.getGuardarropas().contains(guardarropa) && usuario2.getGuardarropas().contains(guardarropa));
  }

  @Test
  public void sePuedeQuitarAUnUsuarioAlGuardarropa() {
    Guardarropa guardarropa = new Guardarropa("Criterio", List.of(usuario1, usuario2));

    assertTrue(usuario1.getGuardarropas().contains(guardarropa) && usuario2.getGuardarropas().contains(guardarropa));

    guardarropa.quitarUsuario(usuario2);

    assertTrue(usuario1.getGuardarropas().contains(guardarropa) && !usuario2.getGuardarropas().contains(guardarropa));
  }
}
