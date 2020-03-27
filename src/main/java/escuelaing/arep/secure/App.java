package escuelaing.arep.secure;

import static spark.Spark.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        port(getPort());
        // staticFiles.location("/public");
        secure("src/main/resources/keystores/keystore.p12", "arep123", 
                "src/main/resources/keystores/cacerts.p12", "arep123");
        get("/", (req, res) -> "Hello World");
    }

    /**
     * @return retorna un puerto disponible para correr la aplicacion
     */
    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567; //returns default port if heroku-port isn't set (i.e. on localhost)
    }
}
