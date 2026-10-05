package com.tatf.adminCes.login.data;

import com.tatf.core.util.ConfigReader;

public class LoguinData {

    private static final ConfigReader config = new ConfigReader("config.properties");

    public static final String EMAIL = config.asString("adminces.usuarioAdmin");
    public static final String CONTRASENIA = config.asString("adminces.contrasenia");


    public static String MSJ_SESION_OK = "Sesión iniciada.";
    public static String MSJ_SESION_ERROR = "Perfil de usuario NO administrador.";

}
