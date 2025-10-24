package controller;

import com.google.gson.Gson;
import dao.JogoDAO;
import dao.RedisConnection;
import model.Jogo;
import redis.clients.jedis.UnifiedJedis;

public class JogoController {

    private final JogoDAO jogoDAO;
    private final UnifiedJedis jedis;
    private final Gson gson;

    public JogoController() {
        this.jogoDAO = new JogoDAO();
        this.jedis = RedisConnection.getConnection(); // conexão Redis Cloud
        this.gson = new Gson();
    }

    public String adicionarJogo(String titulo, String genero, int anoLancamento, double preco) {
        if (titulo == null || titulo.trim().isEmpty()
                || genero == null || genero.trim().isEmpty()
                || anoLancamento < 1900 || preco < 0) {
            return "Erro: Campos inválidos (título/gênero vazios, ano < 1900 ou preço negativo).";
        }
        try {
            Jogo jogo = new Jogo(null, titulo, genero, anoLancamento, preco);
            jogoDAO.inserir(jogo);

            // 🔄 Limpa o cache da listagem após inserir
            jedis.del("lista_jogos");

            return "Jogo adicionado com sucesso! ID: " + jogo.getId();
        } catch (Exception e) {
            return "Erro ao adicionar: " + e.getMessage();
        }
    }

    public String listarJogos() {
        try {
            String cacheKey = "lista_jogos";
            String listaCache = jedis.get(cacheKey);

            if (listaCache != null) {
                System.out.println("🔁 Dados vindos do Redis (cache)");
                return listaCache;
            }

            System.out.println("📦 Dados vindos do MongoDB");
            String lista = jogoDAO.listarTodos();

            if (!lista.isEmpty()) {
                jedis.set(cacheKey, lista);
                jedis.expire(cacheKey, 60); // cache por 60 segundos
            }

            return lista.isEmpty() ? "Nenhum jogo encontrado." : lista;
        } catch (Exception e) {
            return "Erro ao listar: " + e.getMessage();
        }
    }

    public String atualizarJogo(String id, String titulo, String genero, int anoLancamento, double preco) {
        if (id == null || id.trim().isEmpty()
                || titulo == null || titulo.trim().isEmpty()
                || genero == null || genero.trim().isEmpty()
                || anoLancamento < 1900 || preco < 0) {
            return "Erro: Campos inválidos.";
        }
        try {
            Jogo jogo = new Jogo(id, titulo, genero, anoLancamento, preco);
            jogoDAO.atualizar(jogo);

            // 🔄 Limpa o cache após atualização
            jedis.del("lista_jogos");

            return "Jogo atualizado com sucesso!";
        } catch (Exception e) {
            return "Erro ao atualizar: " + e.getMessage();
        }
    }

    public String excluirJogo(String id) {
        if (id == null || id.trim().isEmpty()) {
            return "Erro: ID inválido.";
        }
        try {
            jogoDAO.remover(id);

            // 🔄 Limpa o cache após exclusão
            jedis.del("lista_jogos");

            return "Jogo excluído com sucesso!";
        } catch (Exception e) {
            return "Erro ao excluir: " + e.getMessage();
        }
    }

    public Jogo buscarJogoPorId(String id) {
        try {
            return jogoDAO.buscar(id);
        } catch (Exception e) {
            System.out.println("Erro ao buscar: " + e.getMessage());
            return null;
        }
    }

    public void close() {
        jogoDAO.close();
        jedis.close();
    }
}
