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

        /// <summary>
        /// ����߳�����
        /// </summary>
        int threadCount = 8;
        /// <summary>
        /// Ʊ��
        /// </summary>
        int tickets = 100000000;

        /// ���������1��8�̣߳�318
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
        /// �������100��8�̣߳��ܺ�ʱ��19372���û������ʱ5172+2591=7763
        /// ���������1��8�̣߳��ܺ�ʱ��1013933���û������ʱ670000
        /// </summary>
        [TestMethod]
        public void TestLoopSaleWithLock()
        {
            LockDemo.LoopSaleWithLock(threadCount,tickets);
        }

        /// <summary>
        /// �������100��8�̣߳��ܺ�ʱ��175788���û������ʱ49863+24937=74800
        /// ���������1��8�̣߳�����
        /// </summary>
        [TestMethod]
        public void TestLoopSaleWithMutexLock()
        {
            LockDemo.LoopSaleWithMutexLock(threadCount, tickets);
        }

        /// <summary>
        /// �������100��8�̣߳��ܺ�ʱ��11087���û������ʱ��5016+720=5736
        /// ���������1��8�̣߳��ܺ�ʱ��152158���û������ʱ��87850+12555=100405��cpu��25.2%
        /// </summary>
        [TestMethod]
        public void TestLoopSaleWithSpinLock()
        {
            LockDemo.LoopSaleWithSpinLock(threadCount, tickets);
        }


        /// <summary>
        /// �������100��8�̣߳��ܺ�ʱ��17271���û������ʱ��4034+579=4613
        /// ���������1��8�̣߳��ܺ�ʱ��71087���û������ʱ��40583+5803=46386��cpu��13.7%
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
