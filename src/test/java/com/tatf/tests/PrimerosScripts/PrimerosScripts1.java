package com.tatf.tests.PrimerosScripts;

import com.tatf.core.browser.BrowserFactory;
import com.tatf.core.browser.IBrowser;
import com.tatf.core.element.Element;
import com.tatf.core.verification.IVerify;
import org.junit.jupiter.api.*;

public class PrimerosScripts1 {

    static IBrowser browser;
    private static IVerify verify;
    private static String URL = "http://cestore.ces.com.uy/adminces/";
    private static String URL_CONTRASENIA = "3)ea60e0be3ba12c6ecd%7297868%5c4";
    private static String TITULO = "Taller de Automatización del Testing Funcional";
    private static String USUARIO = "yaniscorrea@gmail.com";
    private static String CONTRASENIA = "12345";

    private static String EXIST_NOMBRE = "Mariana";
    private static String EXIST_APELLIDO = "Travieso";
    private static String EXIST_EMAIL = "mariana@gmail.com";
    private static String EXIST_ROL = "Tester Senior";


    private static String PRUEBA_NOMBRE = "Nombre Prueba";
    private static String PRUEBA_APELLIDO = "Apellido Prueba";
    private static String PRUEBA_EMAIL = "cuenta.admin@yopmail.com";
    private static String PRUEBA_CONTRASENIA = "as123";
    private static String NUEVA_CONTRASENIA = "1234567";
    private static String PRUEBA_PAIS = "Uruguay";

    // ROLES "testerJunior", "testerSenior", "testerLead"
    private static String ID_ROL = "testerSenior";
    private static String VALIDACION_ROL = "Tester Senior";

    private static String MSJ_USUARIO_OK = "Usuario creado.";
    private static String MSJ_SESION_OK = "Sesión iniciada.";
    private static String MSJ_RESET_CONTRASENIA = "Contraseña reiniciada.";
    private static String MSJ_CONF_ELIM_USUARIO = "¿Eliminar usuario: "+ EXIST_EMAIL + "?";
    private static String MSJ_ELIM_USUARIO = "Usuario eliminado.";

    @BeforeAll
    static void beforeAll() {
        System.out.println("Inicio de la suite de tests");
    }

    @BeforeEach
    void ingresarAlSitio() {
        browser = BrowserFactory.getBrowser(true);
        browser.interaction().navigateTo(URL);
        browser.find().id("pass").write(URL_CONTRASENIA);
        browser.find().className("btn-primary").click();

        Element titulo = browser.find().xpath("//p[text()='Taller de Automatización del Testing Funcional']");
        String tituloObtenido = titulo.getText();

        verify = IVerify.create();
        verify.verify(tituloObtenido, TITULO, "Se puedo acceder correctamente.");
    }

    @AfterEach
    void afterEach() {
        BrowserFactory.quitBrowser();
    }

    @AfterAll
    static void afterAll() {
        System.out.println("Fin de la suite de tests");
    }

    @Test
    void crearCuentaAdmin() {

        browser.find().link("Registrarse").click();

        AuxFun.registrarAdmin(PRUEBA_NOMBRE,PRUEBA_APELLIDO, PRUEBA_EMAIL,PRUEBA_CONTRASENIA,PRUEBA_PAIS);

        Element mensajeU = browser.find().id("swal2-html-container");
        String textoObtenidoU = mensajeU.getText();
        verify.verify(textoObtenidoU, MSJ_USUARIO_OK,"Usuario creado OK");

        browser.find().className("swal2-confirm").click();

        AuxFun.LoginAdmin(PRUEBA_EMAIL, PRUEBA_CONTRASENIA);

        Element mensajeS = browser.find().id("swal2-html-container");
        String textoObtenidoS = mensajeS.getText();
        verify.verify(textoObtenidoS, MSJ_SESION_OK,"Sesión iniciada OK");

        browser.find().className("swal2-confirm").click();
    }

    @Test
    void reiniciarContraseniaAdmin() {

        browser.find().link("Reiniciar contraseña").click();

        AuxFun.resetContraseniaAdmin(USUARIO,NUEVA_CONTRASENIA);

        Element mensajeReset = browser.find().id("swal2-html-container");
        String textoObtenidoReset = mensajeReset.getText();
        verify.verify(textoObtenidoReset, MSJ_RESET_CONTRASENIA,"Contraseña reiniciada.");

        browser.find().className("swal2-confirm").click();

        AuxFun.LoginAdmin(USUARIO, NUEVA_CONTRASENIA);

        Element mensajeS = browser.find().id("swal2-html-container");
        String textoObtenidoS = mensajeS.getText();
        verify.verify(textoObtenidoS, MSJ_SESION_OK,"Sesión iniciada OK");

        browser.find().className("swal2-confirm").click();
    }

    @Test
    void crearCuentaTest() {
        AuxFun.LoginAdmin(USUARIO, CONTRASENIA);

        Element mensajeS = browser.find().id("swal2-html-container");
        String textoObtenidoS = mensajeS.getText();
        verify.verify(textoObtenidoS, MSJ_SESION_OK,"Sesión iniciada OK");

        browser.find().className("swal2-confirm").click();

        browser.find().link("Crear usuario").click();

        AuxFun.registrarTest(PRUEBA_NOMBRE,PRUEBA_APELLIDO, PRUEBA_EMAIL,PRUEBA_CONTRASENIA,PRUEBA_PAIS,ID_ROL);

        Element mensajeU = browser.find().id("swal2-html-container");
        String textoObtenidoU = mensajeU.getText();
        verify.verify(textoObtenidoU, MSJ_USUARIO_OK,"Usuario creado OK");

        browser.find().className("swal2-confirm").click();

        browser.find().link("Ver usuarios").click();

        Element fila = AuxFun.buscarFila(PRUEBA_NOMBRE, PRUEBA_APELLIDO, PRUEBA_EMAIL, VALIDACION_ROL);

        verify.verifyNotNull(fila, "El usuario creado aparece en la tabla con nombre, apellido, email y rol correctos");
    }

    @Test
    void eliminarCuentaTest() {

        AuxFun.LoginAdmin(USUARIO, CONTRASENIA);

        Element mensajeS = browser.find().id("swal2-html-container");
        verify.verify(mensajeS.getText(), MSJ_SESION_OK, "Sesión iniciada OK");
        browser.find().className("swal2-confirm").click();

        browser.find().link("Ver usuarios").click();

        Element fila = AuxFun.buscarFila(EXIST_NOMBRE, EXIST_APELLIDO, EXIST_EMAIL, EXIST_ROL);
        verify.verifyNotNull(fila, "La fila del usuario existe antes de eliminar");

        assert fila != null;
        fila.xpath(".//button").click();

        Element mensajeConfElim = browser.find().id("swal2-html-container");
        String textoObtenidoConfElim = mensajeConfElim.getText();
        verify.verify(textoObtenidoConfElim, MSJ_CONF_ELIM_USUARIO,"Conf al eliminar");

        browser.find().className("swal2-confirm").click();

        Element mensajeElim = browser.find().id("swal2-html-container");
        String textoObtenidoElim = mensajeElim.getText();
        verify.verify(textoObtenidoElim, MSJ_ELIM_USUARIO,"Usuario eliminado");

        browser.find().className("swal2-confirm").click();

        Element filaEliminada = AuxFun.buscarFila(EXIST_NOMBRE, EXIST_APELLIDO, EXIST_EMAIL, EXIST_ROL);
        verify.verifyNull(filaEliminada, "La fila del usuario fue eliminada");

    }


}