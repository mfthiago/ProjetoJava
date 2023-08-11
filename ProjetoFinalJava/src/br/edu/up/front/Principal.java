package br.edu.up.front;

public class Principal {
	public static void main(String[] args) {
		int opc;
		do {
			System.out.println("\n\n-----MENU PRINCIPAL-----");
			System.out.println("[1] - Clientes\n[2] - Estúdio\n[3] - Tecnicos\n[4] - Agenda\n[5] - Sair");
			opc = Console.readInt("\nOpção: ");
			switch (opc) {
				case 1:
					new AppCliente();
					break;
				case 2:
					new AppEstudio();
					break;
				case 3:
					new AppTecnico();
					break;
				case 4:
					new AppAgenda();
					break;
			}
		} while (opc != 5);
	}
}
