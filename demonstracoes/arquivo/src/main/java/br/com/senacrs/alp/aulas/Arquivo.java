package br.com.senacrs.alp.aulas;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Arquivo {
	
	private final static String DIRETORIO_ENTRADA = 
			System.getProperty("user.dir")
			+ File.separator
			+ "diretorio"
			+ File.separatorChar;
	private final static String NOME_ARQUIVO_ENTRADA = "entrada.txt";
	private final static String NOME_ARQUIVO_SAIDA = "saida.txt";
	private final static String ARQUIVO_ENTRADA = DIRETORIO_ENTRADA
			+ NOME_ARQUIVO_ENTRADA;
	private final static String ARQUIVO_SAIDA = DIRETORIO_ENTRADA
			+ NOME_ARQUIVO_SAIDA;
	
	public static void main(String[] args) throws IOException {
		
		int conteudo = 0;
		System.out.println(ARQUIVO_ENTRADA);
		
		File entrada = new File(ARQUIVO_ENTRADA);
		File saida = new File(ARQUIVO_SAIDA);
		System.out.println(
				"existe entrada: " + entrada.exists() +
				" existe saída: " + saida.exists());
		InputStream in = new FileInputStream(entrada);
		OutputStream out = new FileOutputStream(saida);
		System.out.println(in.available());
		while (in.available() > 0) {
			conteudo = in.read();
			out.write((byte)conteudo);
		}
		in.close();
		out.close();
	}
}
