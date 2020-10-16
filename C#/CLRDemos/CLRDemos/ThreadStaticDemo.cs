using System;
using System.Collections.Generic;
using System.Text;
using System.Threading;

namespace CLRDemos
{
    public class ThreadStaticDemo
    {
        [ThreadStatic] public static int A;

        [ThreadStatic] public static int B;

        public static void Set(int a,int b)
        {
            A = a;
            B = b;
        }


    }
}
