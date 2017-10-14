package com.company.decorators;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class EncryptionAESDecorator extends DataSourceDecorator {

    public EncryptionAESDecorator(DataSource source) {
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

    public byte[] encode(byte[] data){
        try{

            Cipher cipher = Cipher.getInstance("AES");

            SecretKeySpec key = new SecretKeySpec("keyMostBe16Bytes".getBytes(),"AES");

            cipher.init(Cipher.ENCRYPT_MODE,key);

            return cipher.doFinal(data);

        }catch (Exception ex){
            ex.printStackTrace();
        }

        return null;
    }

    public byte[] decode(byte[] data){
        try{

            SecretKeySpec key = new SecretKeySpec("keyMostBe16Bytes".getBytes(),"AES");
            Cipher decipher = Cipher.getInstance("AES");
            decipher.init(Cipher.DECRYPT_MODE,key);
            return decipher.doFinal(data);

        }catch (Exception ex){
            ex.printStackTrace();
        }

        return null;
    }
}
