package org.qmp;

import org.junit.jupiter.api.Test;
import org.qmp.exceptions.NullParamException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Qmp_PI_Test {

  @Test
  public void verificoElTipoYCategoriaDeUnaPrenda() {
    Prenda prenda = new Prenda(TipoDePrenda.REMERA, Material.TELA_ALGODON, new Color(255, 255, 255));

    assertEquals(TipoDePrenda.REMERA, prenda.getTipo());
    assertEquals(Categoria.PARTE_SUPERIOR, prenda.categoria());
  }

  @Test
  public void verificoQueLaPrendaTieneUnSoloColor() {
    Prenda prenda = new Prenda(TipoDePrenda.REMERA, Material.TELA_ALGODON, new Color(255, 255, 255));

    assertNull(prenda.getColorSecundario());
  }

  @Test
  public void verificoQueLaPrendaTieneDosColores() {
    Prenda prenda = new Prenda(TipoDePrenda.REMERA, Material.TELA_ALGODON, new Color(255, 255, 255), new Color(0, 0, 0));

    assertNotNull(prenda.getColorSecundario());
  }

  @Test
  public void rompeSiElTipoDePrendaEsNulo() {
    assertThrows(NullParamException.class, () -> {
      new Prenda(null, Material.TELA_ALGODON, new Color(255, 255, 255));
    });
  }

  @Test
  public void rompeSiMaterialEsNulo() {
    assertThrows(NullParamException.class, () -> {
      new Prenda(TipoDePrenda.REMERA, null, new Color(255, 255, 255));
    });
  }

  @Test
  public void rompeSiColorEsNulo() {
    assertThrows(NullParamException.class, () -> {
      new Prenda(TipoDePrenda.REMERA, Material.TELA_ALGODON, null);
    });
  }
}
