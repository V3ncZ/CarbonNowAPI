package br.com.carbonNow.carbonNowAPI.controller;

import br.com.carbonNow.carbonNowAPI.domain.ItemEletrico;
import br.com.carbonNow.carbonNowAPI.domain.Usuario;
import br.com.carbonNow.carbonNowAPI.dto.ItemEletricoCadastroDto;
import br.com.carbonNow.carbonNowAPI.dto.ItemEletricoExibicaoDto;
import br.com.carbonNow.carbonNowAPI.service.ItemEletricoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carbonnow")
public class ItemEletricoController {

    @Autowired
    private ItemEletricoService itemEletricoService;

    @GetMapping("/buscarItemEletrico/{nome}")
    @ResponseStatus(HttpStatus.OK)
    public ItemEletrico buscarItemEletrico(@PathVariable String nome) {
        return itemEletricoService.encontrarPeloNome(nome);
    }

    @PostMapping("/cadastrarItemEletrico")
    @ResponseStatus(HttpStatus.CREATED)
    public ItemEletricoExibicaoDto cadastrarItemEletrico(@Valid @RequestBody ItemEletricoCadastroDto itemEletricoCadastroDto) {
        var usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return itemEletricoService.salvarItemEletrico(itemEletricoCadastroDto, usuario);
    }

    @GetMapping("/listarItensEletricos")
    @ResponseStatus(HttpStatus.OK)
    public Page<ItemEletricoExibicaoDto> listarItensEletricos(Pageable pageable) {
        return itemEletricoService.listarItensEletricos(pageable);
    }

    @DeleteMapping("/deletarItemEletrico/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deletarItemEletrico(@PathVariable Long id) {
        itemEletricoService.deletarItemEletrico(id);
    }

    @PutMapping("/atualizarItemEletrico")
    @ResponseStatus(HttpStatus.OK)
    public ItemEletrico atualizarItemEletrico(@RequestBody ItemEletrico itemEletrico) {
        return itemEletricoService.atualizarItemEletrico(itemEletrico);
    }
}
