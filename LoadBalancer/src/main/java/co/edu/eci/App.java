package co.edu.eci;

import static spark.Spark.*;
import java.io.*;  
import java.net.*;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

public class App {
    
    private static int[] serversPorts = {35001, 35002, 35003};
    private static int currentServer = 0;
    private static String url = "http://172.17.0.1:";
    public static void main( String[] args ) {
        port(getPort());
        staticFiles.location("/public");
        get("/", "text/html", (req, res) -> {
            res.redirect("index.html");            
            return null;
        });
        
        get("/log", (req, res) -> {
            // isServerActive();
            try {
                HttpResponse<String> response = Unirest.get(url + serversPorts[currentServer] + "/log").header("accept", "application/json").asString();
                changeServer();
                return response.getBody();
            } catch (Exception e) {
                changeServer();
                e.printStackTrace();
            }
            return null;
        });

        post("/log", (req, res) -> {
            // isServerActive();
            try {
                HttpResponse<String> response = Unirest.post(url+ serversPorts[currentServer] + "/log").header("accept", "application/json").body(req.body()).asString();
                changeServer();
                return response.getBody();
            } catch (Exception e) {
                e.printStackTrace();
                changeServer();
            }
            return null;
        });
    }

    public static int getPort() {
        if (System.getenv("PORT") != null) {
        return Integer.parseInt(System.getenv("PORT"));
        }
        return 3500;
    }

    private static void changeServer() {
        currentServer = (currentServer + 1) % serversPorts.length;
    }

    private static boolean isServerActive() {
        boolean active = false;
        while (!active) {
            try (Socket socket = new Socket()) {
                socket.connect(new InetSocketAddress("0.0.0.0", serversPorts[currentServer]), 500);
                active = true;
            } catch (IOException e) {
                active = false;
                changeServer();
            }
        }
        return active;
    }
}