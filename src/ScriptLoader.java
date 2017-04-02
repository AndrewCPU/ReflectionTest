import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by AndrewCPU on 3/25/2017.
 */
public class ScriptLoader {
    private static ScriptLoader instance = null;
    public static ScriptLoader getInstance(){
        if(instance == null)
            instance = new ScriptLoader();
        return instance;
    }
    private List<Script> scripts = new ArrayList<>();
    public void loadScriptsFromFile(File file){
//If this pathname does not denote a directory, then listFiles() returns null.
        List<File> filesInFolder = null;
        try{
           filesInFolder=  Files.walk(Paths.get(file.getAbsolutePath()))
                    .filter(Files::isRegularFile).filter((f) -> f.toFile().getName().contains(".script"))
                    .map(Path::toFile)
                    .collect(Collectors.toList());
        }catch (Exception ex){ex.printStackTrace();}
       // List<File> results = new ArrayList<>();
//        for (File f : files) {
//            String extension = "";
//
//            int i = f.getName().lastIndexOf('.');
//            int p = Math.max(f.getName().lastIndexOf('/'), f.getName().lastIndexOf('\\'));
//
//            if (i > p) {
//                extension = f.getName().substring(i+1);
//            }
//            if (f.isFile() && extension.equalsIgnoreCase("script")){
//                results.add(file);
//            }
//        }
        for(File f : filesInFolder){
            System.out.println(f.getAbsolutePath());
            try {
                scripts.add(parseScript(f));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //
    }
    public Script parseScript(File file) throws Exception{
        String className = "";
        String methodName = "";
        String scriptName = "";
        List<String> doc = java.nio.file.Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
//        System.out.println(doc.toString());
        for(String line : doc){
            String[] data = line.contains(": ") ? line.split(": ") : new String[] {};
            if(data[0].equalsIgnoreCase("Class")){
                className = data[1];
//                System.out.println(data[1]);
            }
            else if(data[0].equalsIgnoreCase("Method")){
                methodName = data[1];
//                System.out.println(data[1]);
            }
            else if(data[0].equalsIgnoreCase("Name")){
                scriptName = data[1];
            }
        }

//        try(BufferedReader br = new BufferedReader(new FileReader(file.getAbsolutePath()))) {
//            StringBuilder sb = new StringBuilder();
//            String line = br.readLine();
//
//            while (line != null) {
//                sb.append(line);
//                sb.append(System.lineSeparator());
//                line = br.readLine();
////                if(line==null) continue;
//                System.out.println(line);
//                String[] data = line.contains(": ") ? line.split(": ") : new String[] {};
//                if(data[0].equalsIgnoreCase("ClassName")){
//                    className = data[1];
//                    System.out.println(data[1]);
//                }
//                else if(data[0].equalsIgnoreCase("Method")){
//                    methodName = data[1];
//                    System.out.println(data[1]);
//                }
//            }
//        } catch(Exception ex){ex.printStackTrace();}
        return new Script(scriptName, className, methodName, file.getAbsoluteFile().getParentFile().getAbsolutePath());
    }

    public List<Script> getScripts() {
        return scripts;
    }
    public void clearScripts(){
        scripts.clear();
    }
    public void setScripts(List<Script> scripts) {
        this.scripts = scripts;
    }

    public Script getScriptByName(String s){
        for(Script script : getScripts()){
            if(script.getName().equalsIgnoreCase(s))
                return script;
        }
        return null;
    }
}
