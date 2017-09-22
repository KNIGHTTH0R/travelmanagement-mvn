package com.mauwahid.tm.travelmgt.domain.api.response;

import com.mauwahid.tm.travelmgt.domain.apimodel.SearchInfo;

import java.util.ArrayList;

public class ResSearch implements IData {

    private ArrayList<SearchInfo> searchInfos;

    public ArrayList<SearchInfo> getSearchInfos() {
        return searchInfos;
    }

    public void setSearchInfos(ArrayList<SearchInfo> searchInfos) {
        this.searchInfos = searchInfos;
    }
}
