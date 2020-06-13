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

import br.com.serasa.dtos.ExportacaoDTO;

@Service
public class CsvServico {
	
	public List<ExportacaoDTO> converterCsvParaExportacaoDto(MultipartFile arquivoCsv){
		HeaderColumnNameTranslateMappingStrategy<ExportacaoDTO> strat = mapearCabecalho();
		CsvToBean<ExportacaoDTO> csvToExportacao = null;
		try (Reader reader = new BufferedReader(new InputStreamReader(arquivoCsv.getInputStream()))) {
			csvToExportacao = new CsvToBeanBuilder<ExportacaoDTO>(reader)
                    .withType(ExportacaoDTO.class)
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
	
	private HeaderColumnNameTranslateMappingStrategy<ExportacaoDTO> mapearCabecalho() {
		HeaderColumnNameTranslateMappingStrategy<ExportacaoDTO> strat = 
				new HeaderColumnNameTranslateMappingStrategy<ExportacaoDTO>();
	    strat.setType(ExportacaoDTO.class);
	    Map<String, String> map = new HashMap<String, String>();
	    map.put("Empresa", "empresa");
	    map.put("Emissoes", "emissoes");
	    map.put("Debitos", "debitos");
	    strat.setColumnMapping(map);
	    return strat;
	}

}
