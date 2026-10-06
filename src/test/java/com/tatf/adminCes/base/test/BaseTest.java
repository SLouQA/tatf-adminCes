package com.tatf.adminCes.base.test;
import com.tatf.adminCes.base.task.IngresoTask;
import com.tatf.core.browser.BrowserFactory;
import com.tatf.core.browser.IBrowser;
import com.tatf.core.verification.IVerify;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.tatf.adminCes.base.data.IngresoData.*;


public class BaseTest {
    protected static IBrowser browser;
    protected final IVerify verify = IVerify.create();

    @BeforeAll
    static void beforeAll() {
        System.out.println("Inicio de la suite de tests");
    }

    @BeforeEach
    void configuration() {
        browser = BrowserFactory.getBrowser(true);
        IngresoTask ingresoTask = new IngresoTask(browser);
        String tituloObtenido = ingresoTask.enterToSystem(URL, PASS_URL, TITULO);
        verify.verify(TITULO, tituloObtenido, "Se pudo acceder correctamente.");
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