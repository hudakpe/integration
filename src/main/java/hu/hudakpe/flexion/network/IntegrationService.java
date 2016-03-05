package hu.hudakpe.flexion.network;

import com.flexionmobile.codingchallenge.integration.Purchase;
import hu.hudakpe.flexion.network.wrapper.Purchases;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * {@link IntegrationService} is the interface required by Retrofit library. Retrofit generates code based on
 * this interface for every network. All network calls are covered here that are defined in
 * the functional specification.
 *
 * @author Peter Hudak
 */
public interface IntegrationService {

    /**
     * Gets all of the purchases through a GET call from the Flexion Integration REST API
     *
     * @return collection of {@link Purchases}
     */
    @GET("all/")
    Call<Purchases> getAllPurchases();

    /**
     * Mark a {@link Purchase} that will be bought through a POST call to the Flexion Integration REST API
     *
     * @param itemId marks the chosen {@link Purchase}
     * @return the modified {@link Purchase}
     */
    @POST("buy/{itemId}")
    Call<Purchase> buyItem(@Path("itemId") String itemId);

    /**
     * Marks a {@link Purchase} for consumption thorugh a POST call to the Flexion Integration REST API
     *
     * @param purchaseId marks the chosen {@link Purchase}
     */
    @POST("consume/{purchaseId}")
    Call<Void> consumePurchase(@Path("purchaseId") String purchaseId);

}
