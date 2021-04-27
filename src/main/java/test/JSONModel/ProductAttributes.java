package test.JSONModel;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;


import java.util.ArrayList;
import java.util.List;

public class ProductAttributes
{
    private List<Attributes> attributes;

    private List<Categories> categories;

    public void setAttributes(List<Attributes> attributes){
        this.attributes = attributes;
    }
    public List<Attributes> getAttributes(){
        return this.attributes;
    }
    public void setCategories(List<Categories> categories){
        this.categories = categories;
    }
    public List<Categories> getCategories(){
        return this.categories;
    }
    public static ProductAttributes fill(JSONObject jsonobj){
        ProductAttributes entity = new ProductAttributes();
        if (jsonobj.containsKey("attributes")) {
            entity.setAttributes(Attributes.fillList(jsonobj.getJSONArray("attributes")));
        }
        if (jsonobj.containsKey("categories")) {
            entity.setCategories(Categories.fillList(jsonobj.getJSONArray("categories")));
        }
        return entity;
    }
    public static List<ProductAttributes> fillList(JSONArray jsonarray) {
        if (jsonarray == null || jsonarray.size() == 0)
            return null;
        List<ProductAttributes> olist = new ArrayList<ProductAttributes>();
        for (int i = 0; i < jsonarray.size(); i++) {
            olist.add(fill(jsonarray.getJSONObject(i)));
        }
        return olist;
    }
}
