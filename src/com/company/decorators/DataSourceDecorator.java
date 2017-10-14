package com.company.decorators;

public class DataSourceDecorator implements DataSource {
    private DataSource wrappee;

    public DataSourceDecorator(DataSource source) {
        this.wrappee = source;
    }

    @Override
    public void writeData(byte[] data) {
        wrappee.writeData(data);
    }

    @Override
    public byte[] readData() {
        return wrappee.readData();
    }
}