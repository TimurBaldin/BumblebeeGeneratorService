package com.rufus.bumblebee.Main.Tools;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class TestNumberRepositoryTest {

    @Test
    public void create() {
        ArrayList<Number> array=new ArrayList<Number>();
        for(int i=0;i<=10000;i++){
            array.add(i);
        }

    }
}