package com.furniture.utils;

/**
 *
 * @author linh
 */
public class Criterion {	
    private String column;
    private Object value;
    private String relation;
    private String comparison;

    public String getComparison() {
        return comparison;
    }

    public void setComparison(String comparison) {
        this.comparison = comparison;
    }

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
            this.comparison = " = ";
    }
    
    public Criterion(String column,Object value, String relation){
            this.column = column;
            this.value=value;
            this.relation = relation;
            this.comparison = " = ";
    }   
    public Criterion(String column,Object value, String relation,String comparison){
            this.column = column;
            this.value=value;
            this.relation = relation;
            this.comparison = comparison;
    }  
}
