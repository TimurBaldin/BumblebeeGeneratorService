package com.rufus.bumblebee.generators.providers;

import java.io.IOException;

public interface DataProvider <T> {

    T getResourceByName(String fileName) throws IOException;

}
