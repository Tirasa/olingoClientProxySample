package net.tirasa.test.olingoproxysample;

import net.tirasa.olingoproxysample.net.azurewebsites.odatae2etest.microsoft.test.odata.services.odatawcfservice.InMemoryEntities;
import net.tirasa.olingoproxysample.net.azurewebsites.odatae2etest.microsoft.test.odata.services.odatawcfservice.types.Account;
import net.tirasa.olingoproxysample.net.azurewebsites.odatae2etest.microsoft.test.odata.services.odatawcfservice.types.PaymentInstrument;
import net.tirasa.olingoproxysample.net.azurewebsites.odatae2etest.microsoft.test.odata.services.odatawcfservice.types.Subscription;
import org.apache.olingo.client.api.v4.EdmEnabledODataClient;
import org.apache.olingo.ext.proxy.EntityContainerFactory;

public class Main {

    public static void main(final String[] args) {
        // First of all, get container factory
        final EntityContainerFactory<EdmEnabledODataClient> containerFactory =
                EntityContainerFactory.getV4("http://odatae2etest.azurewebsites.net/javatest/DefaultService");

        // Get container
        final InMemoryEntities container = containerFactory.getEntityContainer(InMemoryEntities.class);

        // Get entity from entity set
        final Account account = container.getAccounts().get(101);

        // Get some properties
        System.out.println("Account ID: " + account.getAccountID());
        System.out.println("Country: " + account.getCountry());
        System.out.println("Account Info: "
                + account.getAccountInfo().getFirstName() + " " + account.getAccountInfo().getLastName());
        
        // Navigate
        System.out.println("Number of active subcriptions: " + account.getActiveSubscriptions().count());
        for (Subscription subscription: account.getActiveSubscriptions()) {
            System.out.println("\tSubscription ID: " + subscription.getSubscriptionID());
        }
        
        // Contained
        final PaymentInstrument instrument = account.getMyPaymentInstruments().get(101901);
        System.out.println("Contained instrument - friendly name : " + instrument.getFriendlyName());
    }
}
