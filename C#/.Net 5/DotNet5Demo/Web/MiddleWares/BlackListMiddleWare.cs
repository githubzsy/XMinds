using Microsoft.AspNetCore.Http;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Web.MiddleWares
{
    public class BlackListMiddleWare
    {
        /// <summary>
        /// 黑名单IP
        /// </summary>
        private readonly HashSet<string> _blackList = new HashSet<string>() { "127.0.0.1" };

        private readonly RequestDelegate _next;
        public BlackListMiddleWare(RequestDelegate next)
        {
            _next = next;
        }

        public async Task Invoke(HttpContext context)
        {
            if (_blackList.Contains(context.Connection.RemoteIpAddress.ToString()))
            {
                // 执行黑名单提示，并且不走后面的管道了
                await context.Response.WriteAsync("you are in blacklist");
            }
            else
            {
                await _next.Invoke(context);
            }
        }
    }
}
