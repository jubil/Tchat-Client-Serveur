package serveur;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ServeurEmetteur implements Runnable {

	private boolean doitEnvoyer = false;
	private String message = "";

	public void envoyer(String pseudo, String message) {
		envoyer("<" + pseudo + "> " + message);
	}

	public void envoyer(String message) {
		this.message = message;
		doitEnvoyer = true;
	}

	@Override
	public void run() {
		while (true) {
			if (doitEnvoyer) {
				System.out.println(message);
				for (Socket socket : Serveur.sockets) {
					try {
						PrintWriter out = new PrintWriter(
								socket.getOutputStream(), true);
						out.println(message);
						out.flush();
					} catch (IOException e) {
						// e.printStackTrace();
					}
				}
				doitEnvoyer = false;
			}
		}
	}

}
