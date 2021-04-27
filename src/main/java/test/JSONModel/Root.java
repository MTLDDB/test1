package test.JSONModel;

import com.alibaba.fastjson.JSONObject;

public class Root
{
    private Props props;

    private String page;


    private String buildId;

    private String assetPrefix;


    private boolean isFallback;


    private boolean customServer;

    private boolean gip;

    private boolean appGip;

    public void setProps(Props props){
        this.props = props;
    }
    public Props getProps(){
        return this.props;
    }
    public void setPage(String page){
        this.page = page;
    }
    public String getPage(){
        return this.page;
    }

    public void setBuildId(String buildId){
        this.buildId = buildId;
    }
    public String getBuildId(){
        return this.buildId;
    }
    public void setAssetPrefix(String assetPrefix){
        this.assetPrefix = assetPrefix;
    }
    public String getAssetPrefix(){
        return this.assetPrefix;
    }

    public void setIsFallback(boolean isFallback){
        this.isFallback = isFallback;
    }
    public boolean getIsFallback(){
        return this.isFallback;
    }

    public void setCustomServer(boolean customServer){
        this.customServer = customServer;
    }
    public boolean getCustomServer(){
        return this.customServer;
    }
    public void setGip(boolean gip){
        this.gip = gip;
    }
    public boolean getGip(){
        return this.gip;
    }
    public void setAppGip(boolean appGip){
        this.appGip = appGip;
    }
    public boolean getAppGip(){
        return this.appGip;
    }
    public static Root fill(JSONObject jsonobj){
        Root entity = new Root();
        if (jsonobj.containsKey("props")) {
            entity.setProps(JSONObject.parseObject(jsonobj.getString("props"),Props.class));
        }
        if (jsonobj.containsKey("page")) {
            entity.setPage(jsonobj.getString("page"));
        }

        if (jsonobj.containsKey("buildId")) {
            entity.setBuildId(jsonobj.getString("buildId"));
        }
        if (jsonobj.containsKey("assetPrefix")) {
            entity.setAssetPrefix(jsonobj.getString("assetPrefix"));
        }

        if (jsonobj.containsKey("isFallback")) {
            entity.setIsFallback(jsonobj.getBoolean("isFallback"));
        }

        if (jsonobj.containsKey("customServer")) {
            entity.setCustomServer(jsonobj.getBoolean("customServer"));
        }
        if (jsonobj.containsKey("gip")) {
            entity.setGip(jsonobj.getBoolean("gip"));
        }
        if (jsonobj.containsKey("appGip")) {
            entity.setAppGip(jsonobj.getBoolean("appGip"));
        }
        return entity;
    }

}
