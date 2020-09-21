package NetworkDemo;

import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author zhoushiya
 * @date 2020/9/20 10:47
 */
public class MessageHelper {
    /**
     * 输出流
     */
    final BufferedOutputStream bufferedOutputStream;

    /**
     * 输入流
     */
    final BufferedInputStream bufferedInputStream;


    public MessageHelper(Socket socket) throws IOException {
        bufferedOutputStream = new BufferedOutputStream(socket.getOutputStream());
        bufferedInputStream = new BufferedInputStream(socket.getInputStream());
    }

    /**
     * 小消息的缓冲区大小8字节
     */
    final static int SMALL_BUFFER_SIZE = 8;

    /**
     * 大消息的缓冲区大小8KB
     */
    final static int BIG_BUFFER_SIZE = 1024 * 8;

    /**
     * 向远端输出文本
     *
     * @param text
     * @throws IOException
     */
    public void sendText(String text) throws IOException {
        int msgType = 1;
        byte[] bytes = intToByteArray(msgType);
        bufferedOutputStream.write(bytes);
        bytes = intToByteArray(text.length());
        bufferedOutputStream.write(bytes);
        bufferedOutputStream.write(text.getBytes());
    }

    /**
     * 向远端输出文件
     *
     * @param fileName
     * @throws IOException
     */
    public void sendFile(String fileName) throws IOException {
        int msgType = 2;
        long fileSize = new File(fileName).length();
        if (fileSize > Integer.MAX_VALUE) {
            System.out.println("最大支持单个文件2GB");
            return;
        }

        byte[] bytes = intToByteArray(msgType);
        bufferedOutputStream.write(bytes);

        bytes = intToByteArray((int) fileSize);
        bufferedOutputStream.write(bytes);
        try (BufferedInputStream bufferedInputStream1 = new BufferedInputStream(new FileInputStream(fileName));) {
            int len;
            bytes = new byte[BIG_BUFFER_SIZE];
            while ((len = bufferedInputStream1.read(bytes)) != -1) {
                bufferedOutputStream.write(bytes, 0, len);
            }
        }
        System.out.println("文件已输出");
    }

    public void receiveMessage() throws IOException {
        byte[] bytes = new byte[SMALL_BUFFER_SIZE];
        bufferedInputStream.read(bytes);
        int msgType = byteArrayToInt(bytes);
        bytes = new byte[SMALL_BUFFER_SIZE];
        bufferedInputStream.read(bytes);
        int msgLength = byteArrayToInt(bytes);

        bytes = new byte[BIG_BUFFER_SIZE];
        switch (msgType) {
            case 1:
                try(BufferedOutputStream bufferedOutputStream1=new BufferedOutputStream(System.out);){
                    outputMessage(bytes, msgLength, bufferedOutputStream1);
                }
                break;
            case 2:
                String fileName = "d:\\temp\\" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                try (BufferedOutputStream bufferedOutputStream1 = new BufferedOutputStream(new FileOutputStream(fileName));) {
                    outputMessage(bytes, msgLength, bufferedOutputStream1);
                    System.out.println("文件已存储：" + fileName);
                }
                break;
            default:
                return;
        }

    }

    private void outputMessage(byte[] bytes, int msgLength, BufferedOutputStream bufferedOutputStream1) throws IOException {
        int left = msgLength;
        while (left > 0) {
            int min = Math.min(bytes.length, left);
            bufferedInputStream.read(bytes, 0, min);
            bufferedOutputStream1.write(bytes, 0, min);
            left -= min;
        }
    }

    //    public static int bytes2Int(byte[] bytes) {
//        //如果不与0xff进行按位与操作，转换结果将出错，有兴趣的同学可以试一下。
//        int int1 = bytes[0] & 0xff;
//        int int2 = (bytes[1] & 0xff) << 8;
//        int int3 = (bytes[2] & 0xff) << 16;
//        int int4 = (bytes[3] & 0xff) << 24;
//
//        return int1 | int2 | int3 | int4;
//    }
//
//    public static byte[] intToBytes(int num) {
//        byte[] bytes = new byte[SMALL_BUFFER_SIZE];
//        //通过移位运算，截取低8位的方式，将int保存到byte数组
//        bytes[0] = (byte) (num >>> 24);
//        bytes[1] = (byte) (num >>> 16);
//        bytes[2] = (byte) (num >>> 8);
//        bytes[3] = (byte) num;
//        return bytes;
//    }
//
//    public static byte[] longToByte(long number) {
//        long temp = number;
//        byte[] b = new byte[8];
//        for (int i = 0; i < b.length; i++) {
//            b[i] = new Long(temp & 0xff).byteValue();
//            temp = temp >> 8;// 向右移8位
//        }
//        return b;
//    }
    public int byteArrayToInt(byte[] data) throws IOException {
        ByteArrayInputStream bai = new ByteArrayInputStream(data);
        DataInputStream dis = new DataInputStream(bai);
        return dis.readInt();
    }

    public byte[] intToByteArray(int a) throws IOException {
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(bao);
        dos.writeInt(a);
        byte[] buf = bao.toByteArray();
        return buf;
    }
}
