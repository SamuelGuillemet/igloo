// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.smarttsi.unitaires;

import java.time.Instant;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import eu.telecomsudparis.csc4102.smarttsi.Envoi;
import eu.telecomsudparis.csc4102.util.Datutil;
import eu.telecomsudparis.csc4102.util.OperationImpossible;

public class TestEnvoi {

	@Before
	public void setUp() {
	}

	@After
	public void tearDown() {
	}

	@Test(expected = IllegalArgumentException.class)
	public void constructeurEnvoiTest1Jeu1() throws OperationImpossible {
		new Envoi(null, "origine", Datutil.maintenant(), "destination");
	}

	@Test(expected = IllegalArgumentException.class)
	public void constructeurEnvoiTest1Jeu2() throws OperationImpossible {
		new Envoi("", "origine", Datutil.maintenant(), "destination");
	}

	@Test(expected = IllegalArgumentException.class)
	public void constructeurEnvoiTest2Jeu1() throws OperationImpossible {
		new Envoi("identifiant", null, Datutil.maintenant(), "destination");
	}

	@Test(expected = IllegalArgumentException.class)
	public void constructeurEnvoiTest2Jeu2() throws OperationImpossible {
		new Envoi("identifiant", "", Datutil.maintenant(), "destination");
	}

	@Test(expected = IllegalArgumentException.class)
	public void constructeurEnvoiTest3() throws OperationImpossible {
		new Envoi("identifiant", "origine", null, "destination");
	}

	@Test(expected = IllegalArgumentException.class)
	public void constructeurEnvoiTest4() throws OperationImpossible {
		new Envoi("identifiant", "origine", Datutil.ajouterJoursAInstant(Datutil.maintenant(), 1), "destination");
	}

	@Test(expected = IllegalArgumentException.class)
	public void constructeurEnvoiTest5Jeu1() throws OperationImpossible {
		new Envoi("identifiant", "origine", Datutil.maintenant(), null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void constructeurEnvoiTest5Jeu2() throws OperationImpossible {
		new Envoi("identifiant", "origine", Datutil.maintenant(), "");
	}

	@Test
	public void constructeurEnvoiTest6() throws OperationImpossible {
		Instant maintenant = Datutil.maintenant();
		Envoi envoi = new Envoi("identifiant", "origine", maintenant, "destination");
		Assert.assertEquals("identifiant", envoi.getIdentifiant());
		Assert.assertEquals("origine", envoi.getOrigine());
		Assert.assertEquals(maintenant, envoi.getInstantDepot());
		Assert.assertEquals("destination", envoi.getDestination());
		Assert.assertNull(envoi.getInstantLivraison());
	}
}
