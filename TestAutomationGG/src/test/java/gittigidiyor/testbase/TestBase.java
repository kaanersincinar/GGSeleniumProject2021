/**
 * 
 */
package gittigidiyor.testbase;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import junit.framework.ComparisonFailure;

/**
 * @author Kaan Ersin ÇINAR
 *
 */
public class TestBase{
	 
	public static String webDriverexe= "driver/geckodriver.exe";
	 
	public static WebDriver driver=null;
	
	public static void main(String[]args) throws InterruptedException {
		
		// Defining variables
		
		String userName = "testtest176733";
		
		String passWord = "5XzWrEVMi4mcF8r";
		
		String searchWord="bilgisayar";
		
		System.setProperty("webdriver.gecko.driver",webDriverexe);
		
		driver = new FirefoxDriver();

		//Entering the Home Page and Checking 
		
		driver.navigate().to("https://www.gittigidiyor.com/");
		
		String titleHome=driver.getTitle();
		
		try {
			assertEquals(titleHome,"GittiGidiyor - Türkiye'nin Öncü Alışveriş Sitesi" );
			
			System.out.println("Successfully entered to Gittigidiyor.");
		    } 
		
		catch(Exception e){
	    
	    }
		
		//Logging in and Checking
		driver.navigate().to("https://www.gittigidiyor.com/uye-girisi");	
   
        driver.findElement(By.id("L-UserNameField")).sendKeys(userName);
        
        driver.findElement(By.id("L-PasswordField")).click();
      
        driver.findElement(By.id("L-PasswordField")).sendKeys(passWord);
        
        driver.findElement(By.id("gg-login-enter")).click();
        
        Thread.sleep(500);
        
        driver.findElement(By.xpath("//span[contains(.,'testtest176733')]")).getText();
        
        Thread.sleep(500);
        
        if(userName.equals("testtest176733")) {
        	
        	
           System.out.println("Login Successful");
           
        }
        else {
        	System.out.println("Error at Login step.");
        }
        
        //Searching Bilgisayar and Moving to the Second Page and Checking
        
        driver.findElement(By.xpath("//input[@name='k']")).click();
        
        Thread.sleep(500);
        
        driver.findElement(By.xpath("//input[@name='k']")).sendKeys(searchWord);
        
        Thread.sleep(500);
        
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        
        Thread.sleep(500);
        
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        
        jse.executeScript("window.scrollBy(0,10000)");
        
        driver.findElement(By.xpath("//a[contains(text(),'2')]")).click();
        
        Thread.sleep(500);
        
        if (driver.getCurrentUrl().contains("sf=2")){
        	
            System.out.println("Moved to Second Page Successful");
        }
        else
        {
            System.out.println("Something went wrong when moving to the second page.");
        
        }
        
		//Choosing a Product Randomly, Adding to the Cart and Checking
        
        driver.findElement(By.xpath("/html/body/div[5]/div[2]/div/div[2]/div[3]/div[2]/ul/li[2]/a/div/div/div[1]/div[2]/div")).click();
       
        Thread.sleep(500);
       
        jse.executeScript("window.scrollBy(0,200)");
       
        Thread.sleep(500);
        
        driver.findElement(By.xpath("//*[@id=\"add-to-basket\"]")).click();
       
        String productPrice= driver.findElement(By.xpath("//*[@id=\"sp-price-discountPrice\"]")).getText();
       
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div/div[4]/div[3]/a/div[2]/span")).click();
       
        Thread.sleep(500);
      
        String cartPrice = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/form/div/div[2]/div[3]/div/div[1]/div/div[5]/div[2]/div[3]")).getText();
   
        if(cartPrice.equalsIgnoreCase(productPrice)) {
        	System.out.println("Product Price: "+productPrice+" Price at the Cart: "+cartPrice+" Price is Correct.");
        }
        else {
        	System.out.println("Something went wrong with the prices.");
        }
        
        //Increasing amount at the Cart and Checking
       
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/form/div/div[2]/div[2]/div[1]/div/div[6]/div[2]/div[2]/div[1]/div[4]/div/div[2]/select")).click();
       
        Thread.sleep(500);
        
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/form/div/div[2]/div[2]/div[1]/div/div[6]/div[2]/div[2]/div[1]/div[4]/div/div[2]/select/option[2]")).click();

        String quantity= driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/form/div/div[2]/div[2]/div[1]/div/div[6]/div[2]/div[2]/div[1]/div[4]/div/div[2]/select/option[2]")).getText();
        
        Thread.sleep(1500);
        
        String desiredAmount="2";
        
        if(quantity.equals(desiredAmount)) {
        	
        System.out.println("Successfully increased to 2.");
        
        }
        else {
        	System.out.println("Something went wrong when increasing the amount.");
        }
        
        //Deleting products at the cart
        
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/form/div/div[2]/div[2]/div[1]/div/div[6]/div[2]/div[2]/div[1]/div[3]/div/div[2]/div/a[1]/i")).click();
        
        Thread.sleep(500);
      
        String emptyCart= driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/form/div/div[2]/div[2]/div[1]/div/div[1]/div[1]/div[1]/div/div[2]/h2")).getText();
		
        Thread.sleep(500);
        
        String emptyCartMessage = "Sepetinizde ürün bulunmamaktadır.";
        
        if(emptyCart.equals(emptyCartMessage)) {
        	
        System.out.println("Successfully deleted products from the cart.");
        
        System.out.println("Test Completed.");
        
        }
        else {
        	System.out.println("Something went wrong when deleting products.");
        }
		
		
}


	
	
	
	
	
	

	
}