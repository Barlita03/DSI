import domain.prendas.*;
import junit.framework.TestCase;

public class MacoWinsTests extends TestCase {

    // Test para verificar el precio de una prenda nueva
    public void testPrecioNueva() {
        Prenda camisa = new Prenda(100, Tipo.Camisa, new Nueva());
        assertEquals(100, camisa.precioDeVenta());
    }

    // Test para verificar el precio de una prenda en promoción
    public void testPrecioPromocion() {
        Prenda camisa = new Prenda(100, Tipo.Camisa, new Promocion(20));
        assertEquals(80, camisa.precioDeVenta());
    }

    // Test para verificar el precio de una prenda en liquidación
    public void testPrecioLiquidacion() {
        Prenda camisa = new Prenda(100, Tipo.Camisa, new Liquidacion());
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
}
