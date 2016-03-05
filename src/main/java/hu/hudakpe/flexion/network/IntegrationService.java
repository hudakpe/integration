package hu.hudakpe.flexion.network;

import com.flexionmobile.codingchallenge.integration.Purchase;
import hu.hudakpe.flexion.network.model.Purchases;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface IntegrationService {

    @GET("all/")
    Call<Purchases> getAllPurchases();

    @POST("buy/{itemId}")
    Call<Purchase> buyItem(@Path("itemId") String itemId);

    @POST("consume/{purchaseId}")
    Call<Void> consumePurchase(@Path("purchaseId") String purchaseId);

}
