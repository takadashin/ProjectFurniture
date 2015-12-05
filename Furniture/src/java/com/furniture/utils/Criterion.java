package com.furniture.utils;

/**
 *
 * @author linh
 */
public class Criterion {	
    private String column;
    private Object value;
    private String relation;

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public Object getValue() {
            return value;
    }

    public void setValue(Object value) {
            this.value = value;
    }
    
    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public Criterion(String column,Object value){
            this.column = column;
            this.value=value;
            this.relation = "";
    }
    
    public Criterion(String column,Object value, String relation){
            this.column = column;
            this.value=value;
            this.relation = relation;
    }   
}
