// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.suipro.validation;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import eu.telecomsudparis.csc4102.suipro.SuiPro;
import eu.telecomsudparis.csc4102.util.OperationImpossible;

class TestSuiPro {
    private SuiPro suiPro;

    @BeforeEach
    void setUp() throws OperationImpossible {
        suiPro = new SuiPro("projetx");
    }

    @AfterEach
    void tearDown() {
        suiPro = null;
    }

    @Test
    void Test1() throws Exception {
        Assertions.assertEquals("projetx", suiPro.getNomDeProjet());
    }
}
