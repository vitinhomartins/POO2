public class TesteCestaFrutas {
    public static void main(String[] args) {
        CestaFrutas cesta = new CestaFrutas("Cesta de frutas 1");
        CestaFrutasInterativa cestaInterativa = new CestaFrutasInterativa("Cesta frutas 2");
        cestaInterativa.start();
        cesta.start();
    }
}
