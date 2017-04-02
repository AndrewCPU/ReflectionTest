import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Created by AndrewCPU on 3/25/2017.
 */
public class Script {
    private String name;
    private String className;
    private String methodName;
    private String path;

    public Script(String name, String className, String methodName, String path) {
        this.name = name;
        this.className = className;
        this.methodName = methodName;
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void execute(){
        File file = new File(getPath());
        try {
            URL url = file.toURL();
            URL[] urls = new URL[]{url};
            ClassLoader cl = new URLClassLoader(urls);
            Class cls = cl.loadClass(getClassName());
            cls.getMethod(getMethodName(), new Class[]{}).invoke(cls.newInstance(), new Object[] {});
        } catch (Exception e) { e.printStackTrace();}
    }
}
