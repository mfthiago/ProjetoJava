package br.edu.up.front;

import br.edu.up.entidades.Tecnico;
import br.edu.up.entidades.Cliente;
import br.edu.up.entidades.Estudio;
import br.edu.up.negocio.TecnicoNegocio;
import br.edu.up.persistencia.TecnicoPersistencia;
import br.edu.up.persistencia.ClientePersistencia;
import br.edu.up.persistencia.EstudioPersistencia;

public class AppTecnico {
    public AppTecnico() {
        int opc;
        do {
            System.out.println("\n\n-----TÉCNICOS-----");
            System.out.println(
                    "\n[1] - Novo técnico\n[2] - Listar técnico\n[3] - Listar todos os técnicos\n[4] - Alterar técnicos\n[5] - Excluir técnico\n[6] - Sair");
            opc = Console.readInt("Informe a opção: ");
            switch (opc) {
                case 1:
                    novoTecnico();
                    break;
                case 2:
                    listarTecnico();
                    break;
                case 3:
                    listartodosTecnicos();
                    break;
                case 4:
                    alterarTecnicos();
                    break;
                case 5:
                    excluirTecnico();
                    break;
            }

        } while (opc != 5);
    }

    private static void novoTecnico() {
        String resp;
        System.out.println("-----NOVO TÉCNICO-----");
        Tecnico objTecnico = new Tecnico();
        objTecnico.setCpf(Console.readString("Informe o CPF: "));
        if (TecnicoNegocio.isCPF(objTecnico.getCpf()) == true) {
            if (TecnicoPersistencia.procurarPorCPF(objTecnico) == null) {
                objTecnico.setNome(Console.readString("Informe o nome do técnico: "));
                do {
                    Estudio objEstudio = new Estudio();
                    objEstudio.setNome(Console.readString("Informe o estúdio que o técnico pode atender: "));
                    objEstudio = EstudioPersistencia.procurarPorNome(objEstudio);
                    boolean estudioExistente = false;
                    for (Estudio item : objTecnico.getEstudios()) {
                        if (objEstudio.getNome().equals(item.getNome())) {
                            estudioExistente = true;
                            break;
                        }
                    }
                    if (!estudioExistente) {
                        objTecnico.getEstudios().add(objEstudio);
                        objEstudio.getTecnicos().add(objTecnico);
                        System.out.println("Estúdio adicionado.");
                    } else {
                        System.out.println("Estúdio já cadastrado.");
                    }
                    resp = Console.readString("Deseja adicionar outro estúdio? \n[S] [N]\n ");
                } while (resp.equals("S"));
                if (TecnicoPersistencia.incluir(objTecnico) == true) {
                    System.out.println("Técnico cadastrado.");
                } else {
                    System.out.println("Não foi possível cadastrar o técnico.");
                }
            } else {
                System.out.println("Técnico já cadastrado.");
            }
        } else {
            System.out.println("CPF inválido");
        }
    }

    private static void alterarTecnicos() {
        System.out.println("\n\n-----ALTERAÇÃO DE TECNICOS-----");
        Tecnico objTecnico = new Tecnico();
        objTecnico.setCpf(Console.readString("Informe o CPF a ser consultado: "));
        objTecnico = TecnicoPersistencia.procurarPorCPF(objTecnico);
        if (objTecnico != null) {
            System.out.println("\n\n-------------------------");
            System.out.println("ID: " + objTecnico.getId());
            System.out.println("Nome: " + objTecnico.getNome());
            System.out.println("CPF: " + objTecnico.getCpf());
            System.out.println("-------------------------");
            String resp = Console.readString("Quer alterar os dados deste técnico? ");
            if (resp.equals("S")) {
                objTecnico.setNome(Console.readString("Informe o novo nome para o técnico: "));
                do {
                    Estudio objEstudio = new Estudio();
                    objEstudio.setNome(Console.readString("Informe o estúdio que o técnico pode atender: "));
                    objEstudio = EstudioPersistencia.procurarPorNome(objEstudio);
                    boolean estudioExistente = false;
                    for (Estudio item : objTecnico.getEstudios()) {
                        if (objEstudio.getNome().equals(item.getNome())) {
                            estudioExistente = true;
                            break;
                        }
                    }
                    if (!estudioExistente) {
                        objTecnico.getEstudios().add(objEstudio);
                        objEstudio.getTecnicos().add(objTecnico);
                        System.out.println("Estúdio adicionado.");
                    } else {
                        System.out.println("Estúdio já cadastrado.");
                    }
                    resp = Console.readString("Deseja adicionar outro estúdio? \n[S] [N]\n ");
                } while (resp.equals("S"));
                if (TecnicoPersistencia.alterar(objTecnico) == true) {
                    System.out.println("A alteração foi realizada...");
                } else {
                    System.out.println("A alteração não pôde ser realizada no momento...");
                }
            }
        } else {
            System.out.println("\n\nTecnico não encontrado...");
        }
    }

