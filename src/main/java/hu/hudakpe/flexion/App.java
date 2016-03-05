package hu.hudakpe.flexion;

import com.flexionmobile.codingchallenge.integration.Integration;
import com.flexionmobile.codingchallenge.integration.IntegrationTestRunner;

public class App {


    public static void main(String[] args) {

        System.out.println("Hello World!");

//		DefaultIntegration defaultIntegration = new DefaultIntegration();
//
//
//		defaultIntegration.getPurchases();

        Integration defaultIntegration = new DefaultIntegration("alma");
        IntegrationTestRunner testRunner = new IntegrationTestRunner();
        testRunner.runTests(defaultIntegration);

//		defaultIntegration.buy("blabla");
//
//		DefaultPurchase purch = new DefaultPurchase();
//		purch.setId("c70a5f33-51c3-4b54-a3f5-6114aea72360");
//
//		defaultIntegration.consume(purch);


        System.out.printf("");


    }
}
