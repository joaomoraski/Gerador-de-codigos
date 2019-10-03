package Gerando;

import java.util.ArrayList;
import java.util.List;
import tools.ManipulaArquivo;

public class GerarGUI {

    String nomeDaClasse = "Produto";
    String nomeDaClasseminusculo = "produto";
    List<String> atributo = new ArrayList<>();
    List<String> codigo = new ArrayList<>();
    List<String> codigocontrole = new ArrayList<>();

    public String primeiraLetramaiscula(String s) {
        return String.valueOf(s.charAt(0)).toUpperCase() + s.substring(1, s.length());
    }

    public GerarGUI() {
        atributo.add("long;idProduto");
        atributo.add("double;precoUnitario");
        atributo.add("Date;dataDeCadastro");

        //atributo.add("boolean;aposentado");
        //atributo.add("Date;data");
        //primeira letra maiscula
        String pk = "idProduto"; //indique qual atributo é chave primaria 
        String pkt = "long"; //indique qual é o tipo da váriavel pk 
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
                + "import java.util.logging.Level;\n" 
                + "import java.util.logging.Logger;\n" 
                + "import java.text.ParseException;\n"
                + "import java.sql.Date;\n"
                + "import javax.swing.JButton;\n"
                + "import java.text.SimpleDateFormat;\n"
                + "import javax.swing.JCheckBox;\n"
                + "import javax.swing.JFrame;\n"
                + "import javax.swing.JLabel;\n"
                + "import javax.swing.JOptionPane;\n"
                + "import javax.swing.JPanel;\n"
                + "import tools.DateTextField;\n"
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
                codigo.add("private DateTextField tf" + primeiraLetramaiscula(aux[1]) + " = new DateTextField();\n");
                codigo.add("private JLabel lb" + primeiraLetramaiscula(aux[1]) + " = new JLabel(\"" + aux[1] + "\");\n");
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

        //colunas e dados do negocio la
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

        //gerando construtor e passando caminho pra onde vai os dados
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

        //adicionando no layout
        for (int i = 1; i < atributo.size(); i++) {
            String aux[] = atributo.get(i).split(";");
            if (aux[1].equals(pk)) {
                codigo.add("");
            } else if (aux[0].equals("boolean")) {
                codigo.add("painelCentro.add(cb" + primeiraLetramaiscula(aux[1]) + ");\n");
            } else if (aux[0].equals("Date")) {
                codigo.add("painelCentro.add(lb" + primeiraLetramaiscula(aux[1]) + ");\n");
                codigo.add("painelCentro.add(tf" + primeiraLetramaiscula(aux[1]) + ");\n");
            } else {
                codigo.add("painelCentro.add(lb" + primeiraLetramaiscula(aux[1]) + ");\n");
                codigo.add("painelCentro.add(tf" + primeiraLetramaiscula(aux[1]) + ");\n");
            }

        }

        codigo.add(
                "\ntoolBar.add(lb" + primeiraLetramaiscula(pk) + ");\n");
        codigo.add(
                "toolBar.add(tf" + primeiraLetramaiscula(pk) + ");\n");
        codigo.add(
                "toolBar.add(btAdicionar);\n"
                + "toolBar.add(btBuscar);\n"
                + "toolBar.add(btListar);\n"
                + "toolBar.add(btCarregarDados);\n"
                + "toolBar.add(btGravar);\n"
                + "toolBar.add(btAlterar);\n"
                + "toolBar.add(btExcluir);\n"
                + "toolBar.add(btSalvar);\n"
                + "toolBar.add(btCancelar);\n \n"
        );
        codigo.add(
                "btAdicionar.setVisible(false);\n"
                + "btAlterar.setVisible(false);\n"
                + "btExcluir.setVisible(false);\n"
                + "btSalvar.setVisible(false);\n"
                + "btCancelar.setVisible(false);\n \n"
        );
        for (int i = 1;
                i < atributo.size();
                i++) {
            String aux[] = atributo.get(i).split(";");
            if (aux[0].equals("String") || aux[0].equals("string") || aux[0].equals("int") || aux[0].equals("Int") || aux[0].equals("Double") || aux[0].equals("double") || aux[0].equals("Date") || aux[0].equals("date")) {
                codigo.add("tf" + primeiraLetramaiscula(aux[1]) + ".setEditable(false);\n");
            } else {
                codigo.add("cb" + primeiraLetramaiscula(aux[1]) + ".setSelected(false);\n");
                codigo.add("cb" + primeiraLetramaiscula(aux[1]) + ".setEnabled(false);\n");

            }
        }

        /*
        for (int i = 1; i < atributo.size(); i++) {
            String aux[] = atributo.get(i).split(";");
            codigo.add("tf" + primeiraLetramaiscula(aux[1]) + ".setEditable(false);\n");
        }*/
        codigo.add(
                "texto.setEditable(false);\n\n");

        //primeiro listener
        codigo.add(
                "btCarregarDados.addActionListener(new ActionListener(){\n"
                + "@Override\n"
                + "public void actionPerformed(ActionEvent e){\n"
                + "ManipulaArquivo manipulaArquivo = new ManipulaArquivo();\n"
                + "if (manipulaArquivo.existeOArquivo(caminhoENomeDoArquivo)){\n"
                + "String aux[];\n"
                + nomeDaClasse + " ce;\n"
                + "List<String> listaStringCsv = manipulaArquivo.abrirArquivo(caminhoENomeDoArquivo);\n"
                + "for (String linha : listaStringCsv) {\n"
                + "aux = linha.split(\";\");\n"
                + "SimpleDateFormat formato = new SimpleDateFormat(\"dd/MM/yyyy\");"
                + "ce = new " + nomeDaClasse + " (");
        int auc = -1;
        String as = " ";
        for (int i = 0; i < atributo.size(); i++) {
            String aux[] = atributo.get(i).split(";");
            auc++;
            if (aux[0].equals("String")) {
                String s = "String.valueOf(aux[" + auc + "]),";
                as = as + s;
            } else if (aux[0].equals("long") || (aux[0].equals("Long"))) {
                String s = "Long.valueOf(aux[" + auc + "]),";
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
            } else if (aux[0].equals("Float") || (aux[0].equals("float"))) {
                String s = "Float.valueOf(aux[" + auc + "]),";
                as = as +s;
            }
        }

        if (as.endsWith(", ")) {
            as = as.substring(0, as.length() - 2);
        }

        codigo.add(as
                + ");\n"
                + nomeDaClasseminusculo + "Controle.adicionar(ce);\n}\n \n"
                + "cardLayout.show(painelSul, \"Listagem\");}\n}\n});\n\n");

        //botao gravar
        codigo.add(
                "btGravar.addActionListener(new ActionListener() {\n"
                + "@Override\n"
                + "public void actionPerformed(ActionEvent e){\n"
                + "List<" + nomeDaClasse + "> lista" + nomeDaClasse + " = " + nomeDaClasseminusculo + "Controle.listar();\n"
                + "List<String> lista" + nomeDaClasse + "EmFormatoStringCSV = new ArrayList<>();\n"
                + "for (" + nomeDaClasse + " ce : lista" + nomeDaClasse + ") {\n"
                + "lista" + nomeDaClasse + "EmFormatoStringCSV.add(ce.toString());\n"
                + "}\n"
                + "new ManipulaArquivo().salvarArquivo(caminhoENomeDoArquivo, "
                + "lista" + nomeDaClasse + "EmFormatoStringCSV);\n"
                + "System.out.println(\"gravou\");\n"
                + "}\n"
                + "});\n");

        //botao buscar
        codigo.add(
                "btBuscar.addActionListener(new ActionListener() {\n"
                + "@Override\n"
                + "public void actionPerformed(ActionEvent e) {\n"
                + "btAdicionar.setVisible(false);"
                + "cardLayout.show(painelSul, \"Avisos\");\n"
                + "scrollTexto.setViewportView(texto);\n"
                + "if (tf" + primeiraLetramaiscula(pk) + ".getText().trim().isEmpty()){\n"
                + "JOptionPane.showMessageDialog(cp, \"" + pk + " deve ser preenchido\");\n"
                + "tf" + primeiraLetramaiscula(pk) + ".requestFocus();\n"
                + "tf" + primeiraLetramaiscula(pk) + ".selectAll();\n"
                + "} else {"
                + "chavePrimaria = tf" + primeiraLetramaiscula(pk) + ".getText();\n"
                + nomeDaClasseminusculo + "Entidade" + " = " + nomeDaClasseminusculo + "Controle.buscar(");
        if ((pkt.equals("int")) || (pkt.equals("Int"))) {
            codigo.add("Integer.valueOf(tf" + primeiraLetramaiscula(pk) + ".getText()));\n");
        } else if (pkt.equals("Double") || (pkt.equals("double"))) {
            codigo.add("Double.valueOf(tf" + primeiraLetramaiscula(pk) + ".getText()));\n");
        } else if (pkt.equals("Boolean") || (pkt.equals("boolean"))) {
            codigo.add("Boolean.valueOf(tf" + primeiraLetramaiscula(pk) + ".getText()));\n");
        } else if (pkt.equals("Long") || (pkt.equals("long"))) {
            codigo.add("Long.valueOf(tf" + primeiraLetramaiscula(pk) + ".getText()));\n");
        } else if (pkt.equals("Float") || (pkt.equals("float"))) {
            codigo.add("Float.valueOf(tf" + primeiraLetramaiscula(pk) + ".getText()));\n");
        }

        codigo.add(
                "if (" + nomeDaClasseminusculo + "Entidade" + "== null) {\n"
                + "btAdicionar.setVisible(true);\n"
                + "btAlterar.setVisible(false);\n"
                + "btExcluir.setVisible(false);\n");
        for (int i = 1; i < atributo.size(); i++) {
            String aux[] = atributo.get(i).split(";");
            if (aux[0].equals("boolean") || (aux[0].equals("Boolean"))) {
                codigo.add("cb" + primeiraLetramaiscula(aux[1]) + ".setSelected(false);\n");
            } else if (aux[0].equals("Long") || aux[0].equals("long")) {
                codigo.add("tf" + primeiraLetramaiscula(aux[1]) + ".setText(\"\");\n");
            } else if (aux[0].equals("DateTextField") || aux[0].equals("Date")) {
                codigo.add("tf" + primeiraLetramaiscula(aux[1]) + ".setText(\"\");\n");
            } else if (aux[0].equals("double") || aux[0].equals("Double")) {
                codigo.add("tf" + primeiraLetramaiscula(aux[1]) + ".setText(\"\");\n");
            } else if (aux[0].equals("int") || aux[0].equals("Int")) {
                codigo.add("tf" + primeiraLetramaiscula(aux[1]) + ".setText(\"\");\n");
            } else if (aux[0].equals("Float") || aux[0].equals("float")) {
                codigo.add("tf" + primeiraLetramaiscula(aux[1]) + ".setText(\"\");\n");
            } else {
                codigo.add("tf" + primeiraLetramaiscula(aux[1]) + ".setText(\"\");\n");
            }
        }
        codigo.add(
                "texto.setText(\"Não encontrou na lista - pode Adicionar\\n\\n\\n\");\n"
                + "}\n"
                + "else "
                + "{//encontrou"
                + " \n"
                + "SimpleDateFormat formato = new SimpleDateFormat(\"dd/MM/yyyy\");\n");
        for (int i = 1; i < atributo.size(); i++) {
            String aux[] = atributo.get(i).split(";");
            if (aux[0].equals("boolean") || (aux[0].equals("Boolean"))) {
                codigo.add("cb" + primeiraLetramaiscula(aux[1]) + ".setSelected(" + nomeDaClasseminusculo + "Entidade" + ".is" + primeiraLetramaiscula(aux[1]) + "()));\n");
            } else if (aux[0].equals("Long") || aux[0].equals("long")) {
                codigo.add("tf" + primeiraLetramaiscula(aux[1]) + ".setText(String.valueOf(" + nomeDaClasseminusculo + "Entidade"
                        + ".get" + primeiraLetramaiscula(aux[1]) + "()));\n");
            } else if (aux[0].equals("DateTextField") || aux[0].equals("Date")) {
                codigo.add("tf" + primeiraLetramaiscula(aux[1]) + ".setText(String.valueOf(" + nomeDaClasseminusculo + "Entidade" + ".get" + primeiraLetramaiscula(aux[1])
                        + "()" + "));\n");
                //codigo.add("SimpleDateFormat sdf = new SimpleDateFormat(\"dd/MM/yyyy\");\n");
            } else if (aux[0].equals("double") || aux[0].equals("Double")) {
                codigo.add("tf" + primeiraLetramaiscula(aux[1]) + ".setText(String.valueOf(" + nomeDaClasseminusculo + "Entidade"
                        + ".get" + primeiraLetramaiscula(aux[1]) + "()));\n");
            } else if (aux[0].equals("int") || aux[0].equals("Int")) {
                codigo.add("tf" + primeiraLetramaiscula(aux[1]) + ".setText(String.valueOf(" + nomeDaClasseminusculo + "Entidade"
                        + ".get" + primeiraLetramaiscula(aux[1]) + "()));\n");
            } else if (aux[0].equals("Float") || aux[0].equals("float")) {
                codigo.add("tf" + primeiraLetramaiscula(aux[1]) + ".setText(String.valueOf(" + nomeDaClasseminusculo + "Entidade"
                        + ".get" + primeiraLetramaiscula(aux[1]) + "()));\n");
            } else {
                codigo.add("tf" + primeiraLetramaiscula(aux[1]) + ".setText("
                        + nomeDaClasseminusculo + "Entidade.get" + primeiraLetramaiscula(aux[1]) + "());");
            }
        }

        codigo.add(
                "\n"
                + "btAlterar.setVisible(true);\n"
                + "btExcluir.setVisible(true);\n"
                + "texto.setText(\"Encontrou na Lista - pode Alterar ou Excluir\\n\\n\\n\");\n");
        for (int i = 1; i < atributo.size(); i++) {
            String aux[] = atributo.get(i).split(";");
            if (aux[0].equals("boolean") || (aux[0].equals("Boolean"))) {
                codigo.add("cb" + primeiraLetramaiscula(aux[1]) + ".setEnabled(false);\n");
            } else if (aux[0].equals("Long") || aux[0].equals("long")) {
                codigo.add("tf" + primeiraLetramaiscula(aux[1]) + ".setEditable(false);\n");
            } else if (aux[0].equals("DateTextField") || aux[0].equals("Date")) {
                codigo.add("tf" + primeiraLetramaiscula(aux[1]) + ".setEditable(false);\n");
            } else if (aux[0].equals("double") || aux[0].equals("Double")) {
                codigo.add("tf" + primeiraLetramaiscula(aux[1]) + ".setEditable(false);\n");
            } else if (aux[0].equals("int") || aux[0].equals("Int")) {
                codigo.add("tf" + primeiraLetramaiscula(aux[1]) + ".setEditable(false);\n");
            } else if (aux[0].equals("float") || aux[0].equals("Float")) {
                codigo.add("tf" + primeiraLetramaiscula(aux[1]) + ".setEditable(false);\n");
            } else {
                codigo.add("tf" + primeiraLetramaiscula(aux[1]) + ".setEditable(false);\n");
            }
        }

        codigo.add(
                "}}}});");

        //botão adicionar
        codigo.add(
                "\nbtAdicionar.addActionListener(new ActionListener() {\n"
                + "@Override\n"
                + "public void actionPerformed(ActionEvent e) {\n"
                + "acao = \"adicionar\";\n"
                + "tf" + primeiraLetramaiscula(pk) + ".setText(chavePrimaria);\n"
                + "tf" + primeiraLetramaiscula(pk) + ".setEditable(false);\n"
                + "tf" + primeiraLetramaiscula(atributo.get(1).split(";")[1]) + ".requestFocus();\n"
                + "btSalvar.setVisible(true);\n"
                + "btCancelar.setVisible(true);\n"
                + "btBuscar.setVisible(false);\n"
                + "btListar.setVisible(false);\n"
                + "btAlterar.setVisible(false);\n"
                + "btExcluir.setVisible(false);\n"
                + "\n"
                + "btAdicionar.setVisible(false);\n"
                + "texto.setText(\"Preencha os atributos\\n\\n\\n\\n\\n\");//limpa o campo texto\n");
        for (int i = 1; i < atributo.size(); i++) {
            String aux[] = atributo.get(i).split(";");
            if (aux[0].equals("boolean") || (aux[0].equals("Boolean"))) {
                codigo.add("cb" + primeiraLetramaiscula(aux[1]) + ".setEnabled(true);\n");
            } else if (aux[0].equals("Long") || aux[0].equals("long")) {
                codigo.add("tf" + primeiraLetramaiscula(aux[1]) + ".setEditable(true);\n");
            } else if (aux[0].equals("DateTextField") || aux[0].equals("Date")) {
                codigo.add("tf" + primeiraLetramaiscula(aux[1]) + ".setEditable(true);\n");
            } else if (aux[0].equals("double") || aux[0].equals("Double")) {
                codigo.add("tf" + primeiraLetramaiscula(aux[1]) + ".setEditable(true);\n");
            } else if (aux[0].equals("int") || aux[0].equals("Int")) {
                codigo.add("tf" + primeiraLetramaiscula(aux[1]) + ".setEditable(true);\n");
            } else if (aux[0].equals("Float") || aux[0].equals("float")) {
                codigo.add("tf" + primeiraLetramaiscula(aux[1]) + ".setEditable(true);\n");
            } else {
                codigo.add("tf" + primeiraLetramaiscula(aux[1]) + ".setEditable(true);\n");
            }
        }

        codigo.add(
                "}});");

        //botão alterar
        codigo.add(
                "btAlterar.addActionListener(new ActionListener() {\n"
                + "@Override\n"
                + "public void actionPerformed(ActionEvent e) {\n"
                + "acao = \"alterar\";\n"
                + "SimpleDateFormat formato = new SimpleDateFormat(\"dd/MM/yyyy\");\n"
                + "SimpleDateFormat sdfEua = new SimpleDateFormat(\"yyyy-MM-dd\");"
                + "tf" + primeiraLetramaiscula(pk) + ".setText(chavePrimaria);\n"
                + "tf" + primeiraLetramaiscula(pk) + ".setEditable(false);\n"
                + "tf" + primeiraLetramaiscula(atributo.get(1).split(";")[1]) + ".requestFocus();\n"
                + "btSalvar.setVisible(true);\n"
                + "btCancelar.setVisible(true);\n"
                + "btBuscar.setVisible(false);\n"
                + "btListar.setVisible(false);\n"
                + "btAlterar.setVisible(false);\n"
                + "btExcluir.setVisible(false);\n"
                + "texto.setText(\"Preencha os atributos\\n\\n\\n\\n\\n\");\n");
        for (int i = 1; i < atributo.size(); i++) {
            String aux[] = atributo.get(i).split(";");
            if (aux[0].equals("boolean") || (aux[0].equals("Boolean"))) {
                codigo.add("cb" + primeiraLetramaiscula(aux[1]) + ".setEnabled(true);\n");
            } else if (aux[0].equals("Long") || aux[0].equals("long")) {
                codigo.add("tf" + primeiraLetramaiscula(aux[1]) + ".setEditable(true);\n");
            } else if (aux[0].equals("DateTextField") || aux[0].equals("Date")) {
                codigo.add("tf" + primeiraLetramaiscula(aux[1]) + ".setEditable(true);\n");
            } else if (aux[0].equals("double") || aux[0].equals("Double")) {
                codigo.add("tf" + primeiraLetramaiscula(aux[1]) + ".setEditable(true);\n");
            } else if (aux[0].equals("int") || aux[0].equals("Int")) {
                codigo.add("tf" + primeiraLetramaiscula(aux[1]) + ".setEditable(true);\n");
            } else if (aux[0].equals("float") || aux[0].equals("Float")) {
                codigo.add("tf" + primeiraLetramaiscula(aux[1]) + ".setEditable(true);\n");
            } else {
                codigo.add("tf" + primeiraLetramaiscula(aux[1]) + ".setEditable(true);\n");
            }
        }

        codigo.add(
                "}\n"
                + "});\n");

        codigo.add(
                "btCancelar.addActionListener(new ActionListener() {\n"
                + "@Override\n"
                + "public void actionPerformed(ActionEvent e) {\n"
                + "btSalvar.setVisible(false);\n"
                + "btCancelar.setVisible(false);\n"
                + "btBuscar.setVisible(true);\n"
                + "btListar.setVisible(true);\n"
                + "tf" + primeiraLetramaiscula(pk) + ".setEditable(true);\n");
        for (int i = 1; i < atributo.size(); i++) {
            String aux[] = atributo.get(i).split(";");
            if (aux[0].equals("boolean") || (aux[0].equals("Boolean"))) {
                codigo.add("cb" + primeiraLetramaiscula(aux[1]) + ".setSelected(false);\n");
            } else if (aux[0].equals("Long") || aux[0].equals("long")) {
                codigo.add("tf" + primeiraLetramaiscula(aux[1]) + ".setText(\"\");\n");
            } else if (aux[0].equals("DateTextField") || aux[0].equals("Date")) {
                codigo.add("tf" + primeiraLetramaiscula(aux[1]) + ".setText(\"\");\n");
            } else if (aux[0].equals("double") || aux[0].equals("Double")) {
                codigo.add("tf" + primeiraLetramaiscula(aux[1]) + ".setText(\"\");\n");
            } else if (aux[0].equals("int") || aux[0].equals("Int")) {
                codigo.add("tf" + primeiraLetramaiscula(aux[1]) + ".setText(\"\");\n");
            } else if (aux[0].equals("Float") || aux[0].equals("float")) {
                codigo.add("tf" + primeiraLetramaiscula(aux[1]) + ".setText(\"\");\n");
            } else {
                codigo.add("tf" + primeiraLetramaiscula(aux[1]) + ".setText(\"\");\n");
            }
        }

        codigo.add(
                "tf" + primeiraLetramaiscula(atributo.get(1).split(";")[1]) + ".requestFocus();\n"
                + "tf" + primeiraLetramaiscula(atributo.get(1).split(";")[1]) + ".selectAll();\n"
                + "texto.setText(\"Cancelou\\n\\n\\n\\n\\n\");\n");
        for (int i = 1; i < atributo.size(); i++) {
            String aux[] = atributo.get(i).split(";");
            if (aux[0].equals("boolean") || (aux[0].equals("Boolean"))) {
                codigo.add("cb" + primeiraLetramaiscula(aux[1]) + ".setSelected(false);\n");
            } else if (aux[0].equals("Long") || aux[0].equals("long")) {
                codigo.add("tf" + primeiraLetramaiscula(aux[1]) + ".setEditable(false);\n");
            } else if (aux[0].equals("DateTextField") || aux[0].equals("Date")) {
                codigo.add("tf" + primeiraLetramaiscula(aux[1]) + ".setEditable(false);\n");
            } else if (aux[0].equals("double") || aux[0].equals("Double")) {
                codigo.add("tf" + primeiraLetramaiscula(aux[1]) + ".setEditable(false);\n");
            } else if (aux[0].equals("int") || aux[0].equals("Int")) {
                codigo.add("tf" + primeiraLetramaiscula(aux[1]) + ".setEditable(false);\n");
            } else if (aux[0].equals("float") || aux[0].equals("Float")) {
                codigo.add("tf" + primeiraLetramaiscula(aux[1]) + ".setEditable(false);\n");
            } else {
                codigo.add("tf" + primeiraLetramaiscula(aux[1]) + ".setEditable(false);\n");
            }
        }

        codigo.add(
                "}\n"
                + "});");

        codigo.add("SimpleDateFormat formato = new SimpleDateFormat(\"dd/MM/yyyy\");\n" 
                + "SimpleDateFormat sdfEua = new SimpleDateFormat(\"yyyy-MM-dd\");");
        
        //botão salvar
        codigo.add(
                "btSalvar.addActionListener(new ActionListener() {\n"
                + "@Override\n"
                + "public void actionPerformed(ActionEvent e) {\n"
                + "if (acao.equals(\"alterar\")) {\n"
                
                + nomeDaClasse + " " + nomeDaClasseminusculo + "Antigo = " + nomeDaClasseminusculo + "Entidade" + ";\n");

        for (int i = 1;
                i < atributo.size();
                i++) {
            String aux[] = atributo.get(i).split(";");
            if (aux[0].equals("String") || (aux[0].equals("string"))) {
                codigo.add(nomeDaClasseminusculo + "Entidade" + ".set" + primeiraLetramaiscula(aux[1]) + "(tf" + primeiraLetramaiscula(aux[1]) + ".getText());\n");
            } else if (aux[0].equals("Long") || (aux[0].equals("long"))) {
                codigo.add(nomeDaClasseminusculo + "Entidade" + ".set" + primeiraLetramaiscula(aux[1]) + "(Long.valueOf(tf" + primeiraLetramaiscula(aux[1]) + ".getText()));\n");
            } else if (aux[0].equals("Double") || (aux[0].equals("double"))) {
                codigo.add(nomeDaClasseminusculo + "Entidade" + ".set" + primeiraLetramaiscula(aux[1]) + "(Double.valueOf(tf" + primeiraLetramaiscula(aux[1]) + ".getText()));\n");
            } else if (aux[0].equals("Boolean") || (aux[0].equals("boolean"))) {
                codigo.add(nomeDaClasseminusculo + "Entidade" + ".set" + primeiraLetramaiscula(aux[1]) + "(cb" + primeiraLetramaiscula(aux[1]) + ".isSelected());\n");
            } else if (aux[0].equals("float") || (aux[0].equals("Float"))) {
                codigo.add(nomeDaClasseminusculo + "Entidade" + ".set" + primeiraLetramaiscula(aux[1]) + "(Float.valueOf(tf" + primeiraLetramaiscula(aux[1]) + ".getText()));\n");
            } else if (aux[0].equals("Date") || (aux[0].equals("date"))) {
                codigo.add("try{\n");
                codigo.add(nomeDaClasseminusculo + "Entidade" + ".set" + primeiraLetramaiscula(aux[1]) +"(Date.valueOf(sdfEua.format(formato.parse(tf" + primeiraLetramaiscula(aux[1]) + ".getText()))));");
                codigo.add("} catch (ParseException ex) {\n"
                        + "Logger.getLogger(ProdutoGUI.class.getName()).log(Level.SEVERE, null, ex);\n"
                        + "}");
            }
        }

        codigo.add(nomeDaClasseminusculo
                + "Controle.alterar(" + nomeDaClasseminusculo + "Entidade" + ", " + nomeDaClasseminusculo + "Antigo);\n"
                + "texto.setText(\"Registro alterado\\n\\n\\n\\n\\n\");\n"
                + "} else {//adicionar\n"
                + nomeDaClasseminusculo + "Entidade" + " = new " + nomeDaClasse + "();\n");
        for (int i = 0;
                i < atributo.size();
                i++) {
            String aux[] = atributo.get(i).split(";");
            if (aux[1].equals(pk)) {
                if (aux[0].equals("int")) {
                    codigo.add(nomeDaClasseminusculo + "Entidade" + ".set" + primeiraLetramaiscula(pk) + "(Integer.valueOf((tf" + primeiraLetramaiscula(pk) + ".getText())));\n");
                } else if (aux[0].equals("String") || (aux[0].equals("string"))) {
                    codigo.add(nomeDaClasseminusculo + "Entidade" + ".set" + primeiraLetramaiscula(pk) + "(String.valueOf(tf" + primeiraLetramaiscula(pk) + ".getText()));\n");

                } else if (aux[0].equals("long") || (aux[0].equals("Long"))) {
                    codigo.add(nomeDaClasseminusculo + "Entidade" + ".set" + primeiraLetramaiscula(pk) + "(Long.valueOf(tf" + primeiraLetramaiscula(pk) + ".getText()));\n");

                } else if (aux[0].equals("Double") || (aux[0].equals("double"))) {
                    codigo.add(nomeDaClasseminusculo + "Entidade" + ".set" + primeiraLetramaiscula(pk) + "(Double.valueOf(tf" + primeiraLetramaiscula(pk) + ".getText()));\n");

                } else if (aux[0].equals("Date") || (aux[0].equals("date"))) {
                    codigo.add(nomeDaClasseminusculo + "Entidade" + ".set" + primeiraLetramaiscula(pk) + "(Date.valueOf(tf" + primeiraLetramaiscula(pk) + ".getText()));\n");

                } else if (aux[0].equals("Float") || (aux[0].equals("float"))) {
                    codigo.add(nomeDaClasseminusculo + "Entidade" + ".set" + primeiraLetramaiscula(pk) + "(Float.valueOf(tf" + primeiraLetramaiscula(pk) + ".getText()));\n");

                } else if (aux[0].equals("Boolean") || (aux[0].equals("boolean"))) {
                    codigo.add(nomeDaClasseminusculo + "Entidade" + ".set" + primeiraLetramaiscula(pk) + "(cb" + primeiraLetramaiscula(pk) + ".isSelected());\n");
                }
            } else if (aux[0].equals("String") || (aux[0].equals("string"))) {
                codigo.add(nomeDaClasseminusculo + "Entidade" + ".set" + primeiraLetramaiscula(aux[1]) + "(tf" + primeiraLetramaiscula(aux[1]) + ".getText());\n");
            } else if (aux[0].equals("Double") || (aux[0].equals("double"))) {
                codigo.add(nomeDaClasseminusculo + "Entidade" + ".set" + primeiraLetramaiscula(aux[1]) + "(Double.valueOf((tf" + primeiraLetramaiscula(aux[1]) + ".getText())));\n");
            } else if (aux[0].equals("Int") || (aux[0].equals("int"))) {
                codigo.add(nomeDaClasseminusculo + "Entidade" + ".set" + primeiraLetramaiscula(aux[1]) + "(Integer.valueOf((tf" + primeiraLetramaiscula(aux[1]) + ".getText())));\n");
            } else if (aux[0].equals("Boolean") || (aux[0].equals("boolean"))) {
                codigo.add(nomeDaClasseminusculo + "Entidade" + ".set" + primeiraLetramaiscula(aux[1]) + "(cb" + primeiraLetramaiscula(aux[1]) + ".isSelected());\n");
            } else if (aux[0].equals("Long") || (aux[0].equals("long"))) {
                codigo.add(nomeDaClasseminusculo + "Entidade" + ".set" + primeiraLetramaiscula(aux[1]) + "(Float.valueOf(tf" + primeiraLetramaiscula(aux[1]) + ".getText()));\n");
            } else if (aux[0].equals("Float") || (aux[0].equals("float"))) {
                codigo.add(nomeDaClasseminusculo + "Entidade" + ".set" + primeiraLetramaiscula(aux[1]) + "(Long.valueOf(tf" + primeiraLetramaiscula(aux[1]) + ".getText()));\n");
            } else if (aux[0].equals("Date") || (aux[0].equals("date"))) {
                codigo.add("try{\n");
                codigo.add(nomeDaClasseminusculo + "Entidade" + ".set" + primeiraLetramaiscula(aux[1]) +"(Date.valueOf(sdfEua.format(formato.parse(tf" + primeiraLetramaiscula(aux[1]) + ".getText()))));");
                codigo.add("} catch (ParseException ex) {\n"
                        + "Logger.getLogger(ProdutoGUI.class.getName()).log(Level.SEVERE, null, ex);\n"
                        + "}");
            }
        }

        codigo.add(nomeDaClasseminusculo
                + "Controle.adicionar(" + nomeDaClasseminusculo + "Entidade" + ");\n"
                + "texto.setText(\"Foi adicionado um novo registro\\n\\n\\n\\n\\n\");}\n"
                + "btSalvar.setVisible(false);\n"
                + "btCancelar.setVisible(false);\n"
                + "btBuscar.setVisible(true);\n"
                + "btListar.setVisible(true);\n"
                + "tf" + primeiraLetramaiscula(pk) + ".setEditable(true);\n");
        for (int i = 1; i < atributo.size(); i++) {
            String aux[] = atributo.get(i).split(";");
            if (aux[0].equals("boolean") || (aux[0].equals("Boolean"))) {
                codigo.add("cb" + primeiraLetramaiscula(aux[1]) + ".setSelected(false);\n");
            } else if (aux[0].equals("Long") || aux[0].equals("long")) {
                codigo.add("tf" + primeiraLetramaiscula(aux[1]) + ".setText(\"\");\n");
            } else if (aux[0].equals("DateTextField") || aux[0].equals("Date")) {
                codigo.add("tf" + primeiraLetramaiscula(aux[1]) + ".setText(\"\");\n");                //codigo.add("SimpleDateFormat sdf = new SimpleDateFormat(\"dd/MM/yyyy\");\n");
            } else if (aux[0].equals("double") || aux[0].equals("Double")) {
                codigo.add("tf" + primeiraLetramaiscula(aux[1]) + ".setText(\"\");\n");
            } else if (aux[0].equals("int") || aux[0].equals("Int")) {
                codigo.add("tf" + primeiraLetramaiscula(aux[1]) + ".setText(\"\");\n");
            } else if (aux[0].equals("Float") || aux[0].equals("float")) {
                codigo.add("tf" + primeiraLetramaiscula(aux[1]) + ".setText(\"\");\n");
            }
        }
        codigo.add(
                "tf" + primeiraLetramaiscula(pk) + ".requestFocus();\n"
                + "tf" + primeiraLetramaiscula(pk) + ".selectAll();\n");

        for (int i = 1; i < atributo.size(); i++) {
            String aux[] = atributo.get(i).split(";");
            if (aux[0].equals("boolean") || (aux[0].equals("Boolean"))) {
                codigo.add("cb" + primeiraLetramaiscula(aux[1]) + ".setEnabled(true);\n");
            } else if (aux[0].equals("Long") || aux[0].equals("long")) {
                codigo.add("tf" + primeiraLetramaiscula(aux[1]) + ".setEditable(false);\n");
            } else if (aux[0].equals("DateTextField") || aux[0].equals("Date")) {
                codigo.add("tf" + primeiraLetramaiscula(aux[1]) + ".setEditable(false);\n");
            } else if (aux[0].equals("double") || aux[0].equals("Double")) {
                codigo.add("tf" + primeiraLetramaiscula(aux[1]) + ".setEditable(false);\n");
            } else if (aux[0].equals("int") || aux[0].equals("Int")) {
                codigo.add("tf" + primeiraLetramaiscula(aux[1]) + ".setEditable(false);\n");
            } else {
                codigo.add("tf" + primeiraLetramaiscula(aux[1]) + ".setEditable(false);\n");
            }
        }

        codigo.add(
                "}\n"
                + "\n"
                + "});");

        codigo.add(
                "btExcluir.addActionListener(new ActionListener() {\n"
                + "@Override\n"
                + "public void actionPerformed(ActionEvent e) {\n"
                + "tf" + primeiraLetramaiscula(pk) + ".setText(chavePrimaria);//para retornar ao valor original (caso o usuário mude e tente enganar o programa)\n"
                + "if (JOptionPane.YES_OPTION\n"
                + "== JOptionPane.showConfirmDialog(null,\n"
                + "\"Confirma a exclusão do registro <Nome = \" + " + nomeDaClasseminusculo + "Entidade" + ".get" + primeiraLetramaiscula(atributo.get(1).split(";")[1]) + "() +  \">?\", \"Confirm\",\n"
                + "JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {\n"
                + nomeDaClasseminusculo + "Controle.excluir(" + nomeDaClasseminusculo + "Entidade" + ");\n"
                + "}\n"
                + "btBuscar.setVisible(true);\n"
                + "btListar.setVisible(true);\n"
                + "tf" + primeiraLetramaiscula(pk) + ".setEditable(true);\n");

        for (int i = 1; i < atributo.size(); i++) {
            String aux[] = atributo.get(i).split(";");
            if (aux[0].equals("boolean") || (aux[0].equals("Boolean"))) {
                codigo.add("cb" + primeiraLetramaiscula(aux[1]) + ".setSelected(false);\n");
                codigo.add("cb" + primeiraLetramaiscula(aux[1]) + ".setEnable(true);\n");
            } else if (aux[0].equals("Long") || aux[0].equals("long")) {
                codigo.add("tf" + primeiraLetramaiscula(aux[1]) + ".setText(\"\");\n");
            } else if (aux[0].equals("DateTextField") || aux[0].equals("Date")) {
                codigo.add("tf" + primeiraLetramaiscula(aux[1]) + ".setText(\"\");\n");                //codigo.add("SimpleDateFormat sdf = new SimpleDateFormat(\"dd/MM/yyyy\");\n");
            } else if (aux[0].equals("double") || aux[0].equals("Double")) {
                codigo.add("tf" + primeiraLetramaiscula(aux[1]) + ".setText(\"\");\n");
            } else if (aux[0].equals("int") || aux[0].equals("Int")) {
                codigo.add("tf" + primeiraLetramaiscula(aux[1]) + ".setText(\"\");\n");
            } else if (aux[0].equals("float") || aux[0].equals("Float")) {
                codigo.add("tf" + primeiraLetramaiscula(aux[1]) + ".setText(\"\");\n");
            }
        }

        codigo.add(
                "tf" + primeiraLetramaiscula(pk) + ".requestFocus();\n"
                + "tf" + primeiraLetramaiscula(pk) + ".selectAll();\n"
                + "btExcluir.setVisible(false);\n"
                + "btAlterar.setVisible(false);\n"
                + "texto.setText(\"Excluiu o registro de \" + " + nomeDaClasseminusculo + "Entidade" + ".get" + primeiraLetramaiscula(pk) + "() + \" - \" + "
                + nomeDaClasseminusculo + "Entidade" + ".get" + primeiraLetramaiscula(atributo.get(1).split(";")[1]) + "() + \"\\n\\n\\n\\n\\n\");//limpa o campo texto\n"
                + "}\n"
                + "});"
        );

        codigo.add(
                "btListar.addActionListener(new ActionListener() {\n"
                + "            @Override\n"
                + "            public void actionPerformed(ActionEvent e) {\n"
                + "                List<" + nomeDaClasse + "> lt = " + nomeDaClasseminusculo + "Controle.listar();\n");
        codigo.add(
                "String[] colunas = new String[]{" + a + "};"
                + "\n"
                + "                Object[][] dados = new Object[lt.size()][colunas.length];\n"
                + "                String aux[];\n"
                + "                for (int i = 0; i < lt.size(); i++) {\n"
                + "                    aux = lt.get(i).toString().split(\";\");\n"
                + "                    for (int j = 0; j < colunas.length; j++) {\n"
                + "                        dados[i][j] = aux[j];\n"
                + "                    }\n"
                + "                }\n"
                + "                cardLayout.show(painelSul, \"Listagem\");\n"
                + "                scrollTabela.setPreferredSize(tabela.getPreferredSize());\n"
                + "                painel2.add(scrollTabela);\n"
                + "                scrollTabela.setViewportView(tabela);\n"
                + "                model.setDataVector(dados, colunas);\n"
                + "\n"
                + "                btAlterar.setVisible(false);\n"
                + "                btExcluir.setVisible(false);\n"
                + "                btAdicionar.setVisible(false);\n"
                + "            }\n"
                + "        });\n"
                + "");

        codigo.add(
                "addWindowListener(new WindowAdapter() {\n"
                + "            @Override\n"
                + "            public void windowClosing(WindowEvent e) {\n"
                + "                //antes de sair, salvar a lista em disco\n"
                + "                btGravar.doClick();\n"
                + "                // Sai da classe\n"
                + "                dispose();\n"
                + "            }\n"
                + "        });\n"
                + "\n"
                + "        setVisible(true);   \n"
                + "\n"
                + "        //depois que a tela ficou visível, clic o botão automaticamente.\n"
                + "        btCarregarDados.doClick();//execute o listener do btCarregarDados\n");

        /* */
//finaliza codigo
        codigo.add(
                "}");
        codigo.add(
                "}");
        for (int i = 0;
                i < codigo.size();
                i++) {
            System.out.println(codigo.get(i));
        }

        ManipulaArquivo manipulaArquivo = new ManipulaArquivo();

        manipulaArquivo.salvarArquivo(
                "C:/Users/jvmor/Documents/NetBeansProjects/Cobaia/src/Main/" + nomeDaClasse + "GUI.java", codigo);

    }

}
