package co.edu.eci.controllers;

import java.util.ArrayList;
import java.util.function.Consumer;

import com.mongodb.BasicDBObject;

import co.edu.eci.models.Log;
import co.edu.eci.persistence.MongoDB;

public class LogController {
    
    public static void save(Log log) {
        try {
            MongoDB.logs.insertOne(log);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static ArrayList<Log> getAll() {
        ArrayList<Log> list = new ArrayList<>();
        try {
            MongoDB.logs.find().sort(BasicDBObject.parse("{_id:-1}") ).limit(10).forEach((Consumer<Log>) d -> list.add(d));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
