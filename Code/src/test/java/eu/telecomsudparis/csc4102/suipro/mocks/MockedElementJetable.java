// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.suipro.mocks;

import java.util.concurrent.Flow.Subscription;

import eu.telecomsudparis.csc4102.suipro.ICorbeille;
import eu.telecomsudparis.csc4102.suipro.IElementJetable;

public class MockedElementJetable implements IElementJetable {
    private Subscription subscription;

    public IElementJetable onNextElement;

    public MockedElementJetable() {
    }

    @Override
    public boolean estEnFonctionnement() {
        throw new UnsupportedOperationException("Unimplemented method 'estEnFonctionnement'");
    }

    @Override
    public void mettreALaCorbeille(ICorbeille corbeille) {
        throw new UnsupportedOperationException("Unimplemented method 'mettreALaCorbeille'");
    }

    @Override
    public void restaurer(ICorbeille corbeille) {
        throw new UnsupportedOperationException("Unimplemented method 'restaurer'");
    }

    @Override
    public void onComplete() {
        throw new UnsupportedOperationException("Unimplemented method 'onComplete'");
    }

    @Override
    public void onError(Throwable arg0) {
        throw new UnsupportedOperationException("Unimplemented method 'onError'");
    }

    @Override
    public void onNext(IElementJetable arg0) {
        this.onNextElement = arg0;
        this.subscription.request(1);
    }

    @Override
    public void onSubscribe(Subscription arg0) {
        this.subscription = arg0;
        this.subscription.request(1);
    }
}
