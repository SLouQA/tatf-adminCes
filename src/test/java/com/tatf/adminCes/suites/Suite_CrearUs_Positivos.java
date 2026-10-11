package com.tatf.adminCes.suites;


import com.tatf.adminCes.createUser.test.CreateAdminTest;
import com.tatf.adminCes.createUser.test.CreateTesterTest;
import org.junit.platform.suite.api.*;

@Suite
@SuiteDisplayName("Pruebas de creación de usuario Positivo")
@SelectClasses({CreateTesterTest.class, CreateAdminTest.class})
@ExcludeTags("negativo")
public class Suite_CrearUs_Positivos {

}
