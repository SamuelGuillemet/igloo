// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.suipro.unitaires;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import eu.telecomsudparis.csc4102.suipro.Activite;

class TestActivite {

	@BeforeEach
	void setUp() {
	}

	@AfterEach
	void tearDown() {
	}

	@Nested
	class TestContructeur {
		@Test
		void Test1Jeu1() throws Exception {
			Assertions.assertThrows(IllegalArgumentException.class, () -> new Activite(null, "id"));
		}

		@Test
		void Test1Jeu2() throws Exception {
			Assertions.assertThrows(IllegalArgumentException.class, () -> new Activite("", "id"));
		}

		@Test
		void Test2Jeu1() throws Exception {
			Assertions.assertThrows(IllegalArgumentException.class, () -> new Activite("nom", null));
		}

		@Test
		void Test2Jeu2() throws Exception {
			Assertions.assertThrows(IllegalArgumentException.class, () -> new Activite("nom", ""));
		}

		@Test
		void Test4Jeu1() throws Exception {
			Activite activite = new Activite("nom", "id");
			Assertions.assertNotNull(activite);
			Assertions.assertEquals("nom", activite.getNom());
			Assertions.assertEquals("id", activite.getId());
		}
	}

}
