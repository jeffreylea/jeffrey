package com.learn.jeffrey.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Description <P>生成固定大小文件</P>
 *
 * @Author lijianfei
 * @Date 2020/10/15 8:20
 **/
public class CreateFile {
    static final Long length = 2*1024*1024*1024L-10;
    public static void main(String[] args) throws IOException
    {
        String filePath = "D:\\temp\\api-auto-test\\10000-files";
        File file = new File(filePath);

        if(!file.exists()){
            file.mkdirs();
        }
        long start = System.currentTimeMillis();
            File f = new File(filePath,"test2G.zip");
            if(!f.exists()){
                createFile(f,length);
            }
        long end = System.currentTimeMillis();
        System.out.println("total times "+(end-start));
        /*start = System.currentTimeMillis();
        for(int i=0;i<10000;i++){
            String fileName = "auto-test2"+i;
            File f = new File(filePath,fileName);
            if(!f.exists()){
                createFixLengthFile(f,1l);
            }
        }
        end = System.currentTimeMillis();
        System.out.println("total times "+(end-start));*/
    }

    public static void createFixLengthFile(File file, long length) throws IOException{
        FileOutputStream fos = null;
        FileChannel output = null;
        try {
            fos = new FileOutputStream(file);
            output = fos.getChannel();
            output.write(ByteBuffer.allocate(1), length-1);
        } finally {
            try {
                if (output != null) {
                    output.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private static void createFile(File file, long length) throws IOException {
        RandomAccessFile ff = null;
        try{
            ff = new RandomAccessFile(file,"rw");
            ff.setLength(length);
        }finally{
            if (ff != null){
                try{
                    ff.close();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}
