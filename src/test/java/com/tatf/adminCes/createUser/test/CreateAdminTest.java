package com.tatf.adminCes.createUser.test;

import com.tatf.adminCes.base.test.BaseTest;
import com.tatf.adminCes.base.pom.GeneralPO;
import com.tatf.adminCes.createUser.pom.CreateUserPO;
import com.tatf.adminCes.createUser.task.CreateUserTask;
import com.tatf.adminCes.login.task.LoguinTask;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;


public class CreateAdminTest extends BaseTest {

    @ParameterizedTest(name = "{arguments}")
    @CsvFileSource(
            resources = "/crearAdmin.csv",
            useHeadersInDisplayName = true,
            delimiter = ';'
    )
    void crearCuentaAdmin(String nombre, String apellido, String email, String contrasenia, String pais) {
        CreateUserPO createUserPO = new CreateUserPO(browser);
        CreateUserTask createUserTask = new CreateUserTask(browser);
        GeneralPO generalPO = new GeneralPO(browser);
        LoguinTask loguinTask = new LoguinTask(browser);

        createUserPO.ingresarRegistrarse();
        createUserTask.regAdmin(nombre, apellido, email, contrasenia, pais);

        String textoObtenidoModal = generalPO.getMsjModal();
        createUserTask.verifyCreateOk(textoObtenidoModal);

        generalPO.okClick();

        loguinTask.loguinAdmin(email, contrasenia);

        String textoObtenidoVerify = generalPO.getMsjModal();
        loguinTask.verifyLoguinAdminOk(textoObtenidoVerify);
    }
    @ParameterizedTest(name = "{arguments}")
    @CsvFileSource(
            resources = "/crearAdminEmailR.csv",
            useHeadersInDisplayName = true,
            delimiter = ';'
    )
    void crearCuentaTestereEmailRep(String nombre, String apellido, String email, String contrasenia, String pais,
                                    String nombreD, String apellidoD, String emailR, String contraseniaD, String paisR) {

        CreateUserPO createUserPO = new CreateUserPO(browser);
        CreateUserTask createUserTask = new CreateUserTask(browser);
        GeneralPO generalPO = new GeneralPO(browser);

        createUserPO.ingresarRegistrarse();
        createUserTask.regAdmin(nombre, apellido, email, contrasenia, pais);

        String textoObtenidoModal = generalPO.getMsjModal();
        createUserTask.verifyCreateOk(textoObtenidoModal);
        generalPO.okClick();

        createUserPO.ingresarRegistrarse();
        createUserTask.regAdmin(nombreD, apellidoD, emailR, contraseniaD, paisR);

        String textoObtenidoModalError = generalPO.getMsjModal();
        createUserTask.verifyCreateExiste(textoObtenidoModalError);

        generalPO.okClick();

// Agregar el resto de las validaciónes

    }
}
