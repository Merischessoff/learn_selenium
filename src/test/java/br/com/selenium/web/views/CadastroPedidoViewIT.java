package br.com.selenium.web.views;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CadastroPedidoViewIT {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();

        driver.get("http://localhost:9999/Login");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement checkInput = wait.until(ExpectedConditions.elementToBeClickable(By.id("novo")));
        checkInput.click();

        var textBox = driver.findElement(By.name("wtelefone"));
        textBox.sendKeys("51994318586");

        driver.quit();
    }
}
