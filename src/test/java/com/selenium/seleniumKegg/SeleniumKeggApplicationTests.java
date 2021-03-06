package com.selenium.seleniumKegg;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SeleniumKeggApplicationTests {

	@Before

	public void setUp() throws Exception {

	}

	@Test

	public void testSearchInGooglePage() {

		System.setProperty("webdriver.chrome.driver", "/home/milene.guimaraes/dev/programas/chromedriver");

		WebDriver driver  = new ChromeDriver();

//           Digo qual url para acessar

		driver.get("https://www.genome.jp/kegg/tool/map_module1.html");

//           Agora vamos buscar o elemento na página

		WebElement inputTextGoogle = driver.findElement(By.name("unclassified"));

		inputTextGoogle.sendKeys("ko:K01803");

		/*           faz um submit na página

		 *           poderia buscar o botão search e fazer o submit tb.

		 */

		inputTextGoogle.submit();

		assertTrue(driver.getPageSource().contains(driver.findElement(By.id("gbqfq")).getText()));

	}

}
