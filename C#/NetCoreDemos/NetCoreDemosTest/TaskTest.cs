using System;
using System.Threading.Tasks;
using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace NetCoreDemosTest
{
    [TestClass]
    public class TaskTest
    {
        [TestMethod]
        public void Test1()
        {
            int i = 0;
            Task.Run(() =>
            {
                Console.WriteLine("第1个Task开始，i:" + i);
                i++;
            }).ContinueWith(t =>
            {
                Console.WriteLine("第2个Task开始，i:"+i);
                i++;
            }).ContinueWith(t =>
            {
                Console.WriteLine("第3个Task开始，i:" + i);
                i++;
            });
            Console.WriteLine(i);
        }
    }
}
