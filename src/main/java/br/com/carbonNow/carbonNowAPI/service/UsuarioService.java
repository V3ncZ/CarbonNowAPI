package br.com.carbonNow.carbonNowAPI.service;

import br.com.carbonNow.carbonNowAPI.domain.Usuario;
import br.com.carbonNow.carbonNowAPI.dto.UsuarioCadastroDto;
import br.com.carbonNow.carbonNowAPI.dto.UsuarioExibicaoDto;
import br.com.carbonNow.carbonNowAPI.exception.UsuarioNaoEncontradoException;
import br.com.carbonNow.carbonNowAPI.repository.UsuarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioExibicaoDto salvarUsuario(UsuarioCadastroDto usuarioCadastroDto) {

        //Instanciar um usuario
        Usuario usuario = new Usuario();

        //Copiar as propriedades do DTO para o usuario
        BeanUtils.copyProperties(usuarioCadastroDto, usuario);

        //Salvar o usuario no banco de dados
        Usuario usuarioSalvo = usuarioRepository.save(usuario);

        //Retornar o DTO com os dados do usuario salvo
        return new UsuarioExibicaoDto(usuarioSalvo);
    }

    public void deletarUsuario(Long id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);

        if (usuarioOptional.isPresent()) {
            usuarioRepository.delete(usuarioOptional.get());
            System.out.println("Usuário deletado com sucesso");
        } else {
            throw new UsuarioNaoEncontradoException("Usuário não encontrado");
        }
    }

    public Page<UsuarioExibicaoDto> listarUsuarios(Pageable pageable) {
        return usuarioRepository.findAll(pageable)
                .map(UsuarioExibicaoDto::new);
    }

    public Usuario atualizarUsuario(Usuario usuario) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(usuario.getId());

        if (usuarioOptional.isPresent()) {
            Usuario usuarioExistente = usuarioOptional.get();
            BeanUtils.copyProperties(usuario, usuarioExistente, "id");
            return usuarioRepository.save(usuarioExistente);
        } else {
            throw new UsuarioNaoEncontradoException("Usuário com Id " + usuario.getId() + " não encontrado");
        }
    }

    public UsuarioExibicaoDto buscarUsuarioPorId(Long id) {

        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);

        if (usuarioOptional.isPresent()) {
            return new UsuarioExibicaoDto(usuarioOptional.get());
        } else {
            throw new UsuarioNaoEncontradoException("Usuário não encontrado");
        }
    }

    public Usuario buscarUsuarioPorEmail(String email) {
        return (Usuario) usuarioRepository.findByEmail(email);
    }
}
