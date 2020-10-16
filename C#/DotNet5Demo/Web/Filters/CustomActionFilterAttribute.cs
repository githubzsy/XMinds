using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc.Filters;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Web.Filters
{
    public class CustomActionFilterAttribute :Attribute, IActionFilter
    {
        public void OnActionExecuted(ActionExecutedContext context)
        {
            context.HttpContext.Response.WriteAsync("******OnActionExecuted******");
        }

        public void OnActionExecuting(ActionExecutingContext context)
        {
            string user = context.HttpContext.Session.GetString("User");
            if (string.IsNullOrEmpty(user))
            {
                context.HttpContext.Session.SetString("User", $"张三");
            }

            context.HttpContext.Response.WriteAsync("******OnActionExecuting******");
        }
    }
}
