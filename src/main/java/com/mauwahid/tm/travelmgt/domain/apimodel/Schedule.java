package com.mauwahid.tm.travelmgt.domain.apimodel;

import java.util.ArrayList;

public class Schedule {

    private ArrayList<Depart> depart;
    private ArrayList<Return> aReturn;

    public ArrayList<Depart> getDepart() {
        return depart;
    }

    public void setDepart(ArrayList<Depart> depart) {
        this.depart = depart;
    }

    public ArrayList<Return> getaReturn() {
        return aReturn;
    }

    public void setaReturn(ArrayList<Return> aReturn) {
        this.aReturn = aReturn;
    }
}
