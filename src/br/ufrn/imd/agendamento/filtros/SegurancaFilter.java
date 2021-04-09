package br.ufrn.imd.agendamento.filtros;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufrn.imd.agendamento.dominio.Funcionario;

@WebFilter("/pages/*")
public class SegurancaFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, 
			FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		Funcionario funcionarioLogado = (Funcionario) 
				req.getSession().getAttribute("funcionarioLogado");
		
		if (funcionarioLogado == null) 
			res.sendRedirect("/index.jsf");
		else 
			chain.doFilter(request, response);
	}
}