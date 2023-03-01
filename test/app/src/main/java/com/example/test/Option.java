package com.example.test;

public class Option {
    private String opt_name;
    private int vote;
    public Option(){}

    public Option(String opt_name, int vote) {
    this.opt_name =opt_name;
        this.vote = vote;
    }

    public String getOpt_name() {
        return opt_name;
    }

    public int getValue() {
        return vote;
    }

    public void setOpt_name(String opt_name) {
        this.opt_name = opt_name;
    }

    public void setValue(int value) {
        vote = value;
    }


    @Override
    public String toString() {
        return opt_name;
    }
}
