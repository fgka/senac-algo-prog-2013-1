package br.com.senacrs.alp.aulas.sockets;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClienteMain {

	private static final String MENSAGEM_BOM_DIA = "mensagem_bom_dia";
	private static final int PORT_54321 = 54321;
	private static final String IP_LOCALHOST = "127.0.0.1";

	public static void main(String[] args) {

		Socket client = null;
		ManipuladorSocket obj = null;

		try {
			client = new Socket(IP_LOCALHOST, PORT_54321);
			obj = new ManipuladorSocket(client);
			obj.enviarTexto(MENSAGEM_BOM_DIA);
			consumirTexto(obj);
			obj.finalizar();
		} catch (UnknownHostException e) {
			throw new IllegalArgumentException(e);
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		} finally {
			closeSocket(client);
		}
	}

	private static void consumirTexto(ManipuladorSocket obj) {

		String line = null;

		line = obj.receberTexto();
		System.out.println(line);
	}

	private static void closeSocket(Socket client) {
		
		if (client != null) {
			try {
				client.close();
			} catch (IOException e) {
				throw new IllegalStateException(e);
			}
		}
	}

}
