using System;
using System.Collections.Generic;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace CLRDemos
{
    public class SpinLockDemo
    {
        public static volatile int Tickets = 0;

        private static object l = new object();

        /// <summary>
        /// 循环售票-lock方式
        /// </summary>
        /// <param name="threadCount">多少个线程</param>
        public static void LoopSaleWithLock(int threadCount, int tickets)
        {
            Tickets = tickets;
            for (int i = 0; i < threadCount; i++)
            {
                Task.Run(() =>
                {
                    Thread.CurrentThread.IsBackground = false;
                    string threadName = Thread.CurrentThread.ManagedThreadId.ToString("00");
                    while (true)
                    {
                        if (Tickets > 0)
                        {
                            lock (l)
                            {
                                if (Tickets > 0)
                                {
                                    Console.WriteLine($"{threadName}卖出了票号:" + Tickets);
                                    Tickets--;

                                    Thread.Yield();
                                }
                                else return;
                            }
                        }
                        else return;
                    }
                });
            }

        }

        /// <summary>
        /// 循环售票-SpinLock方式
        /// </summary>
        /// <param name="threadCount">多少个线程</param>
        public static void LoopSaleWithSpinLock(int threadCount, int tickets)
        {
            Tickets = tickets;
            SpinLock spinLock = new SpinLock();

            for (int i = 0; i < threadCount; i++)
            {
                Task.Run(() =>
                {
                    Thread.CurrentThread.IsBackground = false;
                    string threadName = Thread.CurrentThread.ManagedThreadId.ToString("00");

                    bool _lock = false;

                    while (true)
                    {
                        if (Tickets > 0)
                        {
                            spinLock.Enter(ref _lock);
                            if (Tickets > 0)
                            {
                                Console.WriteLine($"{threadName}卖出了票号:" + Tickets);
                                Tickets--;

                                Thread.Yield();
                            }
                            else return;
                            spinLock.Exit();
                            _lock = false;
                        }
                        else return;
                    }
                });
            }

        }

        private static int _lock;
        /// <summary>
        /// 循环售票-SpinWait方式
        /// </summary>
        /// <param name="threadCount">多少个线程</param>
        public static void LoopSaleWithSpinWait(int threadCount, int tickets)
        {
            Tickets = tickets;
            for (int i = 0; i < threadCount; i++)
            {
                Console.WriteLine(i);
                Task.Run(() =>
                {
                    Thread.CurrentThread.IsBackground = false;
                    string threadName = Thread.CurrentThread.ManagedThreadId.ToString("00");


                    SpinWait sw = new SpinWait();
                    while (true)
                    {
                        if (Tickets > 0)
                        {
                            while (Interlocked.Exchange(ref _lock,1)!=0)
                            {
                                sw.SpinOnce();
                            }
                            if (Tickets > 0)
                            {
                                Console.WriteLine($"{threadName}卖出了票号:" + Tickets);
                                Tickets--;
                            }
                            else return;

                            Interlocked.Exchange(ref _lock, 0);

                            Thread.SpinWait(1);
                        }
                        else return;
                    }
                });
            }

        }


        /// <summary>
        /// 循环售票-InterLocked方式
        /// 只能保证Tickets递减是原子的，不会出现N线程执行Interlocked.Decrement之后新值小于原值-N的情况。但是并不能保证每次观察Tickets都是准确的值。
        /// </summary>
        /// <param name="threadCount"></param>
        /// <param name="tickets"></param>
        public static void LoopSaleWithInterLocked(int threadCount, int tickets)
        {
            Tickets = tickets;

            for (int i = 0; i < threadCount; i++)
            {
                Task.Run(() =>
                {
                    Thread.CurrentThread.IsBackground = false;
                    string threadName = Thread.CurrentThread.ManagedThreadId.ToString("00");

                    for (int j = 0; j < 10; j++)
                    {
                        Interlocked.Decrement(ref Tickets);
                        Thread.Yield();
                    }
                });
            }

        }
    }
}
