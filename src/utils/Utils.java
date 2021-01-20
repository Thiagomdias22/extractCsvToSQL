package utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

public class Utils {
	
	/*
	 * Fun��o que remover� todos os acentos de uma String.
	 * 
	 * Entrada:
	 * String str - string para analisar os acentos
	 * 
	 * Sa�da:
	 * String semAcentos - string filtrada que n�o contem acentos
	 * */
	public static String removerAcentos(String str) {
		String semAcentos = Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
	    return semAcentos;
	}
	
	/*
	 * Fun��o que ir� coletar todos os dados de um arquivo csv
	 * 
	 * Entrada:
	 * String path - informar� o caminho do arquivo para leitura
	 * 
	 * Sa�da:
	 * List<String[]> dados - lista de arrays String onde cada array conter�
	 * os campos coletados de cada linha do arquivo.
	 * */
	public static List<String[]> getDataCSV(String path) {

		String separador = ";";

		List<String[]> dados = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"))) {
			String line;
			while ((line = br.readLine()) != null) {
				String values[] = line.split(separador);
				dados.add(values);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return dados;
	}
	
	/*
	 * Fun��o para obter o caminho das bases
	 * 
	 * Entrada:
	 * Base base - enum que informar� qual base desejada
	 * 
	 * Sa�da:
	 * String path - caminho para acessar o csv da base, que est� na raiz do projeto
	 * */
	public static String getPath(Base base) {

		String path = System.getProperty("user.dir") + "\\";

		switch (base) {
			case INFRACOES:
				path += "dadosInfracoes_10_2020.csv";
				break;
			case CONDUTORES_HAB:
				path += "dadosCondutoresHab_11_2020.csv";
				break;
			case FROTAS:
				path += "dadosFrota_11_2020.csv";
				break;
		}

		return path;
	}
	
}