package com.dgraphproject.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import io.dgraph.DgraphClient;
import io.dgraph.DgraphGrpc.DgraphStub;

public class DPConnectionUtils {

    private InputStream inputStream;

    public DgraphClient connectDb() throws IOException {
        Properties properties = new Properties();
        String propertiesFileName = "database.properties";

        inputStream = getClass().getClassLoader().getResourceAsStream(propertiesFileName);
        if (inputStream != null) {
            properties.load(inputStream);
        } else {
            throw new FileNotFoundException("Properties file '" + propertiesFileName + "' not found in classpath");
        }

        String uri = properties.getProperty("CLOUD_URI");
        String apiKey = properties.getProperty("API_KEY");
        
        DgraphStub stub = DgraphClient.clientStubFromSlashEndpoint(uri, apiKey);
        DgraphClient dgraphClient = new DgraphClient(stub);

        return dgraphClient;
    }
}
