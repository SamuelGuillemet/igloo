// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.smarttsi.validation;

import java.time.Instant;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import eu.telecomsudparis.csc4102.smarttsi.SmartTSI;
import eu.telecomsudparis.csc4102.util.Datutil;
import eu.telecomsudparis.csc4102.util.OperationImpossible;

public class TestDeposerUnEnvoi {
	private SmartTSI smartTSI;
	private String identifiantEnvoi;
	private String origine;
	private String destination;

	@Before
	public void setUp() throws OperationImpossible {
		smartTSI = new SmartTSI();
		identifiantEnvoi = "identifiantEnvoi1";
		origine = "origine1";
		destination = "destination1";
	}

	@After
	public void tearDown() {
		smartTSI = null;
		identifiantEnvoi = null;
		origine = null;
		destination = null;
	}

	@Test(expected = OperationImpossible.class)
	public void accepterLaLivraisonDUnEnvoiTest1Jeu1() throws OperationImpossible {
		smartTSI.deposerUnEnvoi(null, origine, destination);
	}

	@Test(expected = OperationImpossible.class)
	public void deposerUnEnvoiTest1Jeu2() throws OperationImpossible {
		smartTSI.deposerUnEnvoi("", origine, destination);
	}

	@Test(expected = OperationImpossible.class)
	public void deposerUnEnvoiTest2Jeu1() throws OperationImpossible {
		smartTSI.deposerUnEnvoi(identifiantEnvoi, null, destination);
	}

	@Test(expected = OperationImpossible.class)
	public void deposerUnEnvoiTest2Jeu2() throws OperationImpossible {
		smartTSI.deposerUnEnvoi(identifiantEnvoi, "", destination);
	}

	@Test(expected = OperationImpossible.class)
	public void deposerUnEnvoiTest3Jeu1() throws OperationImpossible {
		smartTSI.deposerUnEnvoi(identifiantEnvoi, origine, null);
	}

	@Test(expected = OperationImpossible.class)
	public void deposerUnEnvoiTest3Jeu2() throws OperationImpossible {
		smartTSI.deposerUnEnvoi(identifiantEnvoi, origine, "");
	}

	@Test
	public void deposerUnEnvoiTest5Puis4() throws OperationImpossible {
		System.out.println(Datutil.maintenant() + ", " + Datutil.maintenant());
		Instant instant = Datutil.maintenant();
		System.out.println(Datutil.instantEstAvant(instant, Datutil.maintenant()));
		Assert.assertTrue(smartTSI.listeLesEnvois().isEmpty());
		smartTSI.deposerUnEnvoi(identifiantEnvoi, origine, destination);
		Assert.assertEquals(1, smartTSI.listeLesEnvois().size());
		Assert.assertEquals(identifiantEnvoi, smartTSI.listeLesEnvois().get(0));
		Assert.assertThrows(OperationImpossible.class,
				() -> smartTSI.deposerUnEnvoi(identifiantEnvoi, origine, destination));
	}

	@Test(expected = OperationImpossible.class)
	public void deposerUnEnvoiTest5Puis4Bis() throws OperationImpossible {
		System.out.println(Datutil.maintenant() + ", " + Datutil.maintenant());
		Instant instant = Datutil.maintenant();
		System.out.println(Datutil.instantEstAvant(instant, Datutil.maintenant()));
		Assert.assertTrue(smartTSI.listeLesEnvois().isEmpty());
		try {
			smartTSI.deposerUnEnvoi(identifiantEnvoi, origine, destination);
		} catch (OperationImpossible ex) {
			Assert.fail();
		}
		Assert.assertEquals(1, smartTSI.listeLesEnvois().size());
		Assert.assertEquals(identifiantEnvoi, smartTSI.listeLesEnvois().get(0));
		smartTSI.deposerUnEnvoi(identifiantEnvoi, origine, destination);
	}
}
