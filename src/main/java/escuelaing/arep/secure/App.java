package escuelaing.arep.secure;

import static spark.Spark.*;

import escuelaing.arep.secure.math.impl.MathOperations;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        port(getPort());

        int maxThreads = 8;
        threadPool(maxThreads);
        
        secure("src/main/resources/keystores/keystorelocal.p12", "arep123", "src/main/resources/keystores/cacerts.p12", "arep123");
        
        staticFiles.location("/public");

        path("/api/v1/math", () -> {
            MathOperations op = new MathOperations();
            before("/*", (q, a) -> System.out.println("Received api call"));
            path("/sum", () -> {
                get("/:values", (req, res) -> {
                    String[] values = req.params(":values").split("&");
                    Double sum = Double.NaN;
                    if (values.length > 1) {
                        Double[] doubleValues = parseToDoubleList(values);
                        sum = op.sum(doubleValues[0], doubleValues[1]);
                    }
                    return sum;
                });
            });
            path("/substrac", () -> {
                get("/:values", (req, res) -> {
                    String[] values = req.params(":values").split("&");
                    Double substrac = Double.NaN;
                    if (values.length > 1) {
                        Double[] doubleValues = parseToDoubleList(values);
                        substrac = op.substrac(doubleValues[0], doubleValues[1]);
                    }
                    return substrac;
                });
            });
            path("/multiply", () -> {
                get("/:values", (req, res) -> {
                    String[] values = req.params(":values").split("&");
                    Double multiply = Double.NaN;
                    if (values.length > 1) {
                        Double[] doubleValues = parseToDoubleList(values);
                        multiply = op.multiply(doubleValues[0], doubleValues[1]);
                    }
                    return multiply;
                });
            });
            path("/divide", () -> {
                get("/:values", (req, res) -> {
                    String[] values = req.params(":values").split("&");
                    Double divide = Double.NaN;
                    if (values.length > 1) {
                        Double[] doubleValues = parseToDoubleList(values);
                        divide = op.divide(doubleValues[0], doubleValues[1]);
                    }
                    return divide;
                });
            });
            path("/exponent", () -> {
                get("/:values", (req, res) -> {
                    String[] values = req.params(":values").split("&");
                    Double exponent = Double.NaN;
                    if (values.length > 1) {
                        Double[] doubleValues = parseToDoubleList(values);
                        exponent = op.exponent(doubleValues[0], doubleValues[1]);
                    }
                    return exponent;
                });
            });
            path("/square", () -> {
                get("/:values", (req, res) -> {
                    String values = req.params(":values");
                    Double square = Double.NaN;
                    square = op.square(Double.parseDouble(values));
                    return square;
                });
            });
        });
        // CORS
        options("/*", (request, response) -> {

            String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
            if (accessControlRequestHeaders != null) {
                response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
            }

            String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
            if (accessControlRequestMethod != null) {
                response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
            }

            return "OK";
        });
        // Using Route
        notFound((req, res) -> {
            res.type("application/json");
            return "{\"message\":\"path not found 404\"}";
        });
    }

    static Double[] parseToDoubleList(String[] values) {
        Double[] res = new Double[values.length];
        for (int i = 0; i < res.length; i++)
            res[i] = Double.parseDouble(values[i]);
        return res;
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
