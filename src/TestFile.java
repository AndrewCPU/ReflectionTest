/**
 * Created by AndrewCPU on 4/1/2017.
 */
public class TestFile {
    public void test(){
        System.out.println("Test");
    }
    public void testB(){
//        ScriptLoader.getInstance().getScriptByName("TestA").execute();
        System.out.println("TestBC");
        ScriptLoader.getInstance().getScriptByName("TestA").execute();
    }
}
