package com.mauwahid.tm.travelmgt.domain.api.old;

import org.springframework.stereotype.Component;

@Component
public class ContohData implements IData {

    private String nama;
    private String alamat;


    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }


}
