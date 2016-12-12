package serveur;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Serveur {

	public static ArrayList<Socket> sockets = new ArrayList<Socket>();
	public static ServeurEmetteur emetteur = new ServeurEmetteur();
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Choisir un port : ");
		
		try {
			ServerSocket ss = new ServerSocket(Integer.valueOf(sc.nextLine()));
			System.out.println("Serveur OK");
			Thread emission = new Thread(emetteur);
			emission.start();
			
			while(true){
				Socket s = ss.accept();
				Thread ecoute = new Thread(new ClientEcoute(s));
				ecoute.start();
				sockets.add(s);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
