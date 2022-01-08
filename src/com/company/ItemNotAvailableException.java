package com.company;

class ItemNotAvailableException extends Exception {
    public ItemNotAvailableException(String ID) {
        super(ID);
    }
}