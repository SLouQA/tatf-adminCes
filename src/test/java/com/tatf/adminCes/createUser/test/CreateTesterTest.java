package com.tatf.adminCes.createUser.test;

import com.tatf.adminCes.base.test.BaseTest;
import com.tatf.adminCes.base.pom.GeneralPO;
import com.tatf.adminCes.createUser.pom.CreateUserPO;
import com.tatf.adminCes.createUser.task.CreateUserTask;
import com.tatf.adminCes.login.pom.LoguinPO;
import com.tatf.adminCes.login.task.LoguinTask;
import com.tatf.adminCes.viewUs.pom.ViewUsPO;
import com.tatf.adminCes.viewUs.task.ViewUsTask;
import com.tatf.core.element.Element;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static com.tatf.adminCes.login.data.LoguinData.*;

public class CreateTesterTest extends BaseTest {
    @ParameterizedTest(name = "{arguments}")
    @CsvFileSource(
            resources = "/crearTester.csv",
            useHeadersInDisplayName = true,
            delimiter = ';'
    )
    void crearCuentaTester(String nombre, String apellido, String email, String contrasenia, String pais, String idRol, String textoRol) {

        LoguinPO loguinPO = new LoguinPO(browser);
        LoguinTask loguinTask = new LoguinTask(browser);

        GeneralPO generalPO = new GeneralPO(browser);
        CreateUserPO createUserPO = new CreateUserPO(browser);
        CreateUserTask createUserTask = new CreateUserTask(browser);
        ViewUsPO viewUsPO = new ViewUsPO(browser);
        ViewUsTask viewUsTask = new ViewUsTask(browser);

        loguinPO.ingresarInSes();
        loguinTask.loguinAdmin(EMAIL, CONTRASENIA);

        String textoObtenidoLogin = generalPO.getMsjModal();
        loguinTask.verifyLoguinAdminOk(textoObtenidoLogin);
        generalPO.okClick();

        createUserPO.ingresarRegTester();
        createUserTask.regTester(nombre, apellido, email, contrasenia, pais, idRol);

        String textoObtenidoModal = generalPO.getMsjModal();
        createUserTask.verifyCreateOk(textoObtenidoModal);
        generalPO.okClick();

        viewUsPO.ingresarViewUs();
        Element fila = viewUsTask.buscarFila(nombre, apellido, email, textoRol);
        viewUsTask.verifyUsExist(fila);

    }
    @ParameterizedTest(name = "{arguments}")
    @CsvFileSource(
            resources = "/crearTesterEmailR.csv",
            useHeadersInDisplayName = true,
            delimiter = ';'
    )
    void crearCuentaTestereEmailRep(String nombre, String apellido, String email, String contrasenia, String pais, String idRol,
                                    String nombreD, String apellidoD, String emailR, String contraseniaD, String paisR,String idRol2) {

        LoguinPO loguinPO = new LoguinPO(browser);
        LoguinTask loguinTask = new LoguinTask(browser);

        GeneralPO generalPO = new GeneralPO(browser);
        CreateUserPO createUserPO = new CreateUserPO(browser);
        CreateUserTask createUserTask = new CreateUserTask(browser);

        loguinPO.ingresarInSes();
        loguinTask.loguinAdmin(EMAIL, CONTRASENIA);

        String textoObtenidoLogin = generalPO.getMsjModal();
        loguinTask.verifyLoguinAdminOk(textoObtenidoLogin);
        generalPO.okClick();

        createUserPO.ingresarRegTester();
        createUserTask.regTester(nombre, apellido, email, contrasenia, pais, idRol);

        String textoObtenidoModal = generalPO.getMsjModal();
        createUserTask.verifyCreateOk(textoObtenidoModal);
        generalPO.okClick();

        createUserPO.ingresarRegTester();
        createUserTask.regTester(nombreD, apellidoD, emailR, contraseniaD, paisR, idRol2);

        String textoObtenidoModalError = generalPO.getMsjModal();
        createUserTask.verifyCreateExiste(textoObtenidoModalError);

        generalPO.okClick();

    }
}
