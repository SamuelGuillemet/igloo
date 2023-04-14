// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.suipro.unitaires;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import eu.telecomsudparis.csc4102.suipro.SuiPro;
import eu.telecomsudparis.csc4102.util.OperationImpossible;

class TestSuiPro {

    @Nested
    class Constructeur {

        @Test
        void Test1Jeu1() throws Exception {
            Assertions.assertThrows(OperationImpossible.class, () -> new SuiPro(null));
        }

        @Test
        void Test1Jeu2() throws Exception {
            Assertions.assertThrows(OperationImpossible.class, () -> new SuiPro(""));
        }

        @Test
        void Test2() throws Exception {
            SuiPro suiPro = new SuiPro("projetx");
            Assertions.assertNotNull(suiPro);
            Assertions.assertEquals("projetx", suiPro.getNomDeProjet());
        }
    }
}
