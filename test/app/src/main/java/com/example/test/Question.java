package com.example.test;

public class Question {
    int question_id;
    private String question_name;
    private String opt_a;
    private String opt_b;
    private String opt_c;
    private String opt_d;

    public Question(int question_id,String question_name, String opt_a, String opt_b, String opt_c, String opt_d) {
        this.question_name = question_name;
        this.opt_a = opt_a;
        this.opt_b = opt_b;
        this.opt_c = opt_c;
        this.opt_d = opt_d;
        this.question_id = question_id;
    }

    public String getQuestion_name() {
        return question_name;
    }

    public String getOpt_a() {
        return opt_a;
    }

    public String getOpt_b() {
        return opt_b;
    }

    public String getOpt_c() {
        return opt_c;
    }

    public String getOpt_d() {
        return opt_d;
    }
    public int getQuestion_id() {
        return question_id;
    }
    @Override
    public String toString() {
        return "Question{" +
                "question_id=" + question_id +
                ", question_name='" + question_name + '\'' +
                ", opt_a='" + opt_a + '\'' +
                ", opt_b='" + opt_b + '\'' +
                ", opt_c='" + opt_c + '\'' +
                ", opt_d='" + opt_d + '\'' +
                '}';
    }

}

