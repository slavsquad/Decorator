package com.company.decorators;

import java.io.*;

public class FileDataSource implements DataSource {
    private String name;

    public FileDataSource(String name) {
        this.name = name;
    }

    @Override
    public void writeData(byte[] data) {
        File file = new File(name);
        try (OutputStream fos = new FileOutputStream(file)) {
            fos.write(data);
            fos.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public byte[] readData() {
        byte[] buffer = null;
        try{
            File file = new File(name);
            InputStream is = new FileInputStream(file);
            long length = file.length();
            if (length > Integer.MAX_VALUE) {
                return null;
            }

            byte[] bytes = new byte[(int)length];

            // Read in the bytes
            int offset = 0;
            int numRead = 0;
            while (offset < bytes.length && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
                offset += numRead;
            }

            // Ensure all the bytes have been read in
            if (offset < bytes.length) {
                throw new IOException("Could not completely read file "+file.getName());
            }

            // Close the input stream and return bytes
            is.close();
            return bytes;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
}