package com.tatf.core.browser;

import com.tatf.core.driver.factory.DriverType;
import com.tatf.core.driver.instance.DriverManagerSingleton;
import com.tatf.core.util.ConfigReader;

public class BrowserFactory {
    private static final int EXPLICIT_WAIT_DEFAULT_SECONDS = 10;
    private static final ConfigReader config = new ConfigReader("config.properties");
    private BrowserFactory() {
    }

    /**
     * Arma un IBrowser listo para usar, con el driver correspondiente.
     *
     * @param debugging Si es true, resalta los elementos al interactuar con ellos.
     */
    public static IBrowser getBrowser(boolean debugging) {
        DriverType type = resolveDriverType();
        DriverManagerSingleton instance = DriverManagerSingleton.getInstance(type);
        return new BrowserImpl(instance, EXPLICIT_WAIT_DEFAULT_SECONDS, debugging);
    }

    /**
     * Lee el browser a usar desde la propiedad del sistema "browser" (CHROME por defecto).
     */
    private static DriverType resolveDriverType() {
        return DriverType.valueOf(config.asString("browser").toUpperCase());
    }

    /**
     * Cierra el browser actual y libera la instancia del Singleton.
     */
    public static void quitBrowser() {
        DriverType type = resolveDriverType();
        DriverManagerSingleton.getInstance(type).quit();
    }
}
