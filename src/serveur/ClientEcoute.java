package serveur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientEcoute implements Runnable {

	private String pseudo;
	
	private Socket socket;
	
	public ClientEcoute(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		System.out.println(socket.getInetAddress()+ " en attente d'authentification");
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			pseudo = in.readLine();
			Serveur.emetteur.envoyer(pseudo + " a rejoint le tchat");
			while (true) {
				// Attente d'un message
				String msg = in.readLine();

				// Emission du message
				Serveur.emetteur.envoyer(pseudo, msg);

			}

		} catch (IOException e) {
			Serveur.emetteur.envoyer(pseudo + " a quitter le tchat");
		}
	}

}
