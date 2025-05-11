package br.com.carbonNow.carbonNowAPI.controller;

import br.com.carbonNow.carbonNowAPI.domain.ItemEletrico;
import br.com.carbonNow.carbonNowAPI.dto.ItemEletricoCadastroDto;
import br.com.carbonNow.carbonNowAPI.dto.ItemEletricoExibicaoDto;
import br.com.carbonNow.carbonNowAPI.service.ItemEletricoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
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
    public ItemEletricoExibicaoDto cadastrarItemEletrico(ItemEletricoCadastroDto itemEletricoCadastroDto) {
        return itemEletricoService.salvarItemEletrico(itemEletricoCadastroDto);
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
