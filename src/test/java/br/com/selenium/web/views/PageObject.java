package br.com.selenium.web.views;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class PageObject {
	
	protected WebDriver browser;

	public PageObject(WebDriver browser) {
		if (browser != null) {
			this.browser = browser;
		} else {
			this.browser = new ChromeDriver();
		}

		this.browser.manage().timeouts()
			.implicitlyWait(5, TimeUnit.SECONDS)
			.pageLoadTimeout(10, TimeUnit.SECONDS);
	}

	public void fechar() {
		this.browser.quit();
	}

}
