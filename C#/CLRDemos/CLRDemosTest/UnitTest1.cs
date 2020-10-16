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
        /// ����ThreadStatic�����������̺߳����߳������ֵ
        /// </summary>
        [TestMethod]
        public void TestThreadStatic()
        {
            
            ThreadStaticDemo.Set(1,2);
            string threadName = Thread.CurrentThread.ManagedThreadId.ToString("00");
            // B��ֵ����Ϊ2��
            Console.WriteLine(threadName + " " + ThreadStaticDemo.B);

            Task.Run(() =>
            {
                Thread.CurrentThread.IsBackground = false;
                string threadName = Thread.CurrentThread.ManagedThreadId.ToString("00");
                // ���ﻹ��0��˵��B��ThreadStatic��Ч��
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
