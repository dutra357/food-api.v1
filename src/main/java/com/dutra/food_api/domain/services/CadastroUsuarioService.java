package com.dutra.food_api.domain.services;

import com.dutra.food_api.api.model.input.SenhaInput;
import com.dutra.food_api.api.model.input.UsuarioInput;
import com.dutra.food_api.api.model.input.UsuarioUpdateInput;
import com.dutra.food_api.api.model.output.UsuarioOutput;
import com.dutra.food_api.domain.models.Usuario;
import com.dutra.food_api.domain.repositories.UsuarioRepository;
import com.dutra.food_api.domain.services.exceptions.EntidadeEmUsoException;
import com.dutra.food_api.domain.services.exceptions.EntidadeNaoEncontradaException;
import com.dutra.food_api.domain.services.exceptions.UsuarioExistenteException;
import com.dutra.food_api.domain.services.exceptions.ValidationException;
import com.dutra.food_api.domain.services.interfaces.CadastroUsuarioInterface;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CadastroUsuarioService implements CadastroUsuarioInterface {

    private static final String USUARIO_NOT_FOUND = "Usuário não encontrado.";

    private final UsuarioRepository usuarioRepository;

    public CadastroUsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    @Override
    public UsuarioOutput salvar(UsuarioInput usuarioInput) {

        if (verificaEmail(usuarioInput.getEmail())) {
            throw new UsuarioExistenteException("Usuário já cadastrado.");
        }

        Usuario novoUsuario = usuarioInput.toEntity();
        return UsuarioOutput.toUsuarioOutput(usuarioRepository.save(novoUsuario));
    }

    @Transactional
    @Override
    public UsuarioOutput atualizar(Long id, UsuarioUpdateInput usuarioUpdateInput) {
        Usuario usuario = buscaUsuarioInterna(id);
        usuario.setNome(usuarioUpdateInput.getNome());
        usuario.setEmail(usuarioUpdateInput.getEmail());

        return UsuarioOutput.toUsuarioOutput(usuario);
    }

    @Transactional(readOnly = true)
    @Override
    public UsuarioOutput buscarPorId(Long usuarioId) {
        return UsuarioOutput.toUsuarioOutput(buscaUsuarioInterna(usuarioId));
    }

    @Transactional(readOnly = true)
    @Override
    public List<UsuarioOutput> buscarTodos() {
        return usuarioRepository.findAll().stream().map(UsuarioOutput::toUsuarioOutput).toList();
    }

    @Transactional
    @Override
    public void remover(Long usuarioId) {
        Usuario usuario = buscaUsuarioInterna(usuarioId);

        try {
            usuarioRepository.delete(usuario);
            usuarioRepository.flush();

        } catch (DataIntegrityViolationException _) {
            throw new EntidadeEmUsoException(
                    String.format("Usuário de código %d não pode ser removido, pois está em uso", usuarioId));
        }
    }

    @Transactional
    @Override
    public void alterarSenha(Long usuarioId, SenhaInput senhaInput) {
        Usuario usuario = buscaUsuarioInterna(usuarioId);

        if (verificaSenha(senhaInput.getSenhaAtual(), usuario)) {
            throw new ValidationException("Senha atual informada não coincide com a senha do usuário.");
        }

        usuario.setSenha(senhaInput.getNovaSenha());
        usuarioRepository.save(usuario);
    }

    private boolean verificaSenha(String senhaAtual, Usuario usuario) {
        return senhaAtual.equals(usuario.getSenha());
    }

    private boolean verificaEmail(String email) {
        return usuarioRepository.findByEmail(email).isPresent();
    }

    private Usuario buscaUsuarioInterna(Long usuarioId) {
        return usuarioRepository.findById(usuarioId)
                .orElseThrow( () -> new EntidadeNaoEncontradaException(USUARIO_NOT_FOUND));
    }
}
