package br.com.senacrs.alp.aulas;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class MeuObterRequisicaoGet implements ObterRequisicaoGet {

	private static final String ESPACO = " ";
	private static final String PWD = System.getProperty("user.dir");
	private static final String DIR_SEP = "/";
	private static final String INDEX_HTML = "index.html";
	private static final String ERROR_404_HTML = "error_404.html";
	private static final String HTTP_1_0_200_OK = "HTTP/1.0 200 OK";
	private static final String HTTP_1_0_404_NOT_FOUND = "HTTP/1.0 404 NotFound";

	private ArquivoConfiguracao config = null;

	public MeuObterRequisicaoGet(ArquivoConfiguracao config) {

		this.config = config;
	}

	@Override
	public String[] obterResposta(Reader requisicao) {

		String[] resultado = null;
		String path = null;
		String caminhoAbsoluto = null;

		try {
			path = obterPathRequisicao(requisicao);
			caminhoAbsoluto = obterCaminhoAbsoluto(config.getRootDir(), path);
			resultado = responderCaminhoAbsoluto(caminhoAbsoluto);
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}

		return resultado;
	}

	private String obterPathRequisicao(Reader requisicao) throws IOException {

		String resultado = null;
		String requisicaoConteudo = null;
		String[] parcial = null;

		requisicaoConteudo = obterConteudoRequisicao(requisicao);
		parcial = requisicaoConteudo.split(ESPACO);
		resultado = parcial[1];

		return resultado;
	}

	private String obterConteudoRequisicao(Reader requisicao)
			throws IOException {

		StringBuilder builder = null;
		int charInt = 0;

		builder = new StringBuilder();
		while ((charInt = requisicao.read()) >= 0) {
			builder.append((char) charInt);
		}

		return builder.toString();
	}

	private String obterCaminhoAbsoluto(String pai, String path) {
		
		String resultado = null;
		String caminhoRelativo = null;
		
		caminhoRelativo = pai + path; // ./html + /autor
		caminhoRelativo = caminhoRelativo.replace(DIR_SEP, File.separator); // .\html\autor
		resultado = PWD + caminhoRelativo;

		return resultado;
	}

	private String[] responderCaminhoAbsoluto(String caminhoAbsoluto) throws IOException {
		
		String[] resultado = null;
		File file = null;
		File index = null;
		
		file = new File(caminhoAbsoluto);
		if (file.exists()) {
			if (file.isFile()) {
				resultado = responderOk(file);
			} else {
				index = new File(file, INDEX_HTML);
				if (index.exists() && index.isFile()) {
					resultado = responderOk(index);
				} else {
					responderNok();
				}
			}
		} else {
			resultado = responderNok();
		}
		
		return resultado;
	}

	private String[] responderOk(File file) throws IOException {

		String[] resultado = null;
		
		resultado = responderFile(HTTP_1_0_200_OK, file);
		
		return resultado;
	}

	private String[] responderNok() throws IOException {

		String[] resultado = null;
		String error = null;
		File arquivoErro = null;
				
		error = obterCaminhoAbsoluto(config.getErrorDir(), DIR_SEP + ERROR_404_HTML);
		arquivoErro = new File(error);
		resultado = responderFile(HTTP_1_0_404_NOT_FOUND, arquivoErro);
		
		return resultado;
	}

	private String[] responderFile(String respostaRequisicao, File arquivo) throws IOException {
		
		String[] resultado = null;
		List<String> parcial = null;
		
		parcial = new ArrayList<String>();
		parcial.addAll(obterCabecalho(respostaRequisicao, arquivo));
		parcial.add(NOVA_LINHA);
		parcial.addAll(obterConteudo(arquivo));
		resultado = new String[parcial.size()];
		resultado = parcial.toArray(resultado);

		return resultado;
	}

	private List<String> obterCabecalho(
			String respostaRequisicao, File arquivo) {
		
		List<String> resultado = null;
		String data = null;
		String server = null;
		String tamanho = null;
		
		resultado = new ArrayList<String>();
		resultado.add(respostaRequisicao + NOVA_LINHA);
		data = obterDataFormatada();
		resultado.add("Date: " + data + NOVA_LINHA);
		server = "Server: " + SERVER;
		resultado.add(server + NOVA_LINHA);
		tamanho = "Content-Length: " + arquivo.length(); 
		resultado.add(tamanho + NOVA_LINHA);
		resultado.add("Content-Type: text/html; charset=utf-8" + NOVA_LINHA);
		resultado.add("Connection: close" + NOVA_LINHA);

		return resultado;
	}

	private String obterDataFormatada() {
		
		String resultado = null;
		DateFormat formatador = null;
		
		formatador = new SimpleDateFormat(
	            "EEE, dd MMM yyyy HH:mm:ss z",
	            Locale.getDefault());
	    formatador.setTimeZone(TimeZone.getTimeZone("GMT"));
	    resultado = formatador.format(DATE);

		return resultado;
	}

	private List<String> obterConteudo(File arquivo) throws IOException {
		
		List<String> resultado = null;
		Reader reader = null;
		BufferedReader buf = null;
		String linha = null;
		
		resultado = new ArrayList<String>();
		reader = new FileReader(arquivo);
		buf = new BufferedReader(reader);
		while ((linha = buf.readLine()) != null) {
			resultado.add(linha/* + NOVA_LINHA*/);
		}
		buf.close();

		return resultado;
	}
}
