package hu.hudakpe.flexion.network.wrapper;

import com.flexionmobile.codingchallenge.integration.Purchase;


/**
 * On one hand {@link DefaultPurchase} is the implementation of the {@link Purchase} interface, on the other, it
 * is a wrapper class for the Jackson JSON to object parser. It is mapped to the {@link Purchase} interface
 * through deserialization.
 *
 * @author Peter Hudak
 */
public class DefaultPurchase implements Purchase {

    private String id;
    private boolean consumed;
    private String itemId;

    /**
     * @return id of the {@link Purchase}
     */
    public String getId() {
        return id;
    }

    /**
     * @param id id of the {@link Purchase}
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the state of the consumed flag
     */
    public boolean getConsumed() {
        return consumed;
    }

    /**
     * @param consumed the state of the consumption state
     */
    public void setConsumed(boolean consumed) {
        this.consumed = consumed;
    }

    /**
     * @return the itemId of the {@link Purchase}
     */
    public String getItemId() {
        return itemId;
    }

    /**
     * @param itemId the chosen itemId for the {@link Purchase}
     */
    public void setItemId(String itemId) {
        this.itemId = itemId;
    }
}
