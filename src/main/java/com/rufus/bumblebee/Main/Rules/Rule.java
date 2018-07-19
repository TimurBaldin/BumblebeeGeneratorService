package com.rufus.bumblebee.Main.Rules;

public interface Rule {
    void construct() throws Exception;

    void transfer() throws Exception;
}
