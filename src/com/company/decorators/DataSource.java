package com.company.decorators;

public interface DataSource {
    void writeData(byte[] data);

    byte[] readData();
}