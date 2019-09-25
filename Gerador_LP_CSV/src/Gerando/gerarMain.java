package Gerando;

import java.util.ArrayList;
import java.util.List;
import tools.ManipulaArquivo;

public class gerarMain {

    String nomeDaClasse = "Main";
    String nomeDaClasseGUI = "Produto";
    String nomeDaClasseminusculo = "produto";
    List<String> atributo = new ArrayList<>();
    List<String> codigo = new ArrayList<>();
    List<String> codigocontrole = new ArrayList<>();

    public String primeiraLetramaiscula(String s) {
        return String.valueOf(s.charAt(0)).toUpperCase() + s.substring(1, s.length());
    }

    public gerarMain() {
        codigo.add("package Main;\n"
                + "\n"
                + "public class Main {\n"
                + "\n"
                + "    public static void main(String[] args) {\n"
                + "        new "+ nomeDaClasseGUI +"GUI();\n"
                + "    }\n"
                + "    \n"
                + "}");

        ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
        manipulaArquivo.salvarArquivo("C:/Users/jvmor/Documents/NetBeansProjects/Cobaia/src/Main/" + nomeDaClasse + ".java", codigo);

    }
}
