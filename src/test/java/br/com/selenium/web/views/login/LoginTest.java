package br.com.selenium.web.views.login;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LoginTest {
    private LoginPage paginaDeLogin;

    @BeforeEach
    public void beforeEach() {
        this.paginaDeLogin = new LoginPage();
    }

    @AfterEach
    public void afterEach() {
        this.paginaDeLogin.fechar();
    }

    @Test
    public void deveriaEfetuarLoginComDadosValidos() {
        paginaDeLogin.preencherFormularioDeLogin("teste", "teste", "teste");
        paginaDeLogin.efetuarLogin();

        String nomeUsuarioLogado = paginaDeLogin.getNomeUsuarioLogado();
        Assertions.assertEquals("teste", nomeUsuarioLogado);
        Assertions.assertFalse(paginaDeLogin.isPaginaAtual());
    }

    @Test
    public void naoDeveriaEfetuarLoginComDadosInvalidos() {
        paginaDeLogin.preencherFormularioDeLogin("fulano", "fulano", "teste");
        paginaDeLogin.efetuarLogin();

        Assertions.assertNull(paginaDeLogin.getNomeUsuarioLogado());
        Assertions.assertTrue(paginaDeLogin.isPaginaAtual());
        Assertions.assertTrue(paginaDeLogin.isMensagemDeLoginInvalidoVisivel());
    }
    
}
