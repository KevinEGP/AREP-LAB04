package co.edu.eci.persistence;

import co.edu.eci.models.Log;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class MongoDB {

  public static MongoCollection<Log> logs;

  public static void createConnection() {
    ConnectionString connectionString = new ConnectionString("mongodb://172.17.0.1:27017");
    CodecRegistry pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),fromProviders(PojoCodecProvider.builder().automatic(true).build()));
    MongoClientSettings settings = MongoClientSettings.builder().applyConnectionString(connectionString).codecRegistry(pojoCodecRegistry).build();
    MongoClient client = MongoClients.create(settings);
    MongoDatabase database = client.getDatabase("mongodb");

    logs = database.getCollection("logs", Log.class);
  }
  
}
