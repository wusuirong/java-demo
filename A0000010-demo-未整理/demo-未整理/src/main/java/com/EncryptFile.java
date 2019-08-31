package com;


import java.io.*;
import java.util.Random;

public class EncryptFile {

    public static void main(String[] args) throws IOException {

        System.out.println("usage: java TestFile.class -enc/dnc xxxFile");
        System.out.println("file is " + args[1]);

        if ("enc".equals(args[0])) {
            File f = new File(args[1]);
            FileInputStream fis = new FileInputStream(f);
            DataInputStream dis = new DataInputStream(fis);

            FileOutputStream fos = new FileOutputStream(new File(args[1] + ".enc"));
            DataOutputStream dos = new DataOutputStream(fos);

            Random r = new Random(System.currentTimeMillis());
            int b = 0;
            while (-1 != (b = dis.read())) {
                dos.write(r.nextInt());
                dos.write(b);
            }

            dis.close();
            dos.close();
        } else {
            File f = new File(args[1]);
            FileInputStream fis = new FileInputStream(f);
            DataInputStream dis = new DataInputStream(fis);

            FileOutputStream fos = new FileOutputStream(new File(args[1] + ".dnc"));
            DataOutputStream dos = new DataOutputStream(fos);

            int b = 0;
            while (true) {
                b = dis.read();
                if (-1 == b) {
                    break;
                }
                b = dis.read();
                if (-1 == b) {
                    break;
                }
                dos.write(b);
            }

            dis.close();
            dos.close();
        }
        System.out.println("finish");

    }
}