package com.zhoushiya.interview.IODemo.AIO;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutionException;

public class AIOServerHandler implements CompletionHandler<AsynchronousSocketChannel, AIOServer> {
    private final Integer BUFFER_SIZE = 1024;

    /**
     * 请求连接成功时的回调函数
     *
     * @param asyncSocketChannel 监听到的异步通道
     * @param attachment         当前用于监听的服务端
     */
    @Override
    public void completed(AsynchronousSocketChannel asyncSocketChannel, AIOServer attachment) {
        try {
            System.out.println("接收到连接请求:" + asyncSocketChannel.getRemoteAddress().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 读取数据
        read(asyncSocketChannel);

        // 使用当前服务端继续监听，并尝试接收请求(进入到下一次循环了)
        attachment.asynchronousServerSocketChannel.accept(attachment, this);
    }

    /**
     * 读取数据
     *
     * @param asyncSocketChannel 当前异步通道
     */
    private void read(final AsynchronousSocketChannel asyncSocketChannel) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(BUFFER_SIZE);
        // 读取信息
        asyncSocketChannel.read(byteBuffer, byteBuffer, new CompletionHandler<Integer, ByteBuffer>() {
            @Override
            public void completed(Integer resultSize, ByteBuffer attachment) {
                //进行读取之后,重置标识位
                attachment.flip();
                //获取读取的数据
                String resultData = new String(attachment.array()).trim();
                System.out.println("Server -> " + "收到客户端的数据信息为:" + resultData);
                String response = resultData + " = 算不出来 ";
                write(asyncSocketChannel, response);
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {
                exc.printStackTrace();
            }
        });
    }

    // 写入数据
    private void write(AsynchronousSocketChannel asyncSocketChannel, String response) {
        try {
            // 把数据写入到缓冲区中
            ByteBuffer buf = ByteBuffer.allocate(BUFFER_SIZE);
            buf.put(response.getBytes());
            buf.flip();
            // 在从缓冲区写入到通道中
            asyncSocketChannel.write(buf).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void failed(Throwable exc, AIOServer attachment) {
        System.out.println("进入failed");
        exc.printStackTrace();
    }
}