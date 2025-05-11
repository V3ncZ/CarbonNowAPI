package br.com.carbonNow.carbonNowAPI.service;
import br.com.carbonNow.carbonNowAPI.domain.Transporte;
import br.com.carbonNow.carbonNowAPI.dto.TransporteCadastroDto;
import br.com.carbonNow.carbonNowAPI.dto.TransporteExibicaoDto;
import br.com.carbonNow.carbonNowAPI.exception.TransporteNaoEncontradoException;
import br.com.carbonNow.carbonNowAPI.repository.TransporteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransporteService {

    @Autowired
    private TransporteRepository transporteRepository;

    public Transporte encontrarTransportePorNome(String name) {
        return (Transporte) transporteRepository.findByName(name);
    }

    public TransporteExibicaoDto salvarTransporte(TransporteCadastroDto transporteCadastroDto) {
        Transporte transporte = new Transporte();

        BeanUtils.copyProperties(transporteCadastroDto, transporte);

        Transporte transporteSalvo = transporteRepository.save(transporte);

        return new TransporteExibicaoDto(transporteSalvo);
    }

    public void deletarTransporte(Long id) {
        Optional<Transporte> transporteOptional = transporteRepository.findById(id);

        if (transporteOptional.isPresent()) {
            transporteRepository.delete(transporteOptional.get());
            System.out.println("Transporte deletado com sucesso");
        } else {
            throw new TransporteNaoEncontradoException("Transporte não encontrado");
        }
    }

    public Page<TransporteExibicaoDto> listarTransportes(Pageable pageable) {
        return transporteRepository.findAll(pageable)
                .map(TransporteExibicaoDto::new);
    }

    public Transporte atualizarTransporte(Transporte transporte) {
        Optional<Transporte> transporteOptional = transporteRepository.findById(transporte.getIdTransporte());

        if (transporteOptional.isPresent()) {
            Transporte transporteExistente = transporteOptional.get();
            BeanUtils.copyProperties(transporte, transporteExistente, "idTransporte");
            return transporteRepository.save(transporteExistente);
        } else {
            throw new TransporteNaoEncontradoException("Transporte com Id " + transporte.getIdTransporte() + " não encontrado");
        }
    }

}
