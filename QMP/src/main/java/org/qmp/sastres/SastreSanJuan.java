package org.qmp.sastres;

import org.qmp.prendas.Borrador;
import org.qmp.prendas.Color;
import org.qmp.prendas.Material;
import org.qmp.prendas.Prenda;
import org.qmp.prendas.TipoDePrenda;

public class SastreSanJuan extends Sastre {
  @Override
  protected Prenda fabricarParteSuperior() {
    Borrador borrador = new Borrador(TipoDePrenda.CHOMBA);
    borrador.setMaterial(Material.PIQUE);
    borrador.setColorPrincipal(new Color(0, 255, 0));

    return borrador.crearPrenda();
  }

  @Override
  protected Prenda fabricarParteInferior() {
    Borrador borrador = new Borrador(TipoDePrenda.PANTALON);
    borrador.setMaterial(Material.ACETATO);
    borrador.setColorPrincipal(new Color(10, 10, 10));

    return borrador.crearPrenda();
  }

  @Override
  protected Prenda fabricarCalzado() {
    Borrador borrador = new Borrador(TipoDePrenda.ZAPATILLA);
    borrador.setMaterial(Material.CUERO);
    borrador.setColorPrincipal(new Color(0, 0, 0));

    return borrador.crearPrenda();
  }
}
