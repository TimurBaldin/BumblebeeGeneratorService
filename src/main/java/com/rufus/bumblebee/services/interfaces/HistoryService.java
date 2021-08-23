package com.rufus.bumblebee.services.interfaces;

public interface HistoryService<R, I> {

    R getHistory(I input);

}
