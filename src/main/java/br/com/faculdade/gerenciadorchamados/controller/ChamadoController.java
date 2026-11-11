
package br.com.faculdade.gerenciadorchamados.controller;

import br.com.faculdade.gerenciadorchamados.model.Chamado;
import br.com.faculdade.gerenciadorchamados.model.Prioridade;
import br.com.faculdade.gerenciadorchamados.model.StatusChamado;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/chamados")
public class ChamadoController {

    private final List<Chamado> chamados = new ArrayList<>();
    private final AtomicLong proximoId = new AtomicLong(11);

    // Construtor com os 10 chamados iniciais
    public ChamadoController() {

        chamados.add(new Chamado(1L, "Computador não liga",
                "Ana", "Financeiro", Prioridade.ALTA,
                StatusChamado.ABERTO));

        chamados.add(new Chamado(2L, "Impressora sem tinta",
                "Carlos", "RH", Prioridade.BAIXA,
                StatusChamado.ABERTO));

        chamados.add(new Chamado(3L, "Falha na rede",
                "Mariana", "TI", Prioridade.ALTA,
                StatusChamado.EM_ANDAMENTO));

        chamados.add(new Chamado(4L, "Acesso bloqueado",
                "Pedro", "Financeiro", Prioridade.MEDIA,
                StatusChamado.RESOLVIDO));

        chamados.add(new Chamado(5L, "Monitor piscando",
                "Julia", "Comercial", Prioridade.MEDIA,
                StatusChamado.ABERTO));

        chamados.add(new Chamado(6L, "Erro no sistema",
                "Lucas", "TI", Prioridade.ALTA,
                StatusChamado.RESOLVIDO));

        chamados.add(new Chamado(7L, "Teclado com defeito",
                "Beatriz", "RH", Prioridade.BAIXA,
                StatusChamado.EM_ANDAMENTO));

        chamados.add(new Chamado(8L, "Internet lenta",
                "Rafael", "Comercial", Prioridade.ALTA,
                StatusChamado.ABERTO));

        chamados.add(new Chamado(9L, "Instalar aplicativo",
                "Fernanda", "Financeiro", Prioridade.BAIXA,
                StatusChamado.RESOLVIDO));

        chamados.add(new Chamado(10L, "Impressora desconectada",
                "Bruno", "TI", Prioridade.MEDIA,
                StatusChamado.EM_ANDAMENTO));
    }

    // 4.1 - Listar todos os chamados
    @GetMapping
    public List<Chamado> listarTodos() {
        return chamados;
    }

    // 4.2 - Buscar chamado por ID
    @GetMapping("/{id}")
    public ResponseEntity<Chamado> buscarPorId(
            @PathVariable Long id) {

        for (Chamado chamado : chamados) {
            if (chamado.getId().equals(id)) {
                return ResponseEntity.ok(chamado);
            }
        }

        return ResponseEntity.notFound().build();
    }

    // 4.3 - Filtrar chamados por setor
    @GetMapping("/setor")
    public List<Chamado> buscarPorSetor(
            @RequestParam String nome) {

        return chamados.stream()
                .filter(c -> c.getSetor()
                        .equalsIgnoreCase(nome))
                .collect(Collectors.toList());
    }

    // 4.4 - Filtrar chamados por prioridade
    @GetMapping("/prioridade")
    public List<Chamado> buscarPorPrioridade(
            @RequestParam String nivel) {

        return chamados.stream()
                .filter(c -> c.getPrioridade().name()
                        .equalsIgnoreCase(nivel))
                .collect(Collectors.toList());
    }

    // 4.5 - Buscar chamado pelo título
    @GetMapping("/busca")
    public List<Chamado> buscarPorTitulo(
            @RequestParam String texto) {

        return chamados.stream()
                .filter(c -> c.getTitulo()
                        .toLowerCase()
                        .contains(texto.toLowerCase()))
                .collect(Collectors.toList());
    }

    // 4.6 - Listar chamados críticos
    @GetMapping("/criticos")
    public List<Chamado> listarCriticos() {

        return chamados.stream()
                .filter(c -> c.getPrioridade() == Prioridade.ALTA)
                .filter(c -> c.getStatus() != StatusChamado.RESOLVIDO)
                .collect(Collectors.toList());
    }

    // 4.7 - Resumo dos chamados
    @GetMapping("/resumo")
    public Map<String, Long> resumo() {

        Map<String, Long> resultado = new LinkedHashMap<>();

        resultado.put("abertos", chamados.stream()
                .filter(c -> c.getStatus() == StatusChamado.ABERTO)
                .count());

        resultado.put("emAndamento", chamados.stream()
                .filter(c -> c.getStatus() == StatusChamado.EM_ANDAMENTO)
                .count());

        resultado.put("resolvidos", chamados.stream()
                .filter(c -> c.getStatus() == StatusChamado.RESOLVIDO)
                .count());

        resultado.put("total", (long) chamados.size());

        return resultado;
    }

    // 4.8 - Cadastrar novo chamado
    @PostMapping
    public ResponseEntity<Chamado> cadastrar(
            @RequestBody Chamado chamado) {

        chamado.setId(proximoId.getAndIncrement());
        chamados.add(chamado);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(chamado);
    }

}
