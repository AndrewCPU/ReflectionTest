import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Created by AndrewCPU on 4/1/2017.
 */
public class Main extends JFrame{
    public static void main(String[] args) {

//        for(Script script : loader.getScripts()){
//            script.execute();
//        }
//        loader.getScriptByName("TestB").execute();
        new Main();
    }
    public Main(){
        setLayout(null);
        setBounds(0,0,500,500);
        ScriptLoader loader = new ScriptLoader();
        loader.loadScriptsFromFile(new File(System.getenv("APPDATA") + "\\scripts\\"));
        String[] names = new String[loader.getScripts().size()];
        for(int i = 0; i<names.length; i++)
            names[i] = loader.getScripts().get(i).getName();
        JComboBox comboBox = new JComboBox(names);
        comboBox.setBounds(0,0,200,50);
        add(comboBox);


        JButton runScript = new JButton("Run Selected Script");
        runScript.setBounds(0,55,100,50);
        runScript.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loader.getScriptByName(comboBox.getSelectedItem().toString()).execute();
            }
        });
        add(runScript);

        JButton loadScripts = new JButton("Select Script File");
        loadScripts.setBounds(0,110,100,50);
        loadScripts.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loader.clearScripts();
                loader.loadScriptsFromFile(new File(JOptionPane.showInputDialog("Enter folder path:")));
            }
        });

        add(loadScripts);



        setVisible(true);
    }
}
