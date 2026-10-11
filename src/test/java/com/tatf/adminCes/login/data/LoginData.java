package com.tatf.adminCes.login.data;

import com.tatf.core.util.ConfigReader;

public class LoginData {

    private static ConfigReader config = new ConfigReader("config.properties");

    public static final String EMAIL = config.asString("adminces.usuarioAdmin");
    public static final String CONTRASENIA = config.asString("adminces.contrasenia");


    public static final String MSJ_SESION_OK = "Sesión iniciada.";
}
