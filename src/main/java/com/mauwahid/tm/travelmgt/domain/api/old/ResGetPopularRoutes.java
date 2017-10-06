package com.mauwahid.tm.travelmgt.domain.api.old;

import com.mauwahid.tm.travelmgt.domain.apimodel.old.PopularRoute;

import java.util.ArrayList;

public class ResGetPopularRoutes implements IData {

    private ArrayList<PopularRoute> popularRoutes;

    public ArrayList<PopularRoute> getPopularRoutes() {
        return popularRoutes;
    }

    public void setPopularRoutes(ArrayList<PopularRoute> popularRoutes) {
        this.popularRoutes = popularRoutes;
    }
}
