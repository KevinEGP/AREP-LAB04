package co.edu.eci;

import static spark.Spark.*;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

public class App {
    
    private static String[] serversPorts = {"5678","5678","5678"};
    private static int currentServer = 0;
    private static String url = "http://localhost:";
    public static void main( String[] args ) {
        port(getPort());
        staticFiles.location("/public");
        get("/", "text/html", (req, res) -> {
            res.redirect("index.html");            
            return null;
        });
        
        get("/log", (req, res) -> {
            try {
                HttpResponse<String> response = Unirest.get(url + serversPorts[currentServer] + "/log").header("accept", "application/json").asString();
                changeServer();
                return response.getBody();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        });

        post("/log", (req, res) -> {
            try {
                HttpResponse<String> response = Unirest.post(url+ serversPorts[currentServer] + "/log").header("accept", "application/json").body(req.body()).asString();
                changeServer();
                return response.getBody();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        });
    }

    public static int getPort() {
        if (System.getenv("PORT") != null) {
        return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }

    private static void changeServer() {
        currentServer = (currentServer + 1) % 3;
    }
}