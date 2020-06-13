package br.com.serasa.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.serasa.entidades.Empresa;

public interface EmpresaRepositorio extends JpaRepository<Empresa, Long>{
	
	Empresa findByNomeLike(String nome);

}
