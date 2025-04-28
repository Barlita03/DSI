package org.qmp.sastres;

import org.qmp.prendas.Borrador;
import org.qmp.prendas.Color;
import org.qmp.prendas.Material;
import org.qmp.prendas.Prenda;
import org.qmp.prendas.TipoDePrenda;

public class SastreJhonson extends Sastre {
  @Override
  protected Prenda fabricarParteSuperior() {
    Borrador borrador = new Borrador(TipoDePrenda.CAMISA);
    borrador.setMaterial(Material.TELA_ALGODON);
    borrador.setColorPrincipal(new Color(0, 0, 0));

    return borrador.crearPrenda();
  }

  @Override
  protected Prenda fabricarParteInferior() {
    Borrador borrador = new Borrador(TipoDePrenda.PANTALON);
    borrador.setMaterial(Material.ACETATO);
    borrador.setColorPrincipal(new Color(0, 0, 0));

    return borrador.crearPrenda();
  }

  @Override
  protected Prenda fabricarCalzado() {
    Borrador borrador = new Borrador(TipoDePrenda.ZAPATO);
    borrador.setMaterial(Material.CUERO);
    borrador.setColorPrincipal(new Color(0, 0, 0));

    return borrador.crearPrenda();
  }
}
