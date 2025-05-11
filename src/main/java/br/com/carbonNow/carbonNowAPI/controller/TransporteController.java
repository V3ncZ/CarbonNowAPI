package br.com.carbonNow.carbonNowAPI.controller;

import br.com.carbonNow.carbonNowAPI.domain.Transporte;
import br.com.carbonNow.carbonNowAPI.dto.TransporteCadastroDto;
import br.com.carbonNow.carbonNowAPI.dto.TransporteExibicaoDto;
import br.com.carbonNow.carbonNowAPI.service.TransporteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/carbonnow")
public class TransporteController {

    @Autowired
    private TransporteService transporteService;

    @PostMapping("/cadastrarTransporte")
    @ResponseStatus(HttpStatus.CREATED)
    public TransporteExibicaoDto cadastrarTransporte(TransporteCadastroDto transporteCadastroDto) {
        return transporteService.salvarTransporte(transporteCadastroDto);
    }

    @GetMapping("/listarTransportes")
    @ResponseStatus(HttpStatus.OK)
    public Page<TransporteExibicaoDto> listarTransportes(Pageable pageable) {
        return transporteService.listarTransportes(pageable);
    }

    @DeleteMapping("/deletarTransporte/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deletarTransporte(@PathVariable Long id) {
        transporteService.deletarTransporte(id);
    }

    @PutMapping("/atualizarTransporte")
    @ResponseStatus(HttpStatus.OK)
    public Transporte atualizarTransporte(@RequestBody @Valid Transporte transporte) {
        return transporteService.atualizarTransporte(transporte);
    }
}
