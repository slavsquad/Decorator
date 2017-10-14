package com.company.decorators;

import java.util.Base64;

public class EncryptionBase64Decorator extends DataSourceDecorator {

    public EncryptionBase64Decorator(DataSource source) {
        super(source);
    }

    @Override
    public void writeData(byte[] data) {
        super.writeData(encode(data));
    }

    @Override
    public byte[] readData() {
        return decode(super.readData());
    }

    public byte[]readDecodedData() {
        return decode(super.readData());
    }

    public byte[] encode(byte[] data) {
        for (int i = 0; i < data.length; i++) {
            data[i] += (byte) 1;
        }
        return Base64.getEncoder().encode(data);
    }

    public byte[] decode(byte[] data) {
        byte[] result = Base64.getDecoder().decode(data);
        for (int i = 0; i < result.length; i++) {
            result[i] -= (byte) 1;
        }
        return result;
    }
}