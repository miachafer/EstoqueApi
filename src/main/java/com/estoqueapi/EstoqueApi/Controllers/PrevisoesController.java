package com.estoqueapi.EstoqueApi.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estoqueapi.EstoqueApi.Entidades.Previsoes;
import com.estoqueapi.EstoqueApi.Repositorios.PrevisoesRepository;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/previsoes")
public class PrevisoesController {

    @Autowired
    private PrevisoesRepository acao;

    // Cadastro de novas previsões
    @PostMapping("")
    public Previsoes cadastraPrevisoes(@RequestBody Previsoes obj) {
        return obj;
    }
    
    // Listar todas as previsões
    @GetMapping("")
    public Iterable<Previsoes> listaPrevisoes() {
        return acao.findAll();
    }

    // Alterar previsão
    @PutMapping("/alterar")
    public Previsoes alterarPrevisoes(@RequestBody Previsoes obj) {
        return acao.save(obj);
    }

    // Excluir previsão     VERIFICAR VALIDAÇÃO PARA EXCLUSÃO
    @DeleteMapping("/deletar/{id_previsao}")
    public void deletarPrevisoes(@PathVariable long id_previsao) {
        acao.deleteById(id_previsao);
    }

}
