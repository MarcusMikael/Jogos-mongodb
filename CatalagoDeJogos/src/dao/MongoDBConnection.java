package dao;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MongoDBConnection {

    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> collection;

    public MongoDBConnection() {
        try {
            String uri = "mongodb+srv://MarcusM:M%40mikael22@marcusmikael.qg4acgv.mongodb.net/?retryWrites=true&w=majority&appName=MarcusMikael";
            String dbName = "Jogos";
            String collectionName = "jogo";

            // Cria a conexão
            mongoClient = MongoClients.create(uri);
            database = mongoClient.getDatabase(dbName);
            collection = database.getCollection(collectionName);

            // Testa a conexão com um comando ping
            mongoClient.getDatabase("admin").runCommand(new Document("ping", 1));
            System.out.println("Conexão estabelecida com sucesso!");
        } catch (Exception e) {
            throw new RuntimeException("Erro ao conectar ao MongoDB Atlas: " + e.getMessage(), e);
        }
    }

    public MongoCollection<Document> getCollection() {
        return collection;
    }

    public MongoDatabase getDatabase() {
        return database;
    }

    public void close() {
        if (mongoClient != null) {
            try {
                mongoClient.close();
                System.out.println("Conexão encerrada com sucesso.");
            } catch (Exception e) {
                throw new RuntimeException("Erro ao fechar conexão: " + e.getMessage(), e);
            }
        }
    }
}
