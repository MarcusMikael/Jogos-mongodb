package dao;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import java.util.StringJoiner;
import model.Jogo;
import org.bson.Document;
import org.bson.types.ObjectId;

public class JogoDAO {
// Conexão com o banco

    private final MongoCollection<Document> collection;
    private final MongoDBConnection conn;

    public JogoDAO() {
        this.conn = new MongoDBConnection();
        this.collection = conn.getCollection();
    }

    // Inserir dados no banco
    public void inserir(Jogo jogo) {
        try {
            Document doc = new Document()
                    .append("titulo", jogo.getTitulo())
                    .append("genero", jogo.getGenero())
                    .append("anoLancamento", jogo.getAnoLancamento())
                    .append("preco", jogo.getPreco());

            collection.insertOne(doc);

            if (doc.containsKey("_id")) { // pega o ID gerado pelo Mongo e adiciona ao objeto Jogo.
                jogo.setId(doc.getObjectId("_id").toHexString());
            }

            System.out.println("Jogo cadastrado com sucesso: " + jogo);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao cadastrar jogo: " + e.getMessage(), e);
        }
    }

    public Jogo buscar(String id) {
        try {
            Document doc = collection.find(Filters.eq("_id", new ObjectId(id))).first(); // Procura o jogo pelo id, cuja o valor informado
            if (doc != null) {
                Jogo jogo = new Jogo();
                jogo.setId(doc.getObjectId("_id").toString());
                jogo.setTitulo(doc.getString("titulo"));
                jogo.setGenero(doc.getString("genero"));
                jogo.setAnoLancamento(doc.getInteger("anoLancamento"));
                jogo.setPreco(doc.getDouble("preco"));
                return jogo;
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar jogo: " + e.getMessage(), e);
        }
    }

    public void atualizar(Jogo jogo) {
        try {
            collection.updateOne(Filters.eq("_id", new ObjectId(jogo.getId())), // Localizar o jogo pelo id
                    Updates.combine(
                            Updates.set("titulo", jogo.getTitulo()),
                            Updates.set("genero", jogo.getGenero()),
                            Updates.set("anoLancamento", jogo.getAnoLancamento()),
                            Updates.set("preco", jogo.getPreco())));
            System.out.println("Jogo atualizado com sucesso: " + jogo);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar jogo: " + e.getMessage(), e);
        }
    }

    public void remover(String id) {
        try {
            collection.deleteOne(Filters.eq("_id", new ObjectId(id))); // Deleta o documento que tiver o id igual ao informado.
            System.out.println("Jogo excluído com sucesso (ID: " + id + ")");
        } catch (Exception e) {
            throw new RuntimeException("Erro ao excluir jogo: " + e.getMessage(), e);
        }
    }

    public String listarTodos() {
        StringJoiner joiner = new StringJoiner("\n");
        try (MongoCursor<Document> cursor = collection.find().iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                Jogo jogo = new Jogo();
                jogo.setId(doc.getObjectId("_id").toString());
                jogo.setTitulo(doc.getString("titulo"));
                jogo.setGenero(doc.getString("genero"));
                jogo.setAnoLancamento(doc.getInteger("anoLancamento"));
                jogo.setPreco(doc.getDouble("preco"));
                joiner.add(jogo.toString());
            }
            return joiner.toString();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao listar jogos: " + e.getMessage(), e);
        }
    }

    public void close() {
        if (conn != null) {
            conn.close();
        }
    }
}
