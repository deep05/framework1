package SelenuimFramework1.framework1Design.tests;

import java.awt.Scrollbar;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.idealized.Javascript;
import org.openqa.selenium.devtools.v131.animation.model.ViewOrScrollTimeline;

public class InterviewIPL {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
		driver.get("https://www.iplt20.com/");
		driver.findElement(By.xpath("//nav/ul/li[5]/a")).click();
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("scroll(500,500)");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//ul/li[10]")).click();
		
		//driver.close();

	}

}
