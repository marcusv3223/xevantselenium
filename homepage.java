package seleniumtest_marcus_verhage;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class homepage {
	
	
	public static WebDriver driver;
	 
	
	public static void main(String[] args) throws InterruptedException  {
		
		//points to the chrome driver executable
		System.setProperty("webdriver.chrome.driver","/Users/marcusverhage/eclipse-workspace/chromedriver"); 
	    driver = new ChromeDriver();
	    
	    
		//navigate to homepage
		driver.get("https://www.xevant.com/");
		
		
		//Popup appears very slowly (10+ seconds and maybe first requires scrolling), so I'm not adding a check here to close. 
	

		//First hover over the Company menu
		//hover is showing very quickly. Likely due to this issue in Chrome - https://bugs.chromium.org/p/chromium/issues/detail?id=1221387
		Actions hover = new Actions(driver);
		WebElement companyMenu = driver.findElement(By.xpath("//*[@id=\"menu-item-1442\"]/a"));
		hover.moveToElement(companyMenu).build().perform();
		
		
		//Then find the hidden submenu for leadership and click it
		WebElement leadershipMenu = driver.findElement(By.xpath("/html/body/div[1]/section[1]/div/div[2]/div/div/div/nav/ul/li[4]/ul/li[1]/ul/li[2]"));
		leadershipMenu.click();

		Thread.sleep(2000); //wait for page to load
		
		//Chad test case
		System.out.println("Chad Davis test case: ");
		if(getEmployeeTitle("Chad Davis").equals("Director, Compliance")) {
			System.out.print("Pass");
		  }
		else {
			System.out.print("Fail");
		}
		    

	 driver.quit();
	

	}
	
	
	//returns an employee title given employee name from the leadership page
	private static String getEmployeeTitle(String employeeName) {
		
		List<WebElement> employeeCards = driver.findElements(By.className("ha-card-body"));
		
		
		for (WebElement employee : employeeCards) {
		    if(employee.getText().contains(employeeName)) {
		    	return employee.findElement(By.tagName("p")).getText();
		 
		    }
		}
		
		return "Employee not found";
	}
}
