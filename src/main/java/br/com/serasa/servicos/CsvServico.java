package br.com.serasa.servicos;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;

import br.com.serasa.entidades.DadosBancarios;

@Service
public class CsvServico {
	
	public List<DadosBancarios> converterCsvParaDadosBancarios(MultipartFile arquivoCsv){
		HeaderColumnNameTranslateMappingStrategy<DadosBancarios> strat = mapearCabecalho();
		CsvToBean<DadosBancarios> csvToExportacao = null;
		try (Reader reader = new BufferedReader(new InputStreamReader(arquivoCsv.getInputStream()))) {
			csvToExportacao = new CsvToBeanBuilder<DadosBancarios>(reader)
                    .withType(DadosBancarios.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .withSeparator(';')
                    .withMappingStrategy(strat)
                    .build();
			return csvToExportacao.parse();
		} catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		return null;
	}
	
	private HeaderColumnNameTranslateMappingStrategy<DadosBancarios> mapearCabecalho() {
		HeaderColumnNameTranslateMappingStrategy<DadosBancarios> strat = 
				new HeaderColumnNameTranslateMappingStrategy<DadosBancarios>();
	    strat.setType(DadosBancarios.class);
	    Map<String, String> map = new HashMap<String, String>();
	    map.put("Descricao", "descricao");
	    map.put("Data", "data");
	    map.put("Tipo", "tipo");
	    map.put("Valor","valor");
	    strat.setColumnMapping(map);
	    return strat;
	}

}
