package com.opensanca.trilharest.filmes.filmes;

import java.time.LocalDate;
import java.util.UUID;

import com.opensanca.trilharest.filmes.comum.EntitdadeNaoEncontradaException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/filmes")
@Api("API para acesso e manipulacao de filmes em cartaz")
public class FilmesAPI {

    @Autowired
    private FilmesRepository filmesRepository;

    // http://localhost:8080/filmes/em-exibicao?pagina=1&tamanhoDaPagina=3
    @RequestMapping(path="/em-exibicao", method= RequestMethod.GET)
    @ApiOperation(value="Buscar pagina de filmes em exibicao", notes="Permite" +
            "a busca paginada de filmes em exibicao,"+"ou seja, filmes que " +
            "possuem data de inicio e termino de exibicao e cujo periodo engloba" +
            "a data atual")
    public Page<Filme> getEmExibicao(Pageable parametrosDePaginacao) {
        if (parametrosDePaginacao == null) {
            parametrosDePaginacao = new PageRequest(0, 3);
        }
        LocalDate hoje = LocalDate.now();
        return this.filmesRepository.buscarPaginaEmExibicao(parametrosDePaginacao, hoje);
    }

    @GetMapping("/{id}")
    public Filme getPorId(@PathVariable UUID id) {
        Filme entidade = this.filmesRepository.findOne(id);
        if(entidade == null)
        {
            throw new EntitdadeNaoEncontradaException();
        }
        return entidade;
    }

}
