package client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientEmetteur implements Runnable {

	private Socket socket;

	public ClientEmetteur(Socket socket) {
		this.socket = socket;
	}

	@SuppressWarnings("resource")
	@Override
	public void run() {
		Scanner sc = new Scanner(System.in);
		try {
			PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
			out.println(sc.nextLine());
			out.flush();
			Thread reception = new Thread(new ServeurEcoute(socket));
			reception.start();
			while (true) {
				out.println(sc.nextLine());
				out.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
