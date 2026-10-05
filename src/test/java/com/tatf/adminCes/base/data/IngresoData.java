package com.tatf.adminCes.base.data;

import com.tatf.core.util.ConfigReader;

public class IngresoData {
    private static final ConfigReader config = new ConfigReader("config.properties");

    public static final String URL = config.asString("adminces.url");
    public static final String PASS_URL = config.asString("adminces.passUrl");

    public static String TITULO = "Taller de Automatización del Testing Funcional";

}
