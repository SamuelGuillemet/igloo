//CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.suipro.unitaires;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import eu.telecomsudparis.csc4102.suipro.Label;
import eu.telecomsudparis.csc4102.util.OperationImpossible;

public class TestLabel {

    @Nested
    class Constructeur {
        @Test
        void Test1Jeu1() throws Exception {
            Assertions.assertThrows(OperationImpossible.class, () -> new Label(null, "label1"));
        }

        @Test
        void Test1Jeu2() throws Exception {
            Assertions.assertThrows(OperationImpossible.class, () -> new Label("", "label1"));
        }

        @Test
        void Test2Jeu1() throws Exception {
            Assertions.assertThrows(OperationImpossible.class, () -> new Label("labelId", null));
        }

        @Test
        void Test2Jeu2() throws Exception {
            Assertions.assertThrows(OperationImpossible.class, () -> new Label("labelId", ""));
        }

        @Test
        void Test3() throws Exception {
            Label label = new Label("labelId", "label1");
            Assertions.assertNotNull(label);
            Assertions.assertEquals("labelId", label.getId());
            Assertions.assertEquals("label1", label.getNom());
        }
    }
}
