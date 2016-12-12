package client;

import java.net.Socket;
import java.util.Scanner;

public class Client {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Ip du serveur : ");
		String ip = sc.nextLine();
		System.out.print("Port du serveur : ");
		int port = Integer.valueOf(sc.nextLine());
		
		try {
			Socket socket = new Socket(ip, port);

			Thread emmission = new Thread(new ClientEmetteur(socket));
			emmission.start();
			
			System.out.print("Saisir votre pseudo : ");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
