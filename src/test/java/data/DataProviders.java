
package data;

import org.testng.annotations.DataProvider;

public class DataProviders {

	
	@DataProvider(name = "dp")
	public static Object[][] getData()
	{
Object[][] data = new Object[3][4];
		
		data[0][0]= "Hongkong CURA Healthcare Center";
		data[0][1]= "Medicaid";
		data[0][2]= "14/06/2020";
		data[0][3]= "test";
		
		data[1][0]= "Tokyo CURA Healthcare Center";		
		data[1][1]= "Medicare";
		data[1][2]= "14/05/2020";		
		data[1][3]= "test";
		
		data[2][0]= "Seoul CURA Healthcare Center";
		data[2][1]= "None";
		data[2][2]= "14/07/2020";
		data[2][3]= "test";
		
		return data;
	}
}
