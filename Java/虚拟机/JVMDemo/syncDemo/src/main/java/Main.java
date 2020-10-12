import org.openjdk.jol.info.ClassLayout;

/**
 * @author zhoushiya
 * @date 2020/10/12 10:48
 */
public class Main {

    static L l=new L();

    public static void main(String[] args) {
        System.out.println(ClassLayout.parseInstance(l).toPrintable());

        System.out.println("hashCode(16进制):"+Integer.toHexString(l.hashCode()));

        System.out.println(ClassLayout.parseInstance(l).toPrintable());

        synchronized (l){
            System.out.println(ClassLayout.parseInstance(l).toPrintable());
        }
    }
}
