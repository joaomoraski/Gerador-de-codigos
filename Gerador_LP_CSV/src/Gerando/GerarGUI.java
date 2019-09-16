package Gerando;

import java.util.ArrayList;
import java.util.List;
import tools.ManipulaArquivo;

public class GerarGUI {

    String nomeDaClasse = "Atleta";
    String nomeDaClasseminusculo = "atleta";
    List<String> atributo = new ArrayList<>();
    List<String> codigo = new ArrayList<>();
    List<String> codigocontrole = new ArrayList<>();

    public String primeiraLetramaiscula(String s) {
        return String.valueOf(s.charAt(0)).toUpperCase() + s.substring(1, s.length());
    }

    public GerarGUI() {
        atributo.add("int;id");
        atributo.add("String;nome");
        atributo.add("double;altura");
        atributo.add("String;esporte");
        atributo.add("double;peso");
        //atributo.add("boolean;aposentado");
        //atributo.add("Date;data");

        //primeira letra maiscula
        String pk = "Id"; //indique qual atributo é chave primaria 
        String get = "get";
        String set = "set";

        //tudo as importação
        codigo.add("package Main;\n"
                + "import java.awt.BorderLayout;\n"
                + "import java.awt.CardLayout;\n"
                + "import java.awt.Container;\n"
                + "import java.awt.GridLayout;\n"
                + "import java.awt.event.ActionEvent;\n"
                + "import java.awt.event.ActionListener;\n"
                + "import java.awt.event.WindowAdapter;\n"
                + "import java.awt.event.WindowEvent;\n"
                + "import java.util.ArrayList;\n"
                + "import java.util.List;\n"
                + "import java.sql.Date;\n"
                + "import javax.swing.JButton;\n"
                + "import javax.swing.JCheckBox;\n"
                + "import javax.swing.JFrame;\n"
                + "import javax.swing.JLabel;\n"
                + "import javax.swing.JOptionPane;\n"
                + "import javax.swing.JPanel;\n"
                + "import javax.swing.JScrollPane;\n"
                + "import javax.swing.JTable;\n"
                + "import javax.swing.JTextArea;\n"
                + "import javax.swing.JTextField;\n"
                + "import javax.swing.JToolBar;\n"
                + "import javax.swing.table.DefaultTableModel;\n"
                + "import tools.*;\n");

        //famoso credito
        codigo.add("/**"
                + "*"
                + "* @author GERADOR DO MORASKINHOOOOOOOOOOOOOOO\n"
                + "*/\n");
        //Gerando a classe e o extends
        codigo.add("public class " + nomeDaClasse + "GUI extends JFrame{\n"
                + "private Container cp;\n");

        //instanciando dates
        //+atributo.get(0).split(";")[0]
        //codigo.add("private JLabel");
        for (int i = 0; i < atributo.size(); i++) {
            String lbtf[];
            String aux[] = atributo.get(i).split(";");
            if (aux[0].equals("boolean")) {
                codigo.add("private JCheckBox cb" + primeiraLetramaiscula(aux[1]) + " = new  JCheckBox(\""
                        + primeiraLetramaiscula(aux[1]) + "\", false);\n");
            } else if (aux[0].equals("DateTextField") || aux[0].equals("Date")) {
                codigo.add("private DateTextField tf" + aux[1] + " = new DateTextField();\n");
                codigo.add("private JLabel lb" + aux[1] + " = new JLabel(\"" + aux[1] + "\");\n");
                //codigo.add("SimpleDateFormat sdf = new SimpleDateFormat(\"dd/MM/yyyy\");\n");
            } else if (!aux[0].equals("boolean") || !aux[0].equals("DateTextField") || !aux[0].equals("Date")) {
                codigo.add("private JLabel lb" + primeiraLetramaiscula(aux[1]) + " = new JLabel(\"" + aux[1] + "\");\n");
                codigo.add("private JTextField tf" + primeiraLetramaiscula(aux[1]) + " = new JTextField(20);\n");
            }

        }
        //botoes
        codigo.add("private JButton btAdicionar = new JButton(\"Adicionar\");\n"
                + "    private JButton btListar = new JButton(\"Listar\");\n"
                + "    private JButton btBuscar = new JButton(\"Buscar\");\n"
                + "    private JButton btAlterar = new JButton(\"Alterar\");\n"
                + "    private JButton btExcluir = new JButton(\"Excluir\");\n"
                + "    private JButton btSalvar = new JButton(\"Salvar\");\n"
                + "    private JButton btCancelar = new JButton(\"Cancelar\");\n"
                + "    private JButton btCarregarDados = new JButton(\"Carregar\");\n"
                + "    private JButton btGravar = new JButton(\"Gravar\");\n"
                + "    private JToolBar toolBar = new JToolBar();\n"
                + "    private JPanel painelNorte = new JPanel();\n"
                + "    private JPanel painelCentro = new JPanel();\n"
                + "    private JPanel painelSul = new JPanel();\n"
                + "    private JTextArea texto = new JTextArea();\n"
                + "    private JScrollPane scrollTexto = new JScrollPane();\n"
                + "    private JScrollPane scrollTabela = new JScrollPane();\n"
                + "\n"
                + "    private String acao = \"\";\n\n"
                + "    private String chavePrimaria = \"\";\n\n"
                + ""
        );

        //instanciando controle e entidade
        codigo.add("private " + nomeDaClasse + "Controle" + " " + nomeDaClasseminusculo + "Controle"
                + " = new " + nomeDaClasse + "Controle();\n");
        codigo.add("private " + nomeDaClasse + " " + nomeDaClasseminusculo + "Entidade"
                + " = new " + nomeDaClasse + "();\n\n");

        int c = 0;
        String a = " ";
        for (int i = 0; i < atributo.size(); i++) {
            String aux[] = atributo.get(i).split(";");
            String s = "\"" + primeiraLetramaiscula(aux[1]) + "\",";

            a = a + s;
            c = c + 1;
        }
        a = a.substring(0, a.length() - 1);
        codigo.add("String[] colunas = new String[]{" + a + "};");

        codigo.add("String[][] dados = new String[0][" + c + "];");

        codigo.add("\nDefaultTableModel model = new DefaultTableModel(dados, colunas);\n"
                + "\nJTable tabela = new JTable(model);\n"
                + "private JPanel painel1 = new JPanel(new GridLayout(1,1));\n"
                + "private JPanel painel2 = new JPanel(new GridLayout(1,1));\n"
                + "\nprivate CardLayout cardLayout;\n \n");

        codigo.add("\npublic " + nomeDaClasse + "GUI() {\n"
                + "\nString caminhoENomeDoArquivo = "
                + "\"C:/Users/jvmor/Documents/NetBeansProjects/Cobaia/src/Dados" + nomeDaClasse + ".csv\";\n"
                + "\nsetDefaultCloseOperation(DISPOSE_ON_CLOSE);\n"
                + "setSize(600,400);\n"
                + "setTitle(\"CRUD " + nomeDaClasse + " - Gerado\");\n"
                + "setLocationRelativeTo(null);\n"
                + "setVisible(true);\n"
                + "\ncp = getContentPane();\n"
                + "cp.setLayout(new BorderLayout());\n"
                + "cp.add(painelNorte, BorderLayout.NORTH);\n"
                + "cp.add(painelCentro, BorderLayout.CENTER);\n"
                + "cp.add(painelSul, BorderLayout.SOUTH);\n"
                + "\ncardLayout = new CardLayout();\n"
                + "painelSul.setLayout(cardLayout);\n"
                + "\n"
                + "\npainel1.add(scrollTexto);\n"
                + "painel2.add(scrollTabela);\n"
                + "texto.setText(\"\\n\\n\\n\\n\\n\\n\");\n"
                + "scrollTexto.setViewportView(texto);\n"
                + "painelSul.add(painel1, \"Avisos\");\n"
                + "painelSul.add(painel2, \"Listagem\");\n"
                + "\ntabela.setEnabled(false);\n"
                + "\npainelNorte.setLayout(new GridLayout(1,1));\n"
                + "painelNorte.add(toolBar);\n \n"
                + "painelCentro.setLayout(new GridLayout(" + (atributo.size() - 1) + ",2));\n \n");

        for (int i = 0; i < atributo.size(); i++) {
            String aux[] = atributo.get(i).split(";");
            if (!aux[0].equals("boolean") || !aux[0].equals("DateTextField") || !aux[0].equals("Date")) {
                if (aux[1].equals(pk)) {
                    codigo.add("//EASTER EGG UHULL");
                } else {
                    codigo.add("painelCentro.add(lb" + primeiraLetramaiscula(aux[1]) + ");\n");
                    codigo.add("painelCentro.add(tf" + primeiraLetramaiscula(aux[1]) + ");\n");
                }

            }
            if (aux[0].equals("boolean")) {
                codigo.add("painelCentro.add(cb" + primeiraLetramaiscula(aux[1]) + ");\n");
            }
            if (aux[0].equals("DateTextField") || aux[0].equals("Date")) {
                codigo.add("painelCentro.add(lb" + aux[1] + ");\n");
                codigo.add("painelCentro.add(tf" + aux[1] + ");\n");
                //codigo.add("SimpleDateFormat sdf = new SimpleDateFormat(\"dd/MM/yyyy\");\n");
            }

        }

        codigo.add("\ntoolBar.add(lb" + pk + ");\n");
        codigo.add("toolBar.add(tf" + pk + ");\n");
        codigo.add("toolBar.add(btAdicionar);\n"
                + "toolBar.add(btBuscar);\n"
                + "toolBar.add(btListar);\n"
                + "toolBar.add(btCarregarDados);\n"
                + "toolBar.add(btGravar);\n"
                + "toolBar.add(btAlterar);\n"
                + "toolBar.add(btExcluir);\n"
                + "toolBar.add(btSalvar);\n"
                + "toolBar.add(btCancelar);\n \n"
        );
        codigo.add("btAdicionar.setVisible(false);\n"
                + "btAlterar.setVisible(false);\n"
                + "btExcluir.setVisible(false);\n"
                + "btSalvar.setVisible(false);\n"
                + "btCancelar.setVisible(false);\n \n"
        );

        for (int i = 0; i < atributo.size(); i++) {
            String aux[] = atributo.get(i).split(";");
            codigo.add("tf" + primeiraLetramaiscula(aux[1]) + ".setEditable(false);\n");
        }
        codigo.add("texto.setEditable(false);\n\n");

        codigo.add("btCarregarDados.addActionListener(new ActionListener(){\n"
                + "@Override\n"
                + "public void actionPerformed(ActionEvent e){\n"
                + "ManipulaArquivo manipulaArquivo = new ManipulaArquivo();\n"
                + "if (manipulaArquivo.existeOArquivo(caminhoENomeDoArquivo)){\n"
                + "String aux[];\n"
                + nomeDaClasse + " ce;\n"
                + "List<String> listaStringCsv = manipulaArquivo.abrirArquivo(caminhoENomeDoArquivo);\n"
                + "for (String linha : listaStringCsv) {\n"
                + "aux = linha.split(\";\");\n"
                + "ce = new " + nomeDaClasse + " (");
        int auc = -1;
        String as = " ";
        for (int i = 0; i < atributo.size(); i++) {
            String aux[] = atributo.get(i).split(";");
            auc++;
            if (aux[0].equals("String")) {
                String s = "String.valueOf(aux[" + auc + "]),";
                as = as + s;
            } else if (aux[0].equals("int") || (aux[0].equals("Int"))) {
                String s = "Integer.valueOf(aux[" + auc + "]),";
                as = as + s;
            } else if (aux[0].equals("Boolean") || (aux[0].equals("boolean"))) {
                String s = "Boolean.valueOf(\"Sim\") ? true: false, ";
                as = as + s;
            } else if (aux[0].equals("Double") || (aux[0].equals("double"))) {
                String s = "Double.valueOf(aux[" + auc + "]), ";
                as = as + s;
            } else if (aux[0].equals("Date") || (aux[0].equals("DateTextField") || (aux[0].equals("date")))) {
                String s = "Date.valueOf(aux[" + auc + "]), ";
                as = as + s;
            }
        }
        if (as.endsWith(", ")) {
            as = as.substring(0, as.length() - 2);
        }
        codigo.add(as + ");\n"
        + nomeDaClasseminusculo + "Controle.adicionar(ce);\n}\n \n"
        + "cardLayout.show(painelSul, \"Listagem\");}\n}\n});");


//finaliza codigo
        codigo.add("}");
        codigo.add("}");
        for (int i = 0; i < codigo.size(); i++) {
            System.out.println(codigo.get(i));
        }

        ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
        manipulaArquivo.salvarArquivo("C:/Users/jvmor/Documents/NetBeansProjects/Cobaia/src/Main/AtletaGUI.java", codigo);

    }

}
