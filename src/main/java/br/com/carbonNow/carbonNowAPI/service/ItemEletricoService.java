package br.com.carbonNow.carbonNowAPI.service;

import br.com.carbonNow.carbonNowAPI.domain.ItemEletrico;
import br.com.carbonNow.carbonNowAPI.domain.Usuario;
import br.com.carbonNow.carbonNowAPI.dto.ItemEletricoCadastroDto;
import br.com.carbonNow.carbonNowAPI.dto.ItemEletricoExibicaoDto;
import br.com.carbonNow.carbonNowAPI.exception.ItemEletricoNaoEncontradoException;
import br.com.carbonNow.carbonNowAPI.repository.ItemEletricoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemEletricoService {

    @Autowired
    private ItemEletricoRepository itemEletricoRepository;

    public ItemEletrico encontrarPeloNome(String nome) {
        return itemEletricoRepository.findByNome(nome);
    }

    public ItemEletricoExibicaoDto salvarItemEletrico(ItemEletricoCadastroDto itemEletricoCadastroDto, Usuario usuario) {
        ItemEletrico itemEletrico = new ItemEletrico();

        BeanUtils.copyProperties(itemEletricoCadastroDto, itemEletrico);
        itemEletrico.setUsuario(usuario);


        ItemEletrico itemSalvo = itemEletricoRepository.save(itemEletrico);

        return new ItemEletricoExibicaoDto(itemSalvo);
    }

    public void deletarItemEletrico(Long id) {
        Optional<ItemEletrico> itemEletricoOptional = itemEletricoRepository.findById(id);

        if (itemEletricoOptional.isPresent()) {
            itemEletricoRepository.delete(itemEletricoOptional.get());
            System.out.println("Item elétrico deletado com sucesso");
        } else {
            throw new ItemEletricoNaoEncontradoException("Item elétrico não encontrado");
        }
    }

    public Page<ItemEletricoExibicaoDto> listarItensEletricos(Pageable pageable) {
        pageable = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize()
        );
        return itemEletricoRepository.findAll(pageable)
                .map(ItemEletricoExibicaoDto::new);
    }

    public ItemEletrico atualizarItemEletrico(ItemEletrico itemEletrico) {
        Optional<ItemEletrico> itemEletricoOptional = itemEletricoRepository.findById(itemEletrico.getIdItemEletrico());

        if (itemEletricoOptional.isPresent()) {
            ItemEletrico itemExistente = itemEletricoOptional.get();
            BeanUtils.copyProperties(itemEletrico, itemExistente, "idItemEletrico");
            return itemEletricoRepository.save(itemExistente);
        } else {
            throw new ItemEletricoNaoEncontradoException("Item elétrico com Id " + itemEletrico.getIdItemEletrico() + " não encontrado");
        }
    }
}
