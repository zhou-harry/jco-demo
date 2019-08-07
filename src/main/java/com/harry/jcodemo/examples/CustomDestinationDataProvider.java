package com.harry.jcodemo.examples;

import java.util.HashMap;
import java.util.Properties;

import com.harry.jcodemo.jco.JcoDestinationDataProvider;
import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoDestinationManager;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.ext.DataProviderException;
import com.sap.conn.jco.ext.DestinationDataEventListener;
import com.sap.conn.jco.ext.DestinationDataProvider;
import com.sap.conn.jco.ext.Environment;

/**
 * Each application using Java Connector 3 deals with destinations. A destination represents a logical address
 * of an ABAP system and thus separates the destination configuration from application logic. JCo retrieves
 * the destination parameters required at runtime from DestinationDataProvider and ServerDataProvider registered
 * in the runtime environment. If no provider is registered, JCo uses the default implementation that reads the
 * configuration from a properties file. It is expected that each environment provides a suitable
 * implementation that meets security and other requirements. Furthermore, only one DestinationDataProvider
 * and only one ServerDataProvider can be registered per process. The reason behind this design decision
 * is the following: the provider implementations are part of the environment infrastructure.
 * The implementation should not be application specific, and in particular must be separated from
 * application logic.
 * <p>
 * This example demonstrates a simple implementation of the DestinationDataProvider interface and shows how
 * to register it. A real world implementation should save the configuration data in a secure way.
 */
public class CustomDestinationDataProvider {

    //business logic
    void executeCalls(String destName) {
        JCoDestination dest;
        try {
            dest = JCoDestinationManager.getDestination(destName);
            dest.ping();
            System.out.println("Destination " + destName + " works");
        } catch (JCoException e) {
            e.printStackTrace();
            System.out.println("Execution on destination " + destName + " failed");
        }
    }

    static Properties getDestinationPropertiesFromUI() {
        //adapt parameters in order to configure a valid destination
        Properties connectProperties = new Properties();
        connectProperties.setProperty(DestinationDataProvider.JCO_ASHOST, "10.1.118.73");
        connectProperties.setProperty(DestinationDataProvider.JCO_SYSNR, "06");
        connectProperties.setProperty(DestinationDataProvider.JCO_CLIENT, "140");
        connectProperties.setProperty(DestinationDataProvider.JCO_USER, "ILAS");
        connectProperties.setProperty(DestinationDataProvider.JCO_PASSWD, "Tplhk12345");
        connectProperties.setProperty(DestinationDataProvider.JCO_LANG, "zh");
        return connectProperties;
    }

    public static void main(String[] args) {

        JcoDestinationDataProvider myProvider = new JcoDestinationDataProvider();

        //register the provider with the JCo environment;
        //catch IllegalStateException if an instance is already registered
        try {
            Environment.registerDestinationDataProvider(myProvider);
        } catch (IllegalStateException providerAlreadyRegisteredException) {
            //somebody else registered its implementation, 
            //stop the execution
            throw new Error(providerAlreadyRegisteredException);
        }

        String destName = "ABAP_AS";
        CustomDestinationDataProvider test = new CustomDestinationDataProvider();

        //set properties for the destination and ...
        myProvider.addDestinationProperties(destName, getDestinationPropertiesFromUI());
        //... work with it
        test.executeCalls(destName);

        //now remove the properties and ...
        myProvider.addDestinationProperties(destName, null);
        //... and let the test fail
        test.executeCalls(destName);
    }

}
