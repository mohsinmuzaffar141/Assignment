package helper;

import dataProvider.ConfigDataProvider;

import java.io.IOException;

public class DataProviderFactory 

{
	
	public static ConfigDataProvider getConfig() throws IOException {
		
		ConfigDataProvider config=new ConfigDataProvider();
		
		
		return config;
	}


}
