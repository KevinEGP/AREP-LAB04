package co.edu.eci;

import static spark.Spark.*;

import com.google.gson.Gson;

import co.edu.eci.controllers.LogController;
import co.edu.eci.models.Log;
import co.edu.eci.persistence.MongoDB;

public class App {
    public static void main( String[] args ) {
        Gson gson = new Gson();
        MongoDB.createConnection();

        port(getPort());

        get("/log", (req, res) -> {
            return gson.toJson(LogController.getAll());
        });

        post("/log", (req, res) -> {
            Log log = new Log(req.body());
            LogController.save(log);
            return gson.toJson("{success: true}");
        });
    }

    public static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 35000;
    }
}
