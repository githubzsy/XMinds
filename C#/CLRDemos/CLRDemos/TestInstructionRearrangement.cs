using System;
using System.Threading;

namespace Demos
{
    public class TestInstructionRearrangement
    {
        private int x = 0, y = 0, a = 0, b = 0;
        private int count = 0;
        public void Exec()
        {
            while (true)
            {
                //所有变量全部恢复初始值
                a = 0;
                b = 0;
                x = 0;
                y = 0;
                //计数加1
                count++;
                CountdownEvent countdownEvent = new CountdownEvent(2);
                ThreadPool.SetMaxThreads(5, 100);
                ThreadPool.QueueUserWorkItem((state) => {
                    a = 1;
                    y = b;
                    countdownEvent.Signal();
                });
                ThreadPool.QueueUserWorkItem((state) => {
                    b = 1;
                    x = a;
                    countdownEvent.Signal();
                });
                countdownEvent.Wait();
                //此时，上面的两个线程已经执行完毕。如果代码在执行过程中都是
                //一行一行的代码顺序执行的，那么上面两个线程无论是怎样的先后执行
                //顺序，a=1和b=1，这两句代码作为第一行代码，总有一句是最先执行的，
                //也就是说，到最后x或y肯定都不为0。如果到最后x和y都同时为0了，那
                //么根据反证法原理，代码在执行过程中，有可能并不绝对按照代码
                //顺序依次执行，当然也就发生了指令重排，代码执行中存在指令重排
                //的情况也就得到了证明

                //如果x和y都等于0了，就输出结果（如果Java代码真的按照顺序一句一句
                //执行的话，是绝对不可能出现x和y同时为0的）
                if (x == 0 && y == 0)
                {
                    //指令重排输出语句
                    String message = "第" + count + "次出现指令重排，x=" + x + "，y=" + y;
                    //打印，证明完毕
                    System.Console.Error.WriteLine(message);
                    //结束程序
                    break;
                }
                Thread.Sleep(200);
            }
        }
    }
}
