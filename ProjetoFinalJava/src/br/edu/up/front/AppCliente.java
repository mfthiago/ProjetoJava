package br.edu.up.front;

import br.edu.up.entidades.Agenda;
import br.edu.up.entidades.Cliente;
import br.edu.up.entidades.Estudio;
import br.edu.up.entidades.Data;
import br.edu.up.persistencia.ClientePersistencia;
import br.edu.up.negocio.ClienteNegocio;

public class AppCliente {
    public AppCliente() {
        int opc;
        do {
            System.out.println("\n\n-----CLIENTES-----");
            System.out.println(
                    "\n[1] - Novo cliente\n[2] - Listar cliente\n[3] - Listar agendamentos do cliente\n[4] - Alterar cliente\n[5] - Excluir cliente\n[6] - Sair");
            opc = Console.readInt("Informe a opção: ");
            switch (opc) {
                case 1:
                    novoCliente();
                    break;
                case 2:
                    listarCliente();
                    break;
                case 3:
                    listarAgenda();
                case 4:
                    alterarClientes();
                case 5:
                    excluirCliente();
                    break;
            }

        } while (opc != 5);
    }

    private static void novoCliente() {
        System.out.println("-----NOVO CLIENTE-----");
        Cliente objCliente = new Cliente();
        objCliente.setCpf(Console.readString("Informe o CPF: "));
        if (ClienteNegocio.isCPF(objCliente.getCpf()) == true) {
            if (ClientePersistencia.procurarPorCPF(objCliente) == null) {
                objCliente.setNome(Console.readString("Informe o nome do cliente: "));
                objCliente.setTelefone(Console.readString("Informe o número de telefone do cliente: "));
                if (ClientePersistencia.validarTelefone(objCliente.getTelefone()) == true) {
                    if (ClientePersistencia.incluir(objCliente) == true) {
                        System.out.println("Cliente cadastrado.");
                    } else {
                        System.out.println("Não foi possível cadastrar o cliente.");
                    }
                } else {
                    System.out.println("Telefone inválido.");
                }
            } else {
                System.out.println("Cliente já cadastrado.");
            }
        } else {
            System.out.println("CPF inválido.");
        }

    }

    private static void alterarClientes() {
        System.out.println("\n\n-----ALTERAÇÃO DE CLIENTES-----");
        Cliente objCliente = new Cliente();
        objCliente.setCpf(Console.readString("Informe o CPF a ser consultado: "));
        objCliente = ClientePersistencia.procurarPorCPF(objCliente);
        if (objCliente != null) {
            System.out.println("\n\n-------------------------");
            System.out.println("ID: " + objCliente.getId());
            System.out.println("Nome: " + objCliente.getNome());
            System.out.println("CPF: " + objCliente.getCpf());
            System.out.println("-------------------------");
            String resp = Console.readString("Quer alterar os dados deste cliente? ");
            if (resp.equals("S")) {
                objCliente.setNome(Console.readString("Informe o novo nome para o cliente: "));
                if (ClientePersistencia.alterar(objCliente) == true) {
                    System.out.println("A alteração foi realizada...");
                } else {
                    System.out.println("A alteração não pôde ser realizada no momento...");
                }
            }
        } else {
            System.out.println("\n\nCliente não encontrado...");
        }
    }

    private static void listarCliente() {
        System.out.println("-----LISTAR CLIENTES-----");
        Cliente objCliente = new Cliente();
        objCliente.setCpf(Console.readString("Informe o CPF do cliente a ser listado: "));
        objCliente = ClientePersistencia.procurarPorCPF(objCliente);
        if (objCliente != null) {
            System.out.println("--------------------");
            System.out.println("ID: " + objCliente.getId());
            System.out.println("CPF: " + objCliente.getCpf());
            System.out.println("Nome: " + objCliente.getNome());
            System.out.println("Telefone: " + objCliente.getTelefone());
            System.out.println("--------------------");
        }
    }

    private static void listarAgenda() {
        Cliente objCliente = new Cliente();
        objCliente.setCpf(Console.readString("Informe o CPF do cliente: "));
        objCliente = ClientePersistencia.procurarPorCPF(objCliente);
        if (objCliente != null) {
            if (objCliente.getAgendamentos().isEmpty()) {
                System.out.println("Não há agendamentos para esse cliente.");
            } else {
                for (Agenda item : objCliente.getAgendamentos()) {
                    System.out.println("-----AGENDAMENTOS-----");
                    System.out.println("Estúdio: " + item.getEstudios());
                    System.out.println("Técnico: " + item.getTecnicos());
                    System.out.println("Data: " + item.getDataAgenda());
                    System.out.println("----------------------");
                }
            }
        }
    }

    private static void excluirCliente() {
        String opc;
        System.out.println("-----EXCLUIR CLIENTE-----");
        Cliente objCliente = new Cliente();
        objCliente.setCpf(Console.readString("Informe o CPF do cliente a ser excluído: "));
        objCliente = ClientePersistencia.procurarPorCPF(objCliente);
        if (objCliente != null) {
            System.out.println("--------------------");
            System.out.println("ID: " + objCliente.getId());
            System.out.println("CPF: " + objCliente.getCpf());
            System.out.println("Nome: " + objCliente.getNome());
            System.out.println("Telefone: " + objCliente.getTelefone());
            System.out.println("--------------------");
            opc = Console.readString("Deseja excluir esse cliente?\n[S] [N]\n");
            if (opc.equals("S")) {
                if (ClientePersistencia.excluir(objCliente) == true) {
                    System.out.println("Cliente excluído.");
                } else {
                    System.out.println("Não foi possível excluir o cliente");
                }
            }
        } else {
            System.out.println("Cliente não encontrado.");
        }

    }

}
