package org.qmp.sastres;

import org.qmp.prendas.Borrador;
import org.qmp.prendas.Formalidad;
import org.qmp.prendas.Prenda;
import org.qmp.prendas.TipoDePrenda;
import org.qmp.prendas.materiales.Color;
import org.qmp.prendas.materiales.Material;

public class SastreJhonson extends Sastre {
  @Override
  protected Prenda fabricarParteSuperior() {
    Borrador borrador = new Borrador(TipoDePrenda.CAMISA);
    borrador.setFormalidad(Formalidad.FORMAL);
    borrador.setMaterial(Material.TELA_ALGODON);
    borrador.setColorPrincipal(new Color(0, 0, 0));

    return borrador.crearPrenda();
  }

  @Override
  protected Prenda fabricarParteInferior() {
    Borrador borrador = new Borrador(TipoDePrenda.PANTALON);
    borrador.setFormalidad(Formalidad.FORMAL);
    borrador.setMaterial(Material.ACETATO);
    borrador.setColorPrincipal(new Color(0, 0, 0));

    return borrador.crearPrenda();
  }

  @Override
  protected Prenda fabricarCalzado() {
    Borrador borrador = new Borrador(TipoDePrenda.ZAPATO);
    borrador.setFormalidad(Formalidad.FORMAL);
    borrador.setMaterial(Material.CUERO);
    borrador.setColorPrincipal(new Color(0, 0, 0));

    return borrador.crearPrenda();
  }
}
