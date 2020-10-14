package ThreadDemo.LinkedTransferQueueDemo;

/**
 * @author zhoushiya
 * @date 2020/10/13 14:56
 */
public class TempObject {
    private int index;

    public TempObject(int index) {
        System.out.println("生产了元素:"+index);
        this.setIndex(index);
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }


}
