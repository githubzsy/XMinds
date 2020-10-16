using System;
using System.Collections.Generic;
using System.Reflection.PortableExecutable;
using System.Text;
using System.Threading;

namespace Demos
{
    public class InstanceClass
    {
        public int x = 1;

        private static InstanceClass _instance;

        private static readonly object _lock = new object();

        private InstanceClass()
        {

        }

        public static InstanceClass GetInstance()
        {
            if (_instance == null)
            {
                lock (_lock)
                {
                    if (_instance == null)
                    {
                        var instance = new InstanceClass();
                        // 添加内存屏障，上下放的（汇编）指令不能穿越，避免了_instance.x=0的发生
                        Interlocked.MemoryBarrier();
                        _instance = instance;
                    }
                }
            }

            return _instance;
        }
    }
}
