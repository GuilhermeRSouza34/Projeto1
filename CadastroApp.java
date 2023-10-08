import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class CadastroApp {
    private static List<Usuario> usuarios = new ArrayList<>();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            criarGUI();
        });
    }

    private static void criarGUI() {
        JFrame frame = new JFrame("Cadastro de Usuários");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(new GridLayout(2, 2));

        JButton cadastrarButton = new JButton("Cadastrar");
        JButton consultarButton = new JButton("Consultar");
        JButton sairButton = new JButton("Sair");

        frame.add(cadastrarButton);
        frame.add(consultarButton);
        frame.add(sairButton);

        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirTelaCadastro();
            }
        });

        consultarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                consultarUsuarios();
            }
        });

        sairButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        frame.setVisible(true);
    }

    private static void abrirTelaCadastro() {
        JFrame frame = new JFrame("Cadastro de Usuário");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(8, 2));

        JLabel nomeLabel = new JLabel("Nome:");
        JTextField nomeField = new JTextField();
        JLabel cpfLabel = new JLabel("CPF:");
        JTextField cpfField = new JTextField();
        JLabel telefoneLabel = new JLabel("Telefone:");
        JTextField telefoneField = new JTextField();
        JLabel cepLabel = new JLabel("CEP:");
        JTextField cepField = new JTextField();
        JLabel sexoLabel = new JLabel("Sexo:");

        JRadioButton masculinoRadioButton = new JRadioButton("Masculino");
        JRadioButton femininoRadioButton = new JRadioButton("Feminino");

        ButtonGroup sexoGroup = new ButtonGroup();
        sexoGroup.add(masculinoRadioButton);
        sexoGroup.add(femininoRadioButton);

        JLabel dataNascimentoLabel = new JLabel("Data de Nascimento:");
        JTextField dataNascimentoField = new JTextField();

        JButton cadastrarButton = new JButton("Cadastrar");

        frame.add(nomeLabel);
        frame.add(nomeField);
        frame.add(cpfLabel);
        frame.add(cpfField);
        frame.add(telefoneLabel);
        frame.add(telefoneField);
        frame.add(cepLabel);
        frame.add(cepField);
        frame.add(sexoLabel);

        JPanel sexoPanel = new JPanel();
        sexoPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        sexoPanel.add(masculinoRadioButton);
        sexoPanel.add(femininoRadioButton);
        frame.add(sexoPanel);

        frame.add(dataNascimentoLabel);
        frame.add(dataNascimentoField);
        frame.add(new JLabel());
        frame.add(cadastrarButton);

        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = nomeField.getText();
                String cpf = cpfField.getText();
                String telefone = telefoneField.getText();
                String cep = cepField.getText();
                String dataNascimento = dataNascimentoField.getText();
                String sexo = "";

                if (masculinoRadioButton.isSelected()) {
                    sexo = masculinoRadioButton.getText();
                } else if (femininoRadioButton.isSelected()) {
                    sexo = femininoRadioButton.getText();
                }

                if (nome.isEmpty() || cpf.isEmpty() || telefone.isEmpty() || cep.isEmpty() || sexo.isEmpty()
                        || dataNascimento.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Preencha todos os campos!");
                } else {
                    Usuario usuario = new Usuario(nome, cpf, telefone, cep, sexo, dataNascimento);
                    usuarios.add(usuario);

                    JOptionPane.showMessageDialog(frame, "Usuário cadastrado com sucesso!");

                    frame.dispose();
                }
            }
        });

        frame.setVisible(true);
    }

    private static void consultarUsuarios() {
        StringBuilder mensagem = new StringBuilder("Usuários Cadastrados:\n");

        for (Usuario usuario : usuarios) {
            mensagem.append("Nome: ").append(usuario.getNome()).append("\n");
            mensagem.append("CPF: ").append(formatarCPF(usuario.getCpf())).append("\n");
            mensagem.append("Telefone: ").append(formatarTelefone(usuario.getTelefone())).append("\n");
            mensagem.append("CEP: ").append(formatarCEP(usuario.getCep())).append("\n");
            mensagem.append("Sexo: ").append(usuario.getSexo()).append("\n");
            mensagem.append("Data de Nascimento: ").append(formatarDataNascimento(usuario.getDataNascimento()))
                    .append("\n");
            mensagem.append("\n");
        }

        JOptionPane.showMessageDialog(null, mensagem.toString());
    }

    private static String formatarCPF(String cpf) {
        if (cpf == null || cpf.length() != 11) {
            return cpf;
        }
        return String.format("%s.%s.%s-%s", cpf.substring(0, 3), cpf.substring(3, 6), cpf.substring(6, 9),
                cpf.substring(9, 11));
    }

    private static String formatarTelefone(String telefone) {
        if (telefone == null || telefone.length() != 11) {
            return telefone;
        }
        return String.format("(%s) %s%s%s%s%s%s%s%s%s",
                telefone.substring(0, 2),
                telefone.charAt(2), telefone.charAt(3), telefone.charAt(4),
                telefone.charAt(5), telefone.charAt(6), telefone.charAt(7),
                telefone.charAt(8), telefone.charAt(9), telefone.charAt(10));
    }

    private static String formatarCEP(String cep) {
        if (cep == null || cep.length() != 8) {
            return cep;
        }
        return String.format("%s%s%s%s%s-%s%s%s",
                cep.charAt(0), cep.charAt(1), cep.charAt(2), cep.charAt(3), cep.charAt(4),
                cep.charAt(5), cep.charAt(6), cep.charAt(7));
    }

    private static String formatarDataNascimento(String dataNascimento) {
        if (dataNascimento == null || dataNascimento.length() != 8) {
            return dataNascimento;
        }
        return String.format("%s%s/%s%s/%s%s%s%s",
                dataNascimento.charAt(0), dataNascimento.charAt(1),
                dataNascimento.charAt(2), dataNascimento.charAt(3),
                dataNascimento.charAt(4), dataNascimento.charAt(5),
                dataNascimento.charAt(6), dataNascimento.charAt(7));
    }
}

class Usuario {
    private String nome;
    private String cpf;
    private String telefone;
    private String cep;
    private String sexo;
    private String dataNascimento;

    public Usuario(String nome, String cpf, String telefone, String cep, String sexo, String dataNascimento) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.cep = cep;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getCep() {
        return cep;
    }

    public String getSexo() {
        return sexo;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }
}