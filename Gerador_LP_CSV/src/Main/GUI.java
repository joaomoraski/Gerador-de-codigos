package Main;

import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class GUI extends JFrame{
    
    private Container cp;
    
    private JLabel lbnome = new JLabel("Qual é o nome da classe?");
    private JTextField tfnome = new JTextField(15);
    
    private JLabel lbatributos = new JLabel("Informe os atributos: ");
    private JLabel lbexplicacao = new JLabel("Por enquanto so funciona com 5 no maximo");
    
}
