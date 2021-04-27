package test.arrowApi.jsonModel;

public class Data {//封装了库存及价格信息
    private String PartList;//实际存放的地方
    private String resources;

    public String getPartList() {
        return PartList;
    }

    public void setPartList(String partList) {
        PartList = partList;
    }

    public String getResources() {
        return resources;
    }

    public void setResources(String resources) {
        this.resources = resources;
    }
}
