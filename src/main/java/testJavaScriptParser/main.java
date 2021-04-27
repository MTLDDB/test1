package testJavaScriptParser;

import com.alibaba.fastjson.JSON;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : huang
 * @date :  2021/1/20
 * @time : 10:29
 * To change this template use File | Settings | File and Code Templates.
 */
public class main {
    public static void main(String[] args) throws ScriptException, NoSuchMethodException, FileNotFoundException {
        //本质上获得的是Rhino的接口在 JDK6.0后被集成  详细调用方法可以查看rhino的官网
        //获取脚本引擎管理器  接着获得 对应引擎的对象
        ScriptEngineManager manager=new ScriptEngineManager();
        ScriptEngine engine=manager.getEngineByName("javascript");

      //  engine.eval(new FileReader("C:\\Users\\PC\\Desktop\\manifest.js"));
        engine.eval(new FileReader("C:\\Users\\PC\\Desktop\\i18n-en.js"));
       // engine.eval(new FileReader("C:\\Users\\PC\\Desktop\\1610687928921-78.js"));
//        Object obj = engine.get("i18n");
//        System.out.println(JSON.toJSONString(obj));
//        Invocable inv = (Invocable) engine;
//        // 执行obj对象的名为hello的方法
//        inv.invokeMethod(obj, "saveAddress");

//        File file = new File("C:\\Users\\PC\\Desktop\\i18n-en.js");
//        //将File对象f直接注入到js脚本中并可以作为全局变量使用
//        engine.put("files", file);
//        engine.eval("print(files.getPath());print(files.getName());");
        //C:\Users\PC\Desktop
//        engine.eval(new java.io.FileReader(new File("C:\\Users\\PC\\Desktop\\i18n-en.js")));
//        //测试解析js代码
 String string="var data=function(){" +
         "return{" +
         "title:i18n.mnulblAutomotive," +
         "searchUrl:\"//www.microchipdirect.com/api/autosuggestapi/getautosuggestresult?term=\"," +
         "searchDestinationUrl:\"//www.microchipdirect.com/product/search/all/\"," +
         "placeholder:i18n.SearchAutoPart," +
         "autoPartSearch:\"\"," +
         "autocomplete:null," +
         "loading:!0," +
         "leftCol:[]," +
         "middleCol:[]," +
         "rightCol:[],item:null,partNumber:null,show:!1,searchItems:[],viewAll:!1,cats:[{title:i18n.lblAutoProdMicrocontroller,link:\"/chart.aspx?branchID=1012&automotive=1\",cats:[{title:i18n.menu8bit,link:\"/chart.aspx?branchID=1012&automotive=1\"},{title:i18n.menu16bit,link:\"/chart.aspx?branchID=8181&automotive=1\"},{title:i18n.menu32bit,link:\"/chart.aspx?branchID=211&automotive=1\"}],icon:\"far fa-microchip\"},{title:i18n.mnulblAnalog,link:\"/chart.aspx?branchID=1611\",cats:[{title:i18n.mnuPowerManagement,link:\"/chart.aspx?branchID=9006&automotive=1\"},{title:i18n.lblAutoProdLinear,link:\"/chart.aspx?branchID=11011&automotive=1\"},{title:i18n.lblAutoProdTempSens,link:\"/chart.aspx?branchID=2001&automotive=1\"},{title:i18n.lblAutoProdMixSignal,link:\"/chart.aspx?branchID=11022&automotive=1\"},{title:i18n.lblAutoProdAntennaIC,link:\"/chart.aspx?branchID=1611\"},{title:i18n.lblAutoProdMotorDrive,link:\"/chart.aspx?branchID=9054&automotive=1\"}],icon:\"far fa-signature\"},{title:i18n.lblAutoProdConnectivity,link:\"/chart.aspx?branchID=30029&automotive=1\",cats:[{title:i18n.lblAutoProdCANFD,link:\"/chart.aspx?branchID=1931&automotive=1\"},{title:i18n.lblAutoProdLIN,link:\"/chart.aspx?branchID=1934&automotive=1\"},{title:i18n.lblAutoProdEthernet,link:\"/chart.aspx?branchID=1635&automotive=1\"},{title:i18n.lblAutoProdMediaLB,link:\"/chart.aspx?branchID=321&automotive=1\"},{title:i18n.lblAutoProdMOST,link:\"/chart.aspx?branchID=321&automotive=1\"},{title:i18n.lblAutoProdUSB,link:\"/chart.aspx?branchID=1626&automotive=1\"}],icon:\"far fa-signal\"},{title:i18n.lblAutoProdMemory,link:\"/chart.aspx?branchID=71&automotive=1\",cats:[{title:i18n.lblAutoProdSerialEEPROMs,link:\"/chart.aspx?branchID=71&automotive=1\"},{title:i18n.lblAutoProdSerialSRAMs,link:\"/chart.aspx?branchID=307&automotive=1\"},{title:i18n.lblAutoProdSST,link:\"/chart.aspx?branchID=309&automotive=1\"}],icon:\"far fa-brain\"},{title:i18n.lblAutoProdTouch,link:\"/chart.aspx?branchID=30029&automotive=1\",cats:[{title:i18n.lblAutoProdAutoTouch,link:\"/chart.aspx?branchID=30029&automotive=1\"}],icon:\"far fa-fingerprint\"},{title:i18n.lblAutoProdAccess,link:\"/chart.aspx?branchID=30004\",cats:[{title:i18n.lblAutoProdCarAccess,link:\"/chart.aspx?branchID=30004\"}],icon:\"far fa-user-lock\"}]}}";
        engine.eval(string.toString());
//        // javax.script.Invocable 是一个可选的接口
//        // 检查你的script engine 接口是否已实现!
//        // 注意：JavaScript engine实现了Invocable接口
        Invocable inv = (Invocable) engine;
//        // 获取我们想调用那个方法所属的js对象
       // Object obj = engine.get("data");
        //System.out.println(JSON.toJSONString(obj));
        // 执行obj对象的名为hello的方法
        //inv.invokeMethod("data");
        if(engine instanceof Invocable){
            Invocable in = (Invocable)engine;
            Object result = in.invokeFunction("data");
            System.out.println("Result:"+JSON.toJSONString(result));
        }
    }
}
