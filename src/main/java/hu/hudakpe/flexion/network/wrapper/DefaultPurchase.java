package hu.hudakpe.flexion.network.wrapper;

import com.flexionmobile.codingchallenge.integration.Purchase;

public class DefaultPurchase implements Purchase {

    private String id;
    private boolean consumed;
    private String itemId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean getConsumed() {
        return consumed;
    }

    public void setConsumed(boolean consumed) {
        this.consumed = consumed;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }
}
