package com.example.devcom.converter.models;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.convert.Converter;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;

import java.math.BigDecimal;
import java.text.DecimalFormat;

@Root(name = "Valute")
public class ValuteItem {
    @Attribute(name = "ID", required = false)
    private String id;
    @Element(name = "NumCode", required = false)
    private int numCode;
    @Element(name = "CharCode", required = false)
    private String charCode;

    @Element(name = "Nominal", required = false)
    private int nominal;
    @Element(name = "Name", required = false)
    private String name;
    @Element(name = "Value", required = false)
    private String value;



    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public int getNumCode() {
        return numCode;
    }
    public void setNumCode(int numCode) {
        this.numCode = numCode;
    }

    public String getCharCode() {
        return charCode;
    }
    public void setCharCode(String charCode) {
        this.charCode = charCode;
    }

    public int getNominal() {
        return nominal;
    }
    public void setNominal(int nominal) {
        this.nominal = nominal;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value =value;
    }

    @Override
    public String toString() {
        return getName();
    }
}
