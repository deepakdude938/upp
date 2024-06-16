package ignore;

import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.*;

public class Demo {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver= new ChromeDriver();
		
		driver.get("https://www.google.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		WebElement a = driver.findElement(By.xpath("//textarea[@name='q']"));
		a.sendKeys("Selenium");
		Thread.sleep(3000);
		List<WebElement> l = driver.findElements(By.xpath("//div[@class='pcTkSc']"));
		System.out.println(l.size());
		for(WebElement aq : l) {
			String u = aq.getText();
			System.out.println(u);
		}
		

	}

}
