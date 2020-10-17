using System;
using System.Threading;
using System.Threading.Tasks;

namespace NetCoreDemos
{
    public class LockDemo
    {
        public static volatile int Tickets = 0;

        private static object l = new object();

        /// <summary>
        /// 循环售票-lock方式
        /// </summary>
        /// <param name="threadCount">多少个线程</param>
        public static void LoopSaleWithLock(int threadCount, int tickets)
        {
            LoopBase(threadCount,tickets, (threadName) =>
            {
                while (true)
                {
                    if (Tickets > 0)
                    {
                        lock (l)
                        {
                            if (Tickets > 0)
                            {
                                // Console.WriteLine($"{threadName}卖出了票号:" + Tickets);
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

        /// <summary>
        /// 循环售票-SpinLock方式
        /// </summary>
        /// <param name="threadCount">多少个线程</param>
        public static void LoopSaleWithSpinLock(int threadCount, int tickets)
        {
            SpinLock spinLock = new SpinLock();

            LoopBase(threadCount,tickets, (threadName) =>
            {
                bool _slock = false;

                while (true)
                {
                    if (Tickets > 0)
                    {
                        spinLock.Enter(ref _slock);
                        if (Tickets > 0)
                        {
                            // Console.WriteLine($"{threadName}卖出了票号:" + Tickets);
                            Tickets--;
                            spinLock.Exit();
                            _slock = false;
                        }
                        else
                        {
                            spinLock.Exit();
                            _slock = false;
                            return;
                        }
                        
                    }
                    else return;
                }

            });

        }

        private static int _lock;
        /// <summary>
        /// 循环售票-SpinWait方式
        /// </summary>
        /// <param name="threadCount">多少个线程</param>
        public static void LoopSaleWithSpinWait(int threadCount, int tickets)
        {
            LoopBase(threadCount,tickets, (threadName) =>
            {
                SpinWait sw = new SpinWait();
                while (true)
                {
                    if (Tickets > 0)
                    {
                        while (Interlocked.Exchange(ref _lock, 1) != 0)
                        {
                            sw.SpinOnce();
                        }
                        if (Tickets > 0)
                        {
                            // Console.WriteLine($"{threadName}卖出了票号:" + Tickets);
                            Tickets--;

                            Interlocked.Exchange(ref _lock, 0);
                            Thread.SpinWait(1);
                        }
                        else
                        {
                            Interlocked.Exchange(ref _lock, 0);
                            Thread.SpinWait(1);
                            return;
                        }

                    }
                    else return;
                }
            });
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

        private static Mutex _lockMutex=new Mutex(false,null);

        /// <summary>
        /// 循环售票-互斥锁方式
        /// </summary>
        /// <param name="threadCount">多少个线程</param>
        public static void LoopSaleWithMutexLock(int threadCount, int tickets)
        {
            LoopBase(threadCount,tickets, (threadName) =>
            {
                while (true)
                {
                    if (Tickets > 0)
                    {
                        _lockMutex.WaitOne();
                        if (Tickets > 0)
                        {
                            // Console.WriteLine($"{threadName}卖出了票号:" + Tickets);
                            Tickets--;
                            _lockMutex.ReleaseMutex();
                        }
                        else
                        {
                            _lockMutex.ReleaseMutex();
                            return;
                        }

                    }
                    else return;
                }
            });
        }

        private static void LoopBase(int threadCount,int tickets,Action<string> whileAction)
        {
            var manualResetEvents = new ManualResetEvent[threadCount];
            // 线程池最大使用8个线程，每个线程对应一个ManualResetEvent
            ThreadPool.SetMaxThreads(threadCount, threadCount);
            Tickets = tickets;
            for (int i = 0; i < threadCount; i++)
            {
                var manualResetEvent = new ManualResetEvent(false);
                manualResetEvents[i] = manualResetEvent;
                ThreadPool.QueueUserWorkItem((object obj) =>
                {
                    ManualResetEvent mre = (ManualResetEvent)obj;
                    mre.Set();
                    string threadName = Thread.CurrentThread.ManagedThreadId.ToString("00");
                    whileAction(threadName);
                }, manualResetEvent);
            }

            WaitHandle.WaitAll(manualResetEvents);
            Console.WriteLine(Tickets);
        }

        
    }
}
