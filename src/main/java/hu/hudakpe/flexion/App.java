package hu.hudakpe.flexion;

import com.flexionmobile.codingchallenge.integration.Integration;
import com.flexionmobile.codingchallenge.integration.IntegrationTestRunner;

public class App {

    public static void main(String[] args) {

        System.out.println("Running tests is started...");
        Integration defaultIntegration = new DefaultIntegration("peterhudak");
        IntegrationTestRunner testRunner = new IntegrationTestRunner();
        testRunner.runTests(defaultIntegration);
        System.out.println("Running tests is finished...");
    }
}
