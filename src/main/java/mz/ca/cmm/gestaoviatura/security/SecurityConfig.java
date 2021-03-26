package mz.ca.cmm.gestaoviatura.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import mz.ca.cmm.gestaoviatura.dominio.PerfilTipo;
import mz.ca.cmm.gestaoviatura.service.UsuarioService;





//@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private static final String ADMIN = PerfilTipo.ADMIN.getDesc();
    private static final String FUNCIONARIO = PerfilTipo.FUNCIONARIO.getDesc();

	
	@Autowired
	private UsuarioService service;
	
   
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
		
		// acessos públicos liberados 
		.antMatchers("/webjars/**", "/css/**", "/image/**", "/js/**").permitAll() // Recursos estaticos. URLs baseadas em CDN nao sao bloquedas por isso nao ha necessidade de liberar
		.antMatchers("/", "/home").permitAll()
		
		// acessos privados admin
		.antMatchers("/cidades/").hasAuthority(ADMIN)	
		.antMatchers("/u/**").hasAuthority(ADMIN)			//Apenas ADMIN tem acesso ao controller de Usuaraios
		
		// acessos privados funcionarios
		.antMatchers("/funcionarios/**", "/provincias/**").hasAuthority(FUNCIONARIO)			//Apenas FUNCIONARIO tem acesso ao controller de Funcionarios. "Excepto outras uri deste controller que tenham sido liberadas para outros perfis"
/*		

		
		.antMatchers("/u/novo/cadastro", "/u/cadastro/realizado", "/u/cadastro/utente/salvar").permitAll()
		.antMatchers("/confirmacao/cadastro").permitAll()
		.antMatchers("/u/p/**").permitAll()

*/
		.anyRequest().authenticated()
		.and()
			.formLogin()
			.loginPage("/login")
			.defaultSuccessUrl("/", true)			// Em caso de Sucesso no Login o utilizador e redirecionada para esta pagina. Neste caso, "/" eh a raiz
			.failureUrl("/login-error")			// URI para o caso de falha no login
			.permitAll()			// Todo o user mesmo aquele que nao esta logado deve/tem permissao para visualizar a pagina de login e erro
		.and()
			.logout()
			.logoutSuccessUrl("/")			// Ao fazer o logout a aplicacao leva-te para a pagina raiz
		.and()
			.rememberMe()
			;		
	}
	
		
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(service).passwordEncoder(new BCryptPasswordEncoder());
	} 

	 
	
}
