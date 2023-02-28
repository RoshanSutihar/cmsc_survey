package com.example.test;

public class Option {
    private String opt_name;
    private int Value;
    public Option(){}

    public Option(String opt_name, int value) {
        this.opt_name = opt_name;
        this.Value = value;
    }

    public String getOpt_name() {
        return opt_name;
    }

    public int getValue() {
        return Value;
    }

    public void setOpt_name(String opt_name) {
        this.opt_name = opt_name;
    }

    public void setValue(int value) {
        Value = value;
    }
}
