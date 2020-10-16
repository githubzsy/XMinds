using System;
using System.Threading;
using System.Threading.Tasks;
using CLRDemos;
using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace CLRDemosTest
{
    [TestClass]
    public class UnitTest1
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

        [TestMethod]
        public void TestLoopSaleWithLock()
        {
            int threadCount = 4;
            int tickets = int.MaxValue;
            SpinLockDemo.LoopSaleWithLock(threadCount,tickets);
        }

        [TestMethod]
        public void TestLoopSaleWithSpinLock()
        {
            int threadCount = 4;
            int tickets = int.MaxValue;
            SpinLockDemo.LoopSaleWithSpinLock(threadCount, tickets);
        }


        [TestMethod]
        public void TestLoopSaleWithSpinWait()
        {
            int threadCount = 4;
            int tickets = int.MaxValue;
            SpinLockDemo.LoopSaleWithSpinWait(threadCount, tickets);
            
        }

        [TestMethod]
        public void TestLoopSaleWithInterLocked()
        {
            int threadCount = 10;
            int tickets = 100;
            SpinLockDemo.LoopSaleWithInterLocked(threadCount, tickets);
            Thread.Sleep(2000);
            Console.WriteLine(SpinLockDemo.Tickets);
        }


    }
}
