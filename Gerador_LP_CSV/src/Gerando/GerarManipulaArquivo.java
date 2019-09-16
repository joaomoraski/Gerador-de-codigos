package Gerando;

import java.util.ArrayList;
import java.util.List;
import tools.ManipulaArquivo;

public class GerarManipulaArquivo {

    String nomeDaClasse = "Atleta";
    String nomeDaClasseminusculo = "atleta";
    List<String> atributo = new ArrayList<>();
    List<String> codigo = new ArrayList<>();
    List<String> codigocontrole = new ArrayList<>();

    public GerarManipulaArquivo() {
        codigo.add("package tools;\n"
                + "\n"
                + "// @author Radames\n"
                + "import java.io.BufferedReader;\n"
                + "import java.io.BufferedWriter;\n"
                + "import java.io.File;\n"
                + "import java.io.FileReader;\n"
                + "import java.io.FileWriter;\n"
                + "import java.util.ArrayList;\n"
                + "import java.util.List;\n"
                + "\n"
                + "\n"
                + "/*\n"
                + "IMPORTANTE\n"
                + "Caso não seja informado um caminho completo\n"
                + "o arquivo será salvo na pasta atual\n"
                + "No caso, dentro do próprio projeto.\n"
                + "*/\n"
                + "\n"
                + "public class ManipulaArquivo {\n"
                + "\n"
                + "    public ManipulaArquivo() {\n"
                + "    }\n"
                + "\n"
                + "    public boolean existeOArquivo(String caminhoENomeArquivo) {\n"
                + "        BufferedReader arquivoReader;\n"
                + "        File arq = new File(caminhoENomeArquivo);\n"
                + "        if (arq.exists()) {\n"
                + "            try {\n"
                + "                arquivoReader = new BufferedReader(new FileReader(caminhoENomeArquivo));\n"
                + "            } catch (Exception e) {\n"
                + "            }\n"
                + "            return true;\n"
                + "        } else {\n"
                + "            return false;\n"
                + "        }\n"
                + "    }\n"
                + "\n"
                + "    public boolean criarArquivoVazio(String caminhoENomeArquivo) {\n"
                + "        try {\n"
                + "            BufferedReader arquivoReader;\n"
                + "            FileWriter f = new FileWriter(caminhoENomeArquivo);\n"
                + "            f.close();\n"
                + "            try {\n"
                + "                arquivoReader = new BufferedReader(new FileReader(caminhoENomeArquivo));\n"
                + "            } catch (Exception e) {\n"
                + "            }\n"
                + "            return true;\n"
                + "        } catch (Exception e) {\n"
                + "            return false;\n"
                + "        }\n"
                + "    }\n"
                + "\n"
                + "    public List<String> abrirArquivo(String caminho) {\n"
                + "        List<String> texto = new ArrayList<String>();\n"
                + "        try {\n"
                + "            //OpenFile\n"
                + "            FileReader arquivo = new FileReader(caminho);\n"
                + "            BufferedReader conteudoDoArquivo = new BufferedReader(arquivo);\n"
                + "            String linha = conteudoDoArquivo.readLine();\n"
                + "            while (linha != null) {\n"
                + "                texto.add(linha);\n"
                + "                linha = conteudoDoArquivo.readLine();\n"
                + "            }\n"
                + "            conteudoDoArquivo.close();\n"
                + "        } catch (Exception e) {//Catch exception if any\n"
                + "            texto = null;\n"
                + "            System.err.println(\"Erro: \" + e.getMessage());\n"
                + "        }\n"
                + "        return texto;\n"
                + "    }\n"
                + "\n"
                + "    public int salvarArquivo(String caminho, List<String> texto) {\n"
                + "        try {\n"
                + "            // Create file \n"
                + "            FileWriter arquivo = new FileWriter(caminho);\n"
                + "            BufferedWriter conteudoDoArquivo = new BufferedWriter(arquivo);\n"
                + "            for (int i = 0; i < texto.size(); i++) {\n"
                + "                conteudoDoArquivo.write(texto.get(i) + System.getProperty(\"line.separator\")); // \n"
                + "            }\n"
                + "            conteudoDoArquivo.close();\n"
                + "        } catch (Exception e) {//Catch exception if any\n"
                + "            System.err.println(\"Error: \" + e.getMessage());\n"
                + "            return 1; //houve erro\n"
                + "        }\n"
                + "        return 0;\n"
                + "    }\n"
                + "}\n"
                + "");

        ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
        manipulaArquivo.salvarArquivo("C:/Users/jvmor/Documents/NetBeansProjects/Cobaia/src/Tools/ManipulaArquivo.java", codigo);
    }
}
