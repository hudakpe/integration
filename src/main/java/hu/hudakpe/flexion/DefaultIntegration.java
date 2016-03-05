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

/**
 * {@link DefaultIntegration} is an implementation of the {@link Integration} interface. The task of this class is
 * wrapping the network communication with the Flexion Integration REST API and it provides methods to start these
 * calls and wait for their results in a synchronous way. The following tasks are supported:
 * <ul>
 * <li>Buying a chosen purchase</li>
 * <li>Getting all of the purchases</li>
 * <li>Consuming a chosen purchase</li>
 * </ul>
 * <p/>
 * The Flexion Integration REST API supports a developerId parameter. It is not covered in the {@link Integration}
 * interface at all. This parameter can be set through the appropriate constructor of DefaultIntegration, otherwise
 * the default "developer" string is used. This parameter cannot be modified after an object is created because
 * it is not efficient to recreate all of the dependencies, so the creation of a new {@link DefaultIntegration}
 * object is suggested.
 * <p/>
 * Other responsibilities of the {@link DefaultIntegration} class is the configuration of the Retrofit network library
 * and the Jackson JSON parser.
 * <p/>
 * The base URL is given in the documentation of the API, so it is hardcoded to this class. Please consider modifying
 * this class to be able to handle new URLs in the future, if it is mandatory!
 *
 * @author Peter Hudak
 */
public class DefaultIntegration implements Integration {

    private static final String BASE_URL = "http://dev2.flexionmobile.com/javachallenge/rest/developer/";

    private Retrofit retrofit;
    private IntegrationService integrationService;
    private String developerId;

    /**
     * Configures Jackson and Retrofit libraries, sets the developerId to "developer"
     */
    public DefaultIntegration() {

        this.developerId = "developer";
        ObjectMapper mapper = configureJackson();
        configureRetrofit(mapper);
    }

    /**
     * Configures Jackson and Retrofit libraries, sets the developerId to the developerId input parameter
     *
     * @param developerId the developerId to create the connection URL
     */
    public DefaultIntegration(String developerId) {
        this.developerId = developerId;

        ObjectMapper mapper = configureJackson();
        configureRetrofit(mapper);
    }

    /**
     * @return the developerId that is part of the connection URL
     */
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
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL + developerId + "/")
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .build();
        integrationService = retrofit.create(IntegrationService.class);
    }

    /**
     * Buys a chosen {@link Purchase}
     *
     * @param itemId identifies the selected {@link Purchase}
     * @return the bought {@link Purchase}, if the buying process is successful
     */
    public Purchase buy(String itemId) {
        Purchase purchase = null;
        try {
            purchase = integrationService.buyItem(itemId).execute().body();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Sorry, something went wrong... there might be problems with your connection...");
        }
        return purchase;
    }

    /**
     * Gives back a list of existing {@link Purchase}s
     *
     * @return a list of {@link Purchase}s
     */
    public List<Purchase> getPurchases() {
        List<Purchase> purchases = null;
        try {
            purchases = integrationService.getAllPurchases().execute().body().getPurchases();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Sorry, something went wrong... there might be problems with your connection...");

        }
        return purchases;
    }

    /**
     * Marks a chosen {@link Purchase}, and set its state to consumed
     *
     * @param purchase the {@link Purchase} to be modified
     */
    public void consume(Purchase purchase) {
        try {
            integrationService.consumePurchase(purchase.getId()).execute();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Sorry, something went wrong... there might be problems with your connection...");
        }
    }
}
