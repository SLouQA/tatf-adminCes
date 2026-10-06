package com.tatf.adminCes.createUser.test;

import com.tatf.adminCes.base.task.GeneralTask;
import com.tatf.adminCes.base.test.BaseTest;
import com.tatf.adminCes.base.pom.GeneralPO;
import com.tatf.adminCes.createUser.pom.CreateUserPO;
import com.tatf.adminCes.createUser.task.CreateUserTask;
import com.tatf.adminCes.login.pom.LoguinPO;
import com.tatf.adminCes.login.task.LoguinTask;
import com.tatf.adminCes.viewUs.task.ViewUsTask;
import com.tatf.core.element.Element;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static com.tatf.adminCes.createUser.data.CreateUserData.MSJ_USUARIO_OK;
import static com.tatf.adminCes.createUser.data.CreateUserData.MSJ_US_EXISTE;
import static com.tatf.adminCes.login.data.LoguinData.*;

public class CreateTesterTest extends BaseTest {
    @ParameterizedTest(name = "{arguments}")
    @CsvFileSource(
            resources = "/crearTester.csv",
            useHeadersInDisplayName = true,
            delimiter = ';'
    )
    void crearCuentaTester(String nombre, String apellido, String email, String contrasenia, String pais, String idRol, String textoRol) {

        LoguinTask loguinTask = new LoguinTask(browser);
        CreateUserTask createUserTask = new CreateUserTask(browser);
        ViewUsTask viewUsTask = new ViewUsTask(browser);
        GeneralTask generalTask = new GeneralTask(browser);


        loguinTask.loguinAdmin(EMAIL, CONTRASENIA);
        verify.verify(MSJ_SESION_OK, generalTask.obtenerMsjModal(), "Sesión de admin iniciada");
        generalTask.confirmarModal();


        createUserTask.regTester(nombre, apellido, email, contrasenia, pais, idRol);
        verify.verify(MSJ_USUARIO_OK, generalTask.obtenerMsjModal(), "Usuario creado OK");
        generalTask.confirmarModal();

        viewUsTask.ingresarViewUs();
        Element fila = viewUsTask.buscarFila(nombre, apellido, email, textoRol);
        verify.verifyNotNull(fila, "El usuario creado aparece en la tabla con nombre, apellido, email y rol correctos");
    }
    @ParameterizedTest(name = "{arguments}")
    @CsvFileSource(
            resources = "/crearTesterEmailR.csv",
            useHeadersInDisplayName = true,
            delimiter = ';'
    )
    void crearCuentaTestereEmailRep(String nombre, String apellido, String email, String contrasenia, String pais, String idRol,
                                    String nombreD, String apellidoD, String emailR, String contraseniaD, String paisR,String idRol2) {

        LoguinTask loguinTask = new LoguinTask(browser);
        CreateUserTask createUserTask = new CreateUserTask(browser);
        GeneralTask generalTask = new GeneralTask(browser);


        loguinTask.loguinAdmin(EMAIL, CONTRASENIA);
        verify.verify(MSJ_SESION_OK, generalTask.obtenerMsjModal(), "Sesión de admin iniciada");
        generalTask.confirmarModal();

        createUserTask.regTester(nombre, apellido, email, contrasenia, pais, idRol);
        verify.verify(MSJ_USUARIO_OK, generalTask.obtenerMsjModal(), "Usuario creado OK");
        generalTask.confirmarModal();

        createUserTask.regTester(nombreD, apellidoD, emailR, contraseniaD, paisR, idRol2);
        verify.verify(MSJ_US_EXISTE, generalTask.obtenerMsjModal(), "Usuario ya existente");
        generalTask.confirmarModal();
    }
}
