package controller;

import dao.JogoDAO;
import model.Jogo;

public class JogoController {

    private final JogoDAO jogoDAO;

    public JogoController() {
        this.jogoDAO = new JogoDAO();
    }

    public String adicionarJogo(String titulo, String genero, int anoLancamento, double preco) {
        if (titulo == null || titulo.trim().isEmpty() || genero == null || genero.trim().isEmpty() || anoLancamento < 1900 || preco < 0) {
            return "Erro: Campos inválidos (título/gênero vazios, ano < 1900 ou preço negativo).";
        }
        try {
            Jogo jogo = new Jogo(null, titulo, genero, anoLancamento, preco);
            jogoDAO.inserir(jogo);
            return "Jogo adicionado com sucesso! ID: " + jogo.getId();
        } catch (Exception e) {
            return "Erro ao adicionar: " + e.getMessage();
        }
    }

    public String listarJogos() {
        try {
            String lista = jogoDAO.listarTodos();
            return lista.isEmpty() ? "Nenhum jogo encontrado." : lista;
        } catch (Exception e) {
            return "Erro ao listar: " + e.getMessage();
        }
    }

    public String atualizarJogo(String id, String titulo, String genero, int anoLancamento, double preco) {
        if (id == null || id.trim().isEmpty() || titulo == null || titulo.trim().isEmpty() || genero == null || genero.trim().isEmpty() || anoLancamento < 1900 || preco < 0) {
            return "Erro: Campos inválidos.";
        }
        try {
            Jogo jogo = new Jogo(id, titulo, genero, anoLancamento, preco);
            jogoDAO.atualizar(jogo);
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
    }
}
