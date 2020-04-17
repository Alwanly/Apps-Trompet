package com.example.rpl.trompey;

public class Makanan {
    private String judulmakan;
    private int gambarmakan;
    private String hargamakan;


    public Makanan(String judulmakan, int gambarmakan, String hargamakan
                   ) {

        this.judulmakan = judulmakan;
        this.gambarmakan = gambarmakan;
        this.hargamakan = hargamakan;



    }

    public String getJudulmakan() { return judulmakan;
    }
    public int getGambarmakan() {
        return gambarmakan;
    }
    public String getHargamakan() {
        return hargamakan;
    }



    }
