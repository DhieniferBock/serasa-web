package br.com.serasa.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.serasa.entidades.DadosBancarios;

public interface DadosBancariosRepositorio extends JpaRepository<DadosBancarios, Long>{

}