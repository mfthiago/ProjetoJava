package br.edu.up.front;

import br.edu.up.entidades.Cliente;
import br.edu.up.entidades.Estudio;
import br.edu.up.persistencia.ClientePersistencia;
import br.edu.up.persistencia.EstudioPersistencia;
import br.edu.up.entidades.Tecnico;

public class AppEstudio {
    public AppEstudio() {

        int opc;
        do {
            System.out.println("\n\n-----ESTÚDIOS-----");
            System.out.println(
                    "\n[1] - Novo estúdio\n[2] - Listar estúdios\n[3] - Listar todos os estúdios\n[4] - Excluir estúdios\n[5] - Sair");
            opc = Console.readInt("Informe a opção: ");
            switch (opc) {
                case 1:
                    novoEstudio();
                    break;
                case 2:
                    listarEstudio();
                    break;
                case 3:
                    listartodosEstudios();
                    break;
                case 4:
                    excluirEstudio();
                    break;
            }

        } while (opc != 5);
    }

    private static void novoEstudio() {
        System.out.println("-----NOVO ESTUDIO-----");
        Estudio objEstudio = new Estudio();
        objEstudio.setEndereco(Console.readString("Informe o endereço: "));
        if (EstudioPersistencia.procurarPorEndereco(objEstudio) == null) {
            objEstudio.setNome(Console.readString("Informe o nome do Estudio: "));
            if (EstudioPersistencia.procurarPorNome(objEstudio) == null) {
                objEstudio.setValor(Console.readDouble("Informe o valor da hora no estúdio: "));
                if (EstudioPersistencia.incluir(objEstudio) == true) {
                    System.out.println("Estúdio cadastrado.");
                } else {
                    System.out.println("Não foi possível cadastrar o estúdio");
                }
            } else {
                System.out.println("Nome já utilizado.");
            }
        } else {
            System.out.println("Estudio já cadastrado.");
        }
    }

    public static void listarEstudio() {
        System.out.println("-----LISTAR ESTUDIOS-----");
        Estudio objEstudio = new Estudio();
        objEstudio.setNome(Console.readString("Informe o nome do estúdio\n"));
        objEstudio = EstudioPersistencia.procurarPorNome(objEstudio);
        if (objEstudio != null) {
            System.out.println("--------------------");
            System.out.println("ID: " + objEstudio.getId());
            System.out.println("Endereço: " + objEstudio.getEndereco());
            System.out.println("Nome: " + objEstudio.getNome());
            System.out.println("Valor da hora: " + objEstudio.getValor());
            for (Tecnico item : objEstudio.getTecnicos()) {
                System.out.println("Técnico: " + item.getNome());
            }
            System.out.println("--------------------");
        }
    }

    private static void alterarEstudios() {
        System.out.println("\n\n-----ALTERAÇÃO DE ESTUDIO-----");
        Estudio objEstudio = new Estudio();
        objEstudio.setNome(Console.readString("Informe o nome do estúdio a ser alterado: "));
        objEstudio = EstudioPersistencia.procurarPorNome(objEstudio);
        if (objEstudio != null) {
            System.out.println("\n\n-------------------------");
            System.out.println("ID: " + objEstudio.getId());
            System.out.println("Nome: " + objEstudio.getNome());
            System.out.println("Endereço: " + objEstudio.getEndereco());
            System.out.println("-------------------------");
            String resp = Console.readString("Quer alterar os dados deste cliente? ");
            if (resp.equals("S")) {
                objEstudio.setNome(Console.readString("Informe o novo nome para o estúdio: "));
                objEstudio.setEndereco(Console.readString("Informe o novo endereço do estúdio: "));
                if (EstudioPersistencia.alterar(objEstudio) == true) {
                    System.out.println("A alteração foi realizada...");
                } else {
                    System.out.println("A alteração não pôde ser realizada no momento...");
                }
            }
        } else {
            System.out.println("\n\nEstúdio não encontrado...");
        }
    }

    private static void excluirEstudio() {
        String opc;
        System.out.println("-----EXCLUIR ESTUDIO-----");
        Estudio objEstudio = new Estudio();
        objEstudio.setNome(Console.readString("Informe o nome do estudio a ser excluído: "));
        objEstudio = EstudioPersistencia.procurarPorNome(objEstudio);
        if (objEstudio != null) {
            System.out.println("--------------------");
            System.out.println("ID: " + objEstudio.getId());
            System.out.println("Endereço: " + objEstudio.getEndereco());
            System.out.println("Nome: " + objEstudio.getNome());
            System.out.println("Valor da hora: " + objEstudio.getValor());
            System.out.println("--------------------");
            opc = Console.readString("Deseja excluir esse estúdio?\n[S] [N]\n");
            if (opc.equals("S")) {
                if (EstudioPersistencia.excluir(objEstudio) == true) {
                    System.out.println("Estúdio excluído.");
                } else {
                    System.out.println("Não foi possível excluir o estúdio");
                }
            }
        } else {
            System.out.println("Estudio não encontrado.");
        }

    }

    public static void listartodosEstudios() {
        System.out.println("\n\n-----LISTA DE ESTUDIOS-----");
        Estudio objEstudio = new Estudio();
        objEstudio
                .setNome(Console.readString("\nEnter para listar todos. "));
        if (EstudioPersistencia.getEstudios(objEstudio) != null) {
            for (Estudio item : EstudioPersistencia.getEstudios(objEstudio)) {
                System.out.println("ID: " + item.getId());
                System.out.println("Nome: " + item.getNome());
                System.out.println("Endereço: " + item.getEndereco());
                System.out.println("Valor da hora: " + item.getValor());
                for (Tecnico itemTecnico : objEstudio.getTecnicos()) {
                    System.out.println("Estudio: " + itemTecnico.getNome());
                }
                System.out.println("---------------------------");
            }
        } else {
            System.out.println("Estúdio não encontrado.");
        }
    }
}
