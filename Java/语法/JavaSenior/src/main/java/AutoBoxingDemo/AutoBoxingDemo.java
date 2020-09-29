package AutoBoxingDemo;

/**
 * @author zhoushiya
 * @date 2020/9/28 10:28
 */
public class AutoBoxingDemo {
    public static void main(String[] args) {
        Integer a=1;
        Integer b=2;
        Integer c=3;
        Integer d=3;
        Integer e=321;
        Integer f=321;
        Long g=3l;
        System.out.println(c.equals(a+b));
        // false因为a+b返回的不是long类型的
        System.out.println(g.equals(a+b));
    }
}
