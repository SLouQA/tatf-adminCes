                             package com.tatf.tests;

                             import com.tatf.core.browser.BrowserFactory;
                             import com.tatf.core.browser.IBrowser;
                             import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;

public class TestForm {

    private static IBrowser browser;

    @BeforeAll
    static void beforeAll() {
        browser = BrowserFactory.getBrowser(true);
        browser.interaction().f11();
    }

    @AfterAll
    static void afterAll() {
        BrowserFactory.quitBrowser();
    }

    @Test
    void completeForm() {
        browser.interaction().navigateTo("http://cestore.ces.com.uy/autotestlab/");
        browser.find().css("input[type='password']").write("3&44fcf@42e157ff0f)21f2#ecb12ad9");
        browser.find().css("button[type='submit']").click();
        browser.find().link("Formulario").click();
        browser.find().id("nombre").write("Juan Pérez");
        browser.find().id("comentarios").write("Comentario de prueba generado por automatización.");
        browser.find().id("aceptoTerminos").click();
        browser.find().id("femenino").click();
        browser.find().id("pais").selectValue("uruguay");
        browser.find().id("fecha").write("15/03/1995");
        browser.find().link("Enviar").click();


    }
}