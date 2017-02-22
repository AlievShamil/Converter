package com.example.devcom.converter.models;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.core.Persister;

import java.io.Reader;
import java.io.StringReader;
import java.util.List;

@Root(name = "ValCurs")
public class ValCurs {

    @Attribute(name = "Date", required = false)
    private String date;
    @Attribute(name = "name", required = false)
    private String name;

    @ElementList(name = "Valute", inline = true)
    public List<ValuteItem> value;


    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public static ValCurs deserializer(String xml) {

        Reader reader = new StringReader(xml);
        Persister serializer = new Persister();

        try {
            ValCurs curs = serializer.read(ValCurs.class, reader, false);
            return curs;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }

    }
}
