package com.tatf.adminCes.base.test;
import com.tatf.adminCes.base.task.IngresoTask;
import com.tatf.core.browser.BrowserFactory;
import com.tatf.core.browser.IBrowser;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class BaseTest {
    protected static IBrowser browser;

    @BeforeAll
    static void beforeAll() {
        System.out.println("Inicio de la suite de tests");
    }

    @BeforeEach
    void configuration() {
        browser = BrowserFactory.getBrowser(true);
        IngresoTask ingresoTask = new IngresoTask(browser);
        String tituloObtenido = ingresoTask.enterToSystem();
        ingresoTask.verifyTitle(tituloObtenido);
    }

    @AfterEach
    void afterEach() {
        BrowserFactory.quitBrowser();
    }

    @AfterAll
    static void afterAll() {
        System.out.println("Fin de la suite de tests");
    }
}