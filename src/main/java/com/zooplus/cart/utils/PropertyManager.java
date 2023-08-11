package com.zooplus.cart.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public enum PropertyManager
{
    INSTANCE;

    private Properties props;

    PropertyManager()
    {
        this.props = new Properties();
        try
        {
            this.props.load(new FileInputStream("src/test/resources/defaultTestConfiguration.properties"));
        }
        catch (IOException e)
        {
            System.out.println("Configuration properties file cannot be found");
        }
    }

    public String getBaseURL ()
    {
        return this.props.getProperty("baseURL");
    }
}
