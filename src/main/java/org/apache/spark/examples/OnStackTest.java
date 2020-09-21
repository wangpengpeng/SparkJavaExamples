package org.apache.spark.examples;

/**
 * GC默认cms收集器
 * -server -Xmx10m -Xms10m -XX:+DoEscapeAnalysis -XX:+PrintGC
 *
 *gc使用 G1收集器
 * -server -Xmx10m -Xms10m -XX:+DoEscapeAnalysis -XX:+PrintGC -XX:+UseG1GC
 *
 *
 * */

public class OnStackTest {
    public static void alloc(){
        byte[] b=new byte[2];
        b[0]=1;
    }

    public static void main(String[] args) {
        long b=System.currentTimeMillis();
        for(int i=0;i<100000000;i++){
            alloc();
        }
        long e=System.currentTimeMillis();
        System.out.println(e-b);
    }
}

