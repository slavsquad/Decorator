package com.company;

import com.company.decorators.DataSource;
import com.company.decorators.EncryptionAESDecorator;
import com.company.decorators.EncryptionBase64Decorator;
import com.company.decorators.FileDataSource;
import jdk.nashorn.internal.runtime.ECMAException;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;

public class Main {

    public static void main (String[] args) throws Exception{

        String salaryRecords = "ФИО,Зарплата\nИван Петров,100000\nВладимир Иванов,912000";
        DataSource encoded = new EncryptionAESDecorator(new FileDataSource("out/OutputDemo.txt"));
        encoded.writeData(salaryRecords.getBytes());

        DataSource decoded = new EncryptionAESDecorator(new FileDataSource("out/OutputDemo.txt"));

        DataSource plain = new FileDataSource("out/OutputDemo.txt");

        System.out.println("- Input ----------------");
        System.out.println(salaryRecords);
        System.out.println("\n- Encoded --------------");
        String encod = new String(plain.readData(),"UTF-8");
        System.out.println(encod);
        System.out.println("\n- Decoded --------------");
        System.out.println(new String(decoded.readData(),"UTF-8"));

    }

}
