package br.com.selenium.web.views.login;

import br.com.selenium.web.views.PageObject;
import org.openqa.selenium.*;

public class LoginPage extends PageObject {
    private static final String URL_LOGIN = "";

    public LoginPage() {
        super(null);
        this.browser.navigate().to(URL_LOGIN);
    }

    public void preencherFormularioDeLogin(String company, String username, String password) {
        try {
            Thread.sleep(1000);
            SearchContext shadow = browser.findElement(By.cssSelector("login-form")).getShadowRoot();
            Thread.sleep(1000);
            shadow.findElement(By.name("cpnyname")).sendKeys(company);
            shadow.findElement(By.name("username")).sendKeys(username);
            shadow.findElement(By.name("password")).sendKeys(password);

        } catch (Exception e) {

        }
    }

    private WebElement getShadowRoot(WebDriver driver, WebElement shadowHost) {
        return (WebElement) ((JavascriptExecutor) driver)
                .executeScript("return arguments[0].shadowRoot", shadowHost);
    }

    public void efetuarLogin() {
        SearchContext shadow = browser.findElement(By.cssSelector("login-form")).getShadowRoot();
        shadow.findElement(By.id("btlogin")).click();
    }

    public String getNomeUsuarioLogado() {
        try {
            return browser.findElement(By.cssSelector("#avatar-usuario > img")).getAttribute("title");
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public boolean isPaginaAtual() {
        return browser.getCurrentUrl().contains(URL_LOGIN);
    }

    public boolean isMensagemDeLoginInvalidoVisivel() {
        return browser.getPageSource().contains("Usuário e senha inválidos");
    }

}
