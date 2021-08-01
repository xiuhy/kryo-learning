package org.bigmoon.learning.kryo;

import com.esotericsoftware.kryo.kryo5.Kryo;
import com.esotericsoftware.kryo.kryo5.io.InputChunked;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 这里主要实现chunk.kryo方法
 */
public class ChunkSteam {

    public static void main(String[] args)throws Exception {
        inputStream();
    }


    public ChunkSteam() {
    }

    public static void inputStream()throws Exception{

        Kryo kryo=new Kryo();
        InputStream inputputStream=new FileInputStream("abc.txt");
        InputChunked output=new InputChunked(inputputStream);
        kryo.readClassAndObject(output);
//        output.endChunk();
    }
}
