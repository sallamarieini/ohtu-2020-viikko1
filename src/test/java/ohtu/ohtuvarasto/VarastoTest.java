package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() { varasto = new Varasto(10); }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void kayttokelvotonVarastoLuodaan() {
        Varasto varasto2;
        varasto2 = new Varasto(-2);
        assertEquals(0.0, varasto2.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaaNegatiivinenMaara() {
        varasto.lisaaVarastoon(-5);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaaEnemmanKuinMahtuu() {
        varasto.lisaaVarastoon(15);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void otaVarastostaNegatiivinenMaara() {
        assertEquals(0.0, varasto.otaVarastosta(-4), vertailuTarkkuus);
    }

    @Test
    public void otaVarastostaEnemmanKuinSiellaOn() {
        varasto.lisaaVarastoon(5);
        assertEquals(5, varasto.otaVarastosta(7), vertailuTarkkuus);
    }

    @Test
    public void otaEnemmanKuinOnSaldoNollautuu() {
        varasto.lisaaVarastoon(5);
        varasto.otaVarastosta(7);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void toStringTarkastus() {
        assertEquals("saldo = 0.0, vielä tilaa 10.0", varasto.toString());
    }

    @Test
    public void luoKuormitettuVarastoTilavuus() {
        Varasto kuormitettuVarasto = new Varasto(10, 2);
        assertEquals(10, kuormitettuVarasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void luoKuormitettuVarastoSaldo() {
        Varasto kuormitettuVarasto = new Varasto(10, 2);
        assertEquals(2, kuormitettuVarasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void luoKuormitettuVarastoNegatiivinenTilavuus() {
        Varasto v = new Varasto(-10, 2);
        assertEquals(0.0, v.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void luoKuormitettuVarastoNegSaldo() {
        Varasto v = new Varasto(10, -3);
        assertEquals(0.0, v.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void luoKuormitettuVarastoAlkuSaldoSuurempiKuinTilavuus() {
        Varasto v = new Varasto(10, 13);
        assertEquals(10, v.getSaldo(), vertailuTarkkuus);
    }
}