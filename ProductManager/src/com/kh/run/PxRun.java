package com.kh.run;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public class PxRun {

	public static void main(String[] args) {
		Properties prop = new Properties();
		
		try {
			prop.store(new FileOutputStream("resources/driver.properties"), "driver.properties");
			prop.store(new FileOutputStream("resources/query.xml"), "query.xml") ;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
