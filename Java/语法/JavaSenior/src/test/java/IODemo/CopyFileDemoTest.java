package IODemo;

import org.junit.Test;

import java.io.File;

/**
 * @author zhoushiya
 * @date 2020/9/19 18:26
 */
public class CopyFileDemoTest {
    @Test
    public void copyFileTest() {
        CopyFileDemo.copyFile("d:\\东芝.png", "d:\\东芝1.png");
        assert new File("d:\\东芝.png").length() == new File("d:\\东芝1.png").length();
    }
}
