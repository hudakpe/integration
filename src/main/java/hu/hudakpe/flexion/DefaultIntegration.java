package hu.hudakpe.flexion;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.flexionmobile.codingchallenge.integration.Integration;
import com.flexionmobile.codingchallenge.integration.Purchase;
import hu.hudakpe.flexion.network.IntegrationService;
import hu.hudakpe.flexion.network.model.DefaultPurchase;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.util.List;

public class DefaultIntegration implements Integration {

    private String BASE_URL = "http://dev2.flexionmobile.com/javachallenge/rest/developer/";

    private Retrofit retrofit;
    private IntegrationService integrationService;

    public DefaultIntegration(String developerId) {

        BASE_URL += developerId + "/";

        ObjectMapper mapper = new ObjectMapper();

        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addAbstractTypeMapping(Purchase.class, DefaultPurchase.class);
        mapper.registerModule(simpleModule);

        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(JacksonConverterFactory.create(mapper)).build();
        integrationService = retrofit.create(IntegrationService.class);
    }

    public Purchase buy(String s) {
        Purchase purchase = null;
        try {
            purchase = integrationService.buyItem(s).execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return purchase;
    }

    public List<Purchase> getPurchases() {
        List<Purchase> purchases = null;
        try {
            purchases = integrationService.getAllPurchases().execute().body().getPurchases();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return purchases;

    }

    public void consume(Purchase purchase) {
        try {
            integrationService.consumePurchase(purchase.getId()).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
