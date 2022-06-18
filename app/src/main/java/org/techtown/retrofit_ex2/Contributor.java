package org.techtown.retrofit_ex2;
public class Contributor {
    String login;
    String html_url;

    int contributions;

    @Override
    public String toString() {
        return login + " (" + contributions + ")";
    }

}