    private static void listarTecnico() {
        System.out.println("-----LISTAR TÉCNICOS-----");
        Tecnico objTecnico = new Tecnico();
        objTecnico.setCpf(Console.readString("Informe o CPF do cliente a ser listado: "));
        objTecnico = TecnicoPersistencia.procurarPorCPF(objTecnico);
        if (objTecnico != null) {
            System.out.println("--------------------");
            System.out.println("ID: " + objTecnico.getId());
            System.out.println("CPF: " + objTecnico.getCpf());
            System.out.println("Nome: " + objTecnico.getNome());
            for (Estudio item : objTecnico.getEstudios()) {
                System.out.println("Estudio: " + item.getNome());
            }
            System.out.println("--------------------");
        }
    }

    public static void listartodosTecnicos() {
        System.out.println("-----LISTAR TÉCNICOS-----");
        Tecnico objTecnico = new Tecnico();
        objTecnico.setNome(Console.readString("Enter para listar todos os técnicos."));
        if (TecnicoPersistencia.getTecnicos(objTecnico) != null) {
            for (Tecnico itemTecnico : TecnicoPersistencia.getTecnicos(objTecnico)) {
                System.out.println("--------------------");
                System.out.println("ID: " + itemTecnico.getId());
                System.out.println("Nome: " + itemTecnico.getNome());
                for (Estudio item : itemTecnico.getEstudios()) {
                    System.out.println("Estudio: " + item.getNome());
                }
                System.out.println("--------------------");
            }
        } else {
            System.out.println("Não há técnicos cadastrados.");
        }
    }

    private static void listarTecnicoEstudio() {
        System.out.println("-----LISTAR TÉCNICOS-----");
        Tecnico objTecnico = new Tecnico();
        Estudio objEstudio = new Estudio();
        objEstudio.setNome(Console.readString("Informe o nome do estúdio que deseja ver os técnicos disponíveis: "));
        objTecnico = TecnicoPersistencia.procurarEstudio(objTecnico);
        if (objTecnico != null) {
            System.out.println("--------------------");
            System.out.println("ID: " + objTecnico.getId());
            System.out.println("CPF: " + objTecnico.getCpf());
            System.out.println("Nome: " + objTecnico.getNome());
            for (Estudio item : objTecnico.getEstudios()) {
                System.out.println("Estudio: " + item.getNome());
            }
            System.out.println("--------------------");
        }
    }

    private static void excluirTecnico() {
        String opc;
        System.out.println("-----EXCLUIR TÉCNICO-----");
        Tecnico objTecnico = new Tecnico();
        objTecnico.setCpf(Console.readString("Informe o cpf do técnico a ser excluído: "));
        objTecnico = TecnicoPersistencia.procurarPorCPF(objTecnico);
        if (objTecnico != null) {
            System.out.println("--------------------");
            System.out.println("ID: " + objTecnico.getId());
            System.out.println("CPF: " + objTecnico.getCpf());
            System.out.println("Nome: " + objTecnico.getNome());
            for (Estudio item : objTecnico.getEstudios()) {
                System.out.println("Estudio: " + item.getNome());
            }
            System.out.println("--------------------");
            opc = Console.readString("Deseja excluir esse técnico?\n[S] [N]\n");
            if (opc.equals("S")) {
                if (TecnicoPersistencia.excluir(objTecnico) == true) {
                    System.out.println("Técnico excluído.");
                } else {
                    System.out.println("Não foi possível excluir o técnico");
                }
            }
        } else {
            System.out.println("Técnico não encontrado.");
        }

    }
}
