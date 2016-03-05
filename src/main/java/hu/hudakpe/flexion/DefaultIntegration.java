package hu.hudakpe.flexion;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.flexionmobile.codingchallenge.integration.Integration;
import com.flexionmobile.codingchallenge.integration.Purchase;
import hu.hudakpe.flexion.network.IntegrationService;
import hu.hudakpe.flexion.network.wrapper.DefaultPurchase;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.util.List;

public class DefaultIntegration implements Integration {

    private static final String BASE_URL = "http://dev2.flexionmobile.com/javachallenge/rest/developer/";

    private Retrofit retrofit;
    private IntegrationService integrationService;
    private String developerId;

    public DefaultIntegration() {

        this.developerId = "developer";
        ObjectMapper mapper = configureJackson();
        configureRetrofit(mapper);
    }

    public DefaultIntegration(String developerId) {
        this.developerId = developerId;

        ObjectMapper mapper = configureJackson();
        configureRetrofit(mapper);
    }

    public String getDeveloperId() {
        return developerId;
    }

    private ObjectMapper configureJackson() {
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addAbstractTypeMapping(Purchase.class, DefaultPurchase.class);
        objectMapper.registerModule(simpleModule);
        return objectMapper;
    }

    private void configureRetrofit(ObjectMapper objectMapper) {
        retrofit = new Retrofit.Builder().baseUrl(BASE_URL + developerId + "/").addConverterFactory(JacksonConverterFactory.create(objectMapper)).build();
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
