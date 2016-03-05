package hu.hudakpe.flexion.network.wrapper;

import com.flexionmobile.codingchallenge.integration.Purchase;

import java.util.List;

/**
 * {@link Purchases} is a wrapper class for the Jackson library. It prepares Jackson to be able to handle lists
 * of {@link Purchases}.
 *
 * @author Peter Hudak
 */
public class Purchases {

    private List<Purchase> purchases;

    /**
     * @return the list of {@link Purchases}
     */
    public List<Purchase> getPurchases() {
        return purchases;
    }

    /**
     * @param purchases the list of {@link Purchases} to be set
     */
    public void setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
    }
}
