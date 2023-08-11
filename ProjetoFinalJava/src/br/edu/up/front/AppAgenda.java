package br.edu.up.front;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.edu.up.entidades.Data;
import br.edu.up.entidades.Agenda;
import br.edu.up.entidades.Tecnico;
import br.edu.up.entidades.Cliente;
import br.edu.up.entidades.Estudio;
import br.edu.up.persistencia.AgendaPersistencia;
import br.edu.up.persistencia.TecnicoPersistencia;
import br.edu.up.persistencia.ClientePersistencia;
import br.edu.up.persistencia.DataPersistencia;
import br.edu.up.persistencia.EstudioPersistencia;

public class AppAgenda {
    public AppAgenda() {
        int opc;
        do {
            System.out.println("\n\n-----AGENDA-----");
            System.out.println(
                    "[1] - Marcar horário\n[2] - Listar horários\n[3] - Desmarcar horário\n[4] -  Sair");
            opc = Console.readInt("Informe a opção: ");
            switch (opc) {
                case 1:
                    marcarHorario();
                    break;
                case 2:
                    listarHorario();
                    break;
                case 3:
                    excluirHorario();
                    break;

            }
        } while (opc != 4);
    }

    private static void marcarHorario() {
        String dataAgenda;
        String horaAgenda;
        String resp;
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date dataDate = null;
        Date horarioDate = null;
        boolean dataInvalida = true;
        Agenda objAgenda = new Agenda();
        Tecnico objTecnico = new Tecnico();
        Cliente objCliente = new Cliente();
        Estudio objEstudio = new Estudio();
        Data objData = new Data();

        objCliente.setCpf(Console.readString("Informe o CPF do cliente: "));
        objCliente = ClientePersistencia.procurarPorCPF(objCliente);
        if (objCliente != null) {
            objAgenda.setCliente(objCliente);
            AppEstudio.listartodosEstudios();
            objEstudio.setNome(Console.readString("Informe o estúdio que deseja marcar horário: "));
            objEstudio = EstudioPersistencia.procurarPorNome(objEstudio);
            if (objEstudio != null) {
                objAgenda.setEstudio(objEstudio);
                objAgenda.getEstudios().add(objEstudio);
                for (Tecnico itemTecnico : objEstudio.getTecnicos()) {
                    System.out.println("--------------------");
                    System.out.println("ID: " + itemTecnico.getId());
                    System.out.println("Técnico: " + itemTecnico.getNome());
                    System.out.println("--------------------");
                }
                objTecnico.setId(Console.readInt("Informe o ID do técnico que deseja marcar horário: "));
                objTecnico = TecnicoPersistencia.procurarPorID(objTecnico);
                if (objTecnico != null) {
                    objAgenda.getTecnicos().add(objTecnico);
                    objAgenda.setTecnico(objTecnico);
                    do {
                        dataAgenda = Console.readString("\n\nInforme a data da agenda: \nExemplo: 01/01/2021 10:00\n");
                        try {
                            dataDate = (Date) formato.parse(dataAgenda);
                            dataInvalida = false;
                        } catch (ParseException e) {
                            System.out.println("Data inválida. Informe novamente.");
                        }
                    } while (dataInvalida == true);
                    objAgenda.setDataAgenda(dataAgenda);
                    System.out.println("Data: " + objAgenda.getDataAgenda());
                    if (AgendaPersistencia.incluir(objAgenda) == true) {
                        objCliente.getAgendamentos().add(objAgenda);
                        objData.getAgendamentos().add(objAgenda);
                        System.out.println("Horário marcado com sucesso!");
                    } else {
                        System.out.println("Erro ao marcar horário!");
                    }
                } else {
                    System.out.println("Técnico não encontrado");
                }
            } else {
                System.out.println("Estúdio não encontrado");
            }
        } else {
            System.out.println("Cliente não encontrado!");
            return;
        }

    }

    private static void listarHorario() {
        Agenda objAgenda = new Agenda();
        Data objData = new Data();
        System.out.println("-----LISTAR HORÁRIOS-----");
        objAgenda.setDataAgenda(Console.readString("Informe a data que deseja listar os agendamentos: "));
        if (AgendaPersistencia.getAgendas(objAgenda) != null) {
            objData.setDataAgenda(objAgenda.getDataAgenda());
            for (Agenda itemAgenda : AgendaPersistencia.getAgendas(objAgenda)) {
                System.out.println("--------------------");
                System.out.println("ID: " + itemAgenda.getId());
                System.out.println("Cliente: " + itemAgenda.getCliente().getNome());
                System.out.println("Estúdio: " + itemAgenda.getEstudios().get(0).getNome());
                System.out.println("Técnico: " + itemAgenda.getTecnicos().get(0).getNome());
                System.out.println("Data: " + itemAgenda.getDataAgenda());
                System.out.println("--------------------");
            }
        } else {
            System.out.println("Nenhum agendamento nesse dia.");
        }
    }

    private static void excluirHorario() {
        Agenda objAgenda = new Agenda();
        Cliente objCliente = new Cliente();
        System.out.println("-----EXCLUIR HORÁRIO-----");
        objCliente.setCpf(Console.readString("Informe o cliente que deseja listar os agendamentos: "));
        objCliente = ClientePersistencia.procurarPorCPF(objCliente);
        if (objCliente != null) {
            if (objCliente.getAgendamentos().isEmpty()) {
                System.out.println("Não há agendamentos para esse cliente.");
            } else {
                for (Agenda item : objCliente.getAgendamentos()) {
                    System.out.println("-----AGENDAMENTOS-----");
                    System.out.println("ID: " + item.getId());
                    System.out.println("Estúdio: " + item.getEstudios().get(0).getNome());
                    System.out.println("Técnico: " + item.getTecnicos().get(0).getNome());
                    System.out.println("Data: " + item.getDataAgenda());
                    System.out.println("----------------------");
                }
            }
        }
        objAgenda.setId(Console.readInt("Informe o ID do agendamento que deseja excluir: "));
        if (AgendaPersistencia.excluir(objAgenda) == true) {
            System.out.println("Agendamento excluído com sucesso!");
        } else {
            System.out.println("Erro ao excluir agendamento!");
        }
    }
}