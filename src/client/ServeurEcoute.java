package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import serveur.Serveur;

public class ServeurEcoute implements Runnable {

	private Socket socket;

	public ServeurEcoute(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {

		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			while (true) {
				// Attente d'un message
				String msg = in.readLine();
				System.out.println(msg);

				// Emission
				Serveur.emetteur.envoyer(msg);

			}

		} catch (IOException e) {
			System.out.println("Connection perdu avec le serveur");
		}

	}

}
