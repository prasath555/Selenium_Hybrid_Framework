package prasath.Testcase;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import prasath.Base.BaseTest;
import prasath.XLdata.XLUtility;

public class Test_Excel extends BaseTest {
	
	@DataProvider(name = "LoginData")
	public Object[][] getData() throws IOException
	{
		
	/*	Object loginData [][]=
			  {
				{ "anshika@gmail.com","Iamking@000"},
		        {" shetty@gmail.com","Iamking@000"},
		        {"shetty@gmail.com","Iamking@"}
		     }; */
		
		//get data from excel
		//path were the excel file is located
		
		//xlsx -> workbook -> 2007 or later (new xl files )
		//xlx -> workbook -> 2003  (old xl files )
		
		String path = "src/test/java/prasath/XLdata/exceldata.xlsx";
		
		//usage of utility file (import + visibility public)
		
		XLUtility xlutil = new XLUtility(path);
		
		int totalrows = xlutil.getRowCount("Sheet1");   //gets total rows
		int totalcols = xlutil.getCellCount("Sheet1",1); // gets total columns
		
		String loginData[][] = new String[totalrows][totalcols];
		
		for(int i =1;i<=totalrows;i++)
		{
			for(int j =0;j<totalcols;j++)
			{
				loginData[i-1][j] = xlutil.getCellData("Sheet1", i, j); //we are ignoring the header parts 
			} //index at array//i=0,j=0                   //i=1, j=0 (index postion in XL)
		}
		
		return loginData;
	}

	@Test(dataProvider="LoginData")
	public void LoginErrorValidation(String email,String password) {

		
		landingPage.loginApplication(email, password);
   /*     
          String Exp_title = "Let's Shop";
          String act_title = driver.getTitle();
          
          if(status.equals("valid"))
          {
          if(Exp_title.equals(act_title)) 
          {
        	  driver.findElement(By.cssSelector(".fa-sign-out")).click();
        	  Assert.assertTrue(true);
          }
          else
          {
        	  Assert.assertFalse(false);
          }
          }
          else if(status.equals("invalid"))
          {
        	  if(Exp_title.equals(act_title))
        	  {
        		  driver.findElement(By.cssSelector(".fa-sign-out")).click();
        		  Assert.assertFalse(false);
        	  }
        	  else
        	  {
        		  Assert.assertTrue(true); 
        	  }
          }
	}*/
}

}
