package IODemo;

import java.io.*;
import java.util.Scanner;

/**
 * @author zhoushiya
 * @date 2020/9/19 17:53
 */
public class CopyFileDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入原始文件路径：");
        String src = scanner.next();
        System.out.println("请输入新文件路径：");
        String dest = scanner.next();
        System.out.println("开始拷贝...");
        copyFile(src, dest);
        System.out.println("拷贝结束");
    }

    /**
     * 拷贝文件
     *
     * @param src  原始路径
     * @param dest 新文件路径
     */
    public static void copyFile(String src, String dest) {
        try (FileInputStream fileInputStream = new FileInputStream(src);
             FileOutputStream fileOutputStream = new FileOutputStream(dest);
             BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream)) {
            byte[] buffer = new byte[4096];
            int readCount = 0;
            while ((readCount = bufferedInputStream.read(buffer)) != -1) {
                bufferedOutputStream.write(buffer, 0, readCount);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
