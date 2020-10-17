using System;
using System.Threading;
using System.Threading.Tasks;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using NetCoreDemos;

namespace NetCoreDemosTest
{
    [TestClass]
    public class LockDemoTest
    {
        /// <summary>
        /// 测试ThreadStatic变量，在主线程和子线程下面的值
        /// </summary>
        [TestMethod]
        public void TestThreadStatic()
        {
            
            ThreadStaticDemo.Set(1,2);
            string threadName = Thread.CurrentThread.ManagedThreadId.ToString("00");
            // B的值设置为2了
            Console.WriteLine(threadName + " " + ThreadStaticDemo.B);

            Task.Run(() =>
            {
                Thread.CurrentThread.IsBackground = false;
                string threadName = Thread.CurrentThread.ManagedThreadId.ToString("00");
                // 这里还是0，说明B的ThreadStatic生效了
                Console.WriteLine(threadName + " " + ThreadStaticDemo.B);
                ThreadStaticDemo.Set(3,4);
                Console.WriteLine(threadName + " " + ThreadStaticDemo.B);
            });
        }

        /// <summary>
        /// 最大线程数量
        /// </summary>
        int threadCount = 8;
        /// <summary>
        /// 票数
        /// </summary>
        int tickets = 100000000;

        /// 不带输出，1亿8线程，318
        [TestMethod]
        public void TestLoopSingleThread()
        {
            while (tickets>0)
            {
                tickets--;
            }
            Console.WriteLine(tickets);
        }


        /// <summary>
        /// 带输出，100万8线程，总耗时：19372，用户代码耗时5172+2591=7763
        /// 不带输出，1亿8线程，总耗时：1013933，用户代码耗时670000
        /// </summary>
        [TestMethod]
        public void TestLoopSaleWithLock()
        {
            LockDemo.LoopSaleWithLock(threadCount,tickets);
        }

        /// <summary>
        /// 带输出，100万8线程，总耗时：175788，用户代码耗时49863+24937=74800
        /// 不带输出，1亿8线程，很慢
        /// </summary>
        [TestMethod]
        public void TestLoopSaleWithMutexLock()
        {
            LockDemo.LoopSaleWithMutexLock(threadCount, tickets);
        }

        /// <summary>
        /// 带输出，100万8线程，总耗时：11087，用户代码耗时：5016+720=5736
        /// 不带输出，1亿8线程，总耗时：152158，用户代码耗时：87850+12555=100405，cpu：25.2%
        /// </summary>
        [TestMethod]
        public void TestLoopSaleWithSpinLock()
        {
            LockDemo.LoopSaleWithSpinLock(threadCount, tickets);
        }


        /// <summary>
        /// 带输出，100万8线程，总耗时：17271，用户代码耗时：4034+579=4613
        /// 不带输出，1亿8线程，总耗时：71087，用户代码耗时：40583+5803=46386，cpu：13.7%
        /// </summary>
        [TestMethod]
        public void TestLoopSaleWithSpinWait()
        {
            LockDemo.LoopSaleWithSpinWait(threadCount, tickets);
            
        }

        [TestMethod]
        public void TestLoopSaleWithInterLocked()
        {
            Console.WriteLine(LockDemo.Tickets);
        }
    }
}
