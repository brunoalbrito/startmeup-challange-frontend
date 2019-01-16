package com.br.startmeup;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class HibbiesBean {

    private String hobby = "Java Coding";

    public String getHobby(){
        return hobby;
    }

    public void setHobby(String hobby){
        this.hobby = hobby;
    }
}
