import domain.MacoWins;
import domain.prendas.*;
import domain.ventas.*;
import junit.framework.TestCase;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

public class MacoWinsTests extends TestCase {

    // Test para verificar el precio de una prenda nueva
    public void testPrecioNueva() {
        Prenda camisa = new Prenda(100, Tipo.Camisa, new Nueva());
        assertEquals(100, camisa.precioDeVenta());
    }

    // Test para verificar el precio de una prenda en promoción
    public void testPrecioPromocion() {
        Prenda camisa = new Prenda(100, Tipo.Pantalon, new Promocion(20));
        assertEquals(80, camisa.precioDeVenta());
    }

    // Test para verificar el precio de una prenda en liquidación
    public void testPrecioLiquidacion() {
        Prenda camisa = new Prenda(100, Tipo.Saco, new Liquidacion());
        assertEquals(50, camisa.precioDeVenta());
    }

    // Test para verificar el cambio de estado de una prenda
    public void testCambioDeEstado() {
        Prenda camisa = new Prenda(100, Tipo.Camisa, new Nueva());
        assertEquals(100, camisa.precioDeVenta());

        camisa.setEstado(new Promocion(30));
        assertEquals(70, camisa.precioDeVenta());

        camisa.setEstado(new Promocion(50));
        assertEquals(50, camisa.precioDeVenta());

        camisa.setEstado(new Liquidacion());
        assertEquals(50, camisa.precioDeVenta());
    }

    // Test para verificar la ganancia de una venta en efectivo
    public void testGananciaDeVentaEnEfectivo() {
        List<Prenda> prendas = new ArrayList<>(
            Arrays.asList(
                new Prenda(30, Tipo.Camisa, new Nueva()),
                new Prenda(100, Tipo.Saco, new Nueva()),
                new Prenda(50, Tipo.Pantalon, new Nueva())
            )
        );

        Venta venta = new Venta("29/3/2025", prendas, new Efectivo());

        assertEquals(180, venta.ganancia());
    }

    // Test para verificar la ganancia de una venta con tarjeta
    public void testGananciaDeVentaConTarjeta() {
        List<Prenda> prendas = new ArrayList<>(
                Arrays.asList(
                        new Prenda(30, Tipo.Camisa, new Nueva()),
                        new Prenda(100, Tipo.Saco, new Nueva()),
                        new Prenda(50, Tipo.Pantalon, new Nueva())
                )
        );

        Venta venta = new Venta("29/3/2025", prendas, new Tarjeta(6, 10));

        assertEquals(241, venta.ganancia());
    }

    // Test para verificar la ganancia de una fecha en especifico
    public void testGananciasDeUnaFechaEnEspecifico() {
        List<Prenda> prendas = new ArrayList<>(
                Arrays.asList(
                        new Prenda(30, Tipo.Camisa, new Nueva()),
                        new Prenda(100, Tipo.Saco, new Nueva()),
                        new Prenda(50, Tipo.Pantalon, new Nueva())
                )
        );

        Venta venta1 = new Venta("29/3/2025", prendas, new Efectivo());
        Venta venta2 = new Venta("29/3/2025", prendas, new Efectivo());
        Venta venta3 = new Venta("29/3/2025", prendas, new Efectivo());

        MacoWins macoWins = new MacoWins(new ArrayList<>(Arrays.asList(venta1, venta2, venta3)));

        assertEquals(180 * 3, macoWins.gananciasDeUnDia("29/3/2025"));
    }
}
