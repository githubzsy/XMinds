using System;
using System.Reflection;

namespace NetCoreDemos
{
    class GenericDecorator : DispatchProxy
    {
        /// <summary>
        /// 被代理的对象
        /// </summary>
        public object Wrapped { get; set; }

        /// <summary>
        /// 实际方法执行之前的动作
        /// </summary>
        public Action<MethodInfo, object[]> Start { get; set; }

        /// <summary>
        /// 实际方法执行之后的动作
        /// </summary>
        public Action<MethodInfo, object[], object> End { get; set; }

        protected override object Invoke(MethodInfo targetMethod, object[] args)
        {
            Start(targetMethod, args);
            object result = targetMethod.Invoke(Wrapped, args);
            End(targetMethod, args, result);
            return result;
        }
    }

    /// <summary>
    /// 被代理的接口
    /// </summary>
    interface IEcho
    {
        string Hello(string info);
    }

    /// <summary>
    /// 被代理的对象
    /// </summary>
    public class EchoImpl : IEcho
    {
        public string Hello(string info)
        {
            return "Hello:"+info;
        }
    }

    public class Main
    {
        public static void M()
        {
            var toWrap = new EchoImpl();
            var decorator = DispatchProxy.Create<IEcho, GenericDecorator>();
            ((GenericDecorator)decorator).Wrapped = toWrap;
            ((GenericDecorator)decorator).Start = (tm, a) => Console.WriteLine($"{tm}开始调用,参数为:({string.Join(",",a)})");
            ((GenericDecorator)decorator).End = (tm, a, r) => Console.WriteLine($"{tm}调用完毕,参数为:({string.Join(',', a)}),返回结果为:{r}");
            decorator.Hello("Me");
        }
    }


}
