package VerifySlider;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Slider 
{
	public static void main(String[] args) throws Exception 
	{
		WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();
    			
		try
		{
		driver.get("https://www.fitpeo.com/home");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement revenu = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Revenue Calculator")));
		Thread.sleep(2000);
		revenu.click();
		Thread.sleep(2000);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,350)", "");
		
//#1.Perfroming slider action
		WebElement slider = driver.findElement(By.cssSelector("body > div.MuiBox-root.css-3f59le > div.MuiBox-root.css-rfiegf > div.MuiGrid-root.MuiGrid-container.MuiGrid-spacing-xs-6.css-l0ykmo > div:nth-child(2) > div > div > span.MuiSlider-root.MuiSlider-colorPrimary.MuiSlider-sizeMedium.css-16i48op > span.MuiSlider-thumb.MuiSlider-thumbSizeMedium.MuiSlider-thumbColorPrimary.MuiSlider-thumb.MuiSlider-thumbSizeMedium.MuiSlider-thumbColorPrimary.css-1sfugkh"));
		
		Actions slideraction = new Actions(driver);
		Thread.sleep(2000);
		slideraction.clickAndHold(slider).perform();
		
        int moveByOffset = 94;

        // Moving the slider slightly
        slideraction.moveByOffset(moveByOffset, 0).perform();

        // Releasing the slider after the correct value is set
        slideraction.release().perform();
		Thread.sleep(1000);
		
		WebElement textField = driver.findElement(By.xpath("/html/body/div[2]/div[1]/div[1]/div[2]/div/div/div/div/input"));
		System.out.println("Updated Field Value"+": "+textField.getAttribute("value"));
		System.out.println("Slider Action Completed Successfully");

		
//#2. Update the Text Field:
				textField.click();
				//clear the previous value
				String currentValue = textField.getAttribute("value");
				for (int i = 0; i < currentValue.length(); i++) 
				{
				    textField.sendKeys(Keys.BACK_SPACE);
				}
				
				Thread.sleep(1000);
				textField.sendKeys("520");
				
				System.out.println("Updated Field Value After entered value manual"+": "+textField.getAttribute("value"));
				System.out.println("Update the Text Field Action Completed Successfully");

				
//#4. Select CPT Codes:
				Thread.sleep(2000);
				js.executeScript("window.scrollBy(0,450)", "");
				
				Thread.sleep(3000);
	            String[] cptCodes = {"CPT-99091","CPT-99453","CPT-99454","CPT-99474"};
	            
	            for (int i = 0; i < cptCodes.length; i++) 
	            {
	                String cptCode = cptCodes[i];
	                System.out.println("Processing CPT code: " + cptCode);

		            WebElement checkbox = driver.findElement(By.xpath("//p[text()='" + cptCode + "']/ancestor::div[contains(@class, 'css-1eynrej')]//input[@type='checkbox']"));
		            Thread.sleep(1000);
	            
		            // Clicking the checkbox
		            if (!checkbox.isSelected()) 
		            {
		            	Thread.sleep(1000);
		                checkbox.click();
	                    System.out.println("Checkbox for " + cptCode + " selected.");
	                    Thread.sleep(1000);
		            }
	            }
				System.out.println("Select CPT Codes Selection completed Successfully");
	            
	    } 
	
	catch (Exception e) 
	{
        System.out.println(e.getMessage());;
    } 
	
	finally
	{
		Thread.sleep(4000);
		driver.quit();
	}	
		
  }
  
}
