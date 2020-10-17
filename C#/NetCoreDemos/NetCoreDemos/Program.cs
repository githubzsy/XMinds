using System;
using System.Runtime.InteropServices;
using System.Threading;
using System.Threading.Tasks;

namespace NetCoreDemos
{
    [StructLayout(LayoutKind.Explicit)]
    class Program
    {
        static void Main(string[] args)
        {
            // new TestInstructionRearrangement().Exec();
            Console.WriteLine(Thread.CurrentThread.ManagedThreadId.ToString("00"));
            InstanceTest();

        }

        /// <summary>
        /// 测试乱序执行
        /// </summary>
        static void InstanceTest()
        {
            while (true)
            {
                Task.Run(() =>
                {
                    Console.WriteLine(Thread.CurrentThread.ManagedThreadId.ToString("00"));
                });
            }
        }

        
       
    }
}
