package com.furniture.utils;

/**
 *
 * @author linh
 */
public class Criterion {	
    private String column;
    private String value;
    private String relation;

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getValue() {
            return value;
    }

    public void setValue(String value) {
            this.value = value;
    }
    
    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public Criterion(String column,String value){
            this.column = column;
            this.value=value;
            this.relation = "";
    }
    
    public Criterion(String column,String value, String relation){
            this.column = column;
            this.value=value;
            this.relation = relation;
    }   
}
