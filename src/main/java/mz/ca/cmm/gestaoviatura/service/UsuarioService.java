package mz.ca.cmm.gestaoviatura.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mz.ca.cmm.gestaoviatura.datatables.Datatables;
import mz.ca.cmm.gestaoviatura.datatables.DatatablesColunas;
import mz.ca.cmm.gestaoviatura.dominio.Perfil;
import mz.ca.cmm.gestaoviatura.dominio.Usuario;
import mz.ca.cmm.gestaoviatura.repository.UsuarioRepository;




@Service
public class UsuarioService implements UserDetailsService {

	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private Datatables datatables;
	
	@Transactional(readOnly = true)
	public Usuario buscarPorEmail(String email) {
		
		return repository.findByEmail(email);
	}

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { // Para testar se user esta logado no sistema ou nao
		Usuario usuario =  buscarPorEmail(username);
		return new User(		//Este objecto "User", eh o user da sessao 
			usuario.getEmail(),
			usuario.getSenha(),
			AuthorityUtils.createAuthorityList(getAtuthorities(usuario.getPerfis())) //"getAtuthorities" eh o metodo criado a seguir que converte a lista de perfis em Array de perfis
		);
	}
	
	private String[] getAtuthorities(List<Perfil> perfis) { // No final das contas o getAtuthorities eh uma lista dos perfis (descricao) do usuario retornado pela consulta do Username 
		String[] authorities = new String[perfis.size()];
		for (int i = 0; i < perfis.size(); i++) {
			authorities[i] = perfis.get(i).getDesc();
		}
		return authorities;
	}
		
	
/*
	@Transactional(readOnly = true)
	public Map<String, Object> buscarTodos(HttpServletRequest request) {
		datatables.setRequest(request);
		datatables.setColunas(DatatablesColunas.USUARIOS); // As colunas que desejamos mostrar na tela sao da classe Usuario 
		Page<Usuario> page = datatables.getSearch().isEmpty()
				? repository.findAll(datatables.getPageable())  //caso o usuario nao escreva nada traz todos os dados
				: repository.findByEmailOrPerfil(datatables.getSearch(), datatables.getPageable()); // de contario traz o resultado metodo findAllByDesignacao. O datatables.getSearch() permite pegar o valor que esta a ser enviado pelo utilizar na no campo de pesquisa e o datatables.getPageable() passa os dados de paginacao e ordenacao
		return datatables.getResponse(page); // Aqui passasmos a variavel page que tera o retorno da consulta, seja ela vazia ou atraves de emailOuPerfil
	}
	
	@Transactional(readOnly = false)
	public void salvarUsuario(Usuario usuario) {	
		String senhaCriptografada = new BCryptPasswordEncoder().encode(usuario.getSenha()); //Antes de salvar o usuario na BD eh preciso criptografar a senha usando algum tipo de criptografia. Neste caso sera a BCrypt
		usuario.setSenha(senhaCriptografada);
		repository.save(usuario);		
	}
 
	@Transactional(readOnly = true)
	public Usuario buscarPorId(Long id) {
		return repository.findById(id).get();
	}

	@Transactional(readOnly = true)
	public Usuario buscarPorIdEPerfis(Long usuarioId, Long[] perfisId) {
		
		return repository.findByIdAndPerfis(usuarioId, perfisId)		// Aqui estabelecemos o link entre o metodo "usuarioNaoEncontradoException" e a excepcao "UsernameNotFoundException" quando ela ocorrer no sistema
				.orElseThrow(() -> new UsernameNotFoundException ("Usuario inexistente!"));			//Se o optional do repository encontar um usuario e sera recuperado, de contrario uma excepcao sera lancada. 
	}
	
	@Transactional(readOnly = true)
	public static boolean isSenhaCorreta(String senhaDigitada, String senhaArmazenada) {
		
		return new BCryptPasswordEncoder().matches(senhaDigitada, senhaArmazenada);			//o metodo 'matches' compara a senhaArmazenada (que esta criptografada) com a senhaDigitada que nao esta criptografada
	}
	
	@Transactional(readOnly = false)
	public void alterarSenha(Usuario usuario, String senha) {
		usuario.setSenha(new BCryptPasswordEncoder().encode(senha));
		repository.save(usuario);
	}
	
	*/
	
}
