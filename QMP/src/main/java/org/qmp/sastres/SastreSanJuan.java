package org.qmp.sastres;

import org.qmp.prendas.Borrador;
import org.qmp.prendas.Prenda;
import org.qmp.prendas.atributos.Formalidad;
import org.qmp.prendas.atributos.TipoDePrenda;
import org.qmp.prendas.materiales.Color;
import org.qmp.prendas.materiales.Material;

public class SastreSanJuan extends Sastre {
  @Override
  protected Prenda fabricarParteSuperior() {
    Borrador borrador = new Borrador(TipoDePrenda.CHOMBA);
    borrador.setFormalidad(Formalidad.FORMAL);
    borrador.setMaterial(Material.PIQUE);
    borrador.setColorPrincipal(new Color(0, 255, 0));

    return borrador.crearPrenda();
  }

  @Override
  protected Prenda fabricarParteInferior() {
    Borrador borrador = new Borrador(TipoDePrenda.PANTALON);
    borrador.setFormalidad(Formalidad.FORMAL);
    borrador.setMaterial(Material.ACETATO);
    borrador.setColorPrincipal(new Color(10, 10, 10));

    return borrador.crearPrenda();
  }

  @Override
  protected Prenda fabricarCalzado() {
    Borrador borrador = new Borrador(TipoDePrenda.ZAPATILLA);
    borrador.setFormalidad(Formalidad.INFORMAL);
    borrador.setMaterial(Material.CUERO);
    borrador.setColorPrincipal(new Color(0, 0, 0));

    return borrador.crearPrenda();
  }
}
