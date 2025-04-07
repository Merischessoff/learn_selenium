package br.com.selenium.web.views.login;

import br.com.selenium.web.views.PageObject;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends PageObject {
    private static final String URL_LOGIN = "";

    public LoginPage() {
        super(null);
        this.browser.navigate().to(URL_LOGIN);
    }

    public void preencherFormularioDeLogin(String company, String username, String password) {
        SearchContext shadow = new WebDriverWait(browser, Duration.ofSeconds(5))
            .until(driver -> driver.findElement(By.cssSelector("login-form")).getShadowRoot());

        WebElement campoEmpresa = shadow.findElement(By.id("cpnyname"));
        WebElement campoUsuario = shadow.findElement(By.id("username"));
        WebElement campoSenha   = shadow.findElement(By.id("password"));

        JavascriptExecutor js = (JavascriptExecutor) browser;

        setInput(js, campoEmpresa, company);
        setInput(js, campoUsuario, username);
        setInput(js, campoSenha, password);
    }

    private void setInput(JavascriptExecutor js, WebElement input, String valor) {
        js.executeScript(
            "arguments[0].value = arguments[1];" +
                "arguments[0].dispatchEvent(new Event('input', { bubbles: true }));" +
                "arguments[0].dispatchEvent(new Event('change', { bubbles: true }));",
            input, valor
        );
    }


    private WebElement getShadowRoot(WebDriver driver, WebElement shadowHost) {
        return (WebElement) ((JavascriptExecutor) driver)
                .executeScript("return arguments[0].shadowRoot", shadowHost);
    }

    public void efetuarLogin() {
        SearchContext shadow = browser.findElement(By.cssSelector("login-form")).getShadowRoot();
        WebElement botaoLogin = shadow.findElement(By.id("btlogin"));
        botaoLogin.click();
    }

    public String getNomeUsuarioLogado() {
        try {
            WebElement el = new WebDriverWait(browser, Duration.ofSeconds(5))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("name-usu-logado")));

            String texto = el.getText();

            return texto;
        } catch (NoSuchElementException | TimeoutException e) {
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
