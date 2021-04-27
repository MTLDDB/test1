package testSolr.model;

import org.apache.solr.client.solrj.beans.Field;

public class Person {
    @Field
    private String id;
    @Field
    private String name;
    @Field
    private String description;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
