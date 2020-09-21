package NetworkDemo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.Random;
import java.util.concurrent.Future;

public class AIOClient {
    private static Integer PORT = 8888;
    private static String IP_ADDRESS = "127.0.0.1";
    /**
     * 异步通讯通道
     */
    private AsynchronousSocketChannel asyncSocketChannel;

    AIOClient() throws Exception {
        asyncSocketChannel = AsynchronousSocketChannel.open();  // 打开通道
    }

    /**
     * 连接服务端并且发送数据
     */
    void connectAndWrite() {
        // 连接并声明回调方法
        asyncSocketChannel.connect(new InetSocketAddress(IP_ADDRESS, PORT), this, new CompletionHandler<Void, AIOClient>() {
            @Override
            public void completed(Void result, AIOClient attachment) {
                // 设置一个收发消息的循环体，持续与服务器通信
                while (asyncSocketChannel.isOpen()) {
                    String[] operators = {"+", "-", "*", "/"};
                    Random random = new Random(System.currentTimeMillis());
                    String expression = random.nextInt(10) + operators[random.nextInt(4)] + (random.nextInt(10) + 1);
                    writeAndRead(expression);
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void failed(Throwable exc, AIOClient attachment) {
                exc.printStackTrace();
            }
        });
    }

    /**
     * 向服务端发送数据并且接收结果
     *
     * @param message 文字信息
     */
    void writeAndRead(String message) {
        try {
            asyncSocketChannel.write(ByteBuffer.wrap(message.getBytes())).get();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            asyncSocketChannel.read(byteBuffer).get();
            byteBuffer.flip();
            byte[] respByte = new byte[byteBuffer.remaining()];
            byteBuffer.get(respByte); // 将缓冲区的数据放入到 byte数组中
            System.out.println(new String(respByte, "utf-8").trim());
        } catch (Exception e) {
            e.printStackTrace();

            if(e.getCause() instanceof IOException){
                try {
                    asyncSocketChannel.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }

    }

    public static void main(String[] args) throws Exception {
        AIOClient myClient = new AIOClient();
        myClient.connectAndWrite();
        // 暂停主线程，防止程序退出。PS，服务器中不需要这段代码
        Thread.sleep(Integer.MAX_VALUE);
    }
}