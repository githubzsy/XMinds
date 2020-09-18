using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Hosting;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.HttpsPolicy;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.FileProviders;
using Microsoft.Extensions.Hosting;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Threading.Tasks;
using Web.MiddleWares;

namespace Web
{
    public class Startup
    {
        public Startup(IConfiguration configuration)
        {
            Configuration = configuration;
        }

        public IConfiguration Configuration { get; }

        // This method gets called by the runtime. Use this method to add services to the container.
        public void ConfigureServices(IServiceCollection services)
        {
            services.AddControllersWithViews();

            // 如何处理session
            services.AddSession();

            services.AddDistributedRedisCache(options =>
            {
                // 别名
                options.InstanceName = "Net5Redis";
                options.Configuration = "127.0.0.1:6379,password=welcome";
            });
        }

        // This method gets called by the runtime. Use this method to configure the HTTP request pipeline.
        public void Configure(IApplicationBuilder app, IWebHostEnvironment env)
        {
            app.Use(next =>
            {
                Console.WriteLine("性能监控开始");
                return async c =>
                {
                    DateTime start = DateTime.Now;
                    await next.Invoke(c);
                    Console.WriteLine($"{c.Request.Path}:访问时长:{(DateTime.Now - start).TotalMilliseconds}毫秒");
                };
            });

            // 使用黑名单中间件，与下面效果相同
            app.UseMiddleware<BlackListMiddleWare>();

            //app.Use(next =>
            //{
            //    Console.WriteLine("黑名单监控开始");
            //    return async c =>
            //    {
            //        if (c.Connection.RemoteIpAddress.ToString() == "127.0.0.1")
            //        {
            //            await c.Response.WriteAsync("you are in blacklist");
            //        }
            //        else
            //        {
            //            await next.Invoke(c);
            //        }
            //    };
            //});


            // 请求过程中，经过session中间件的处理
            app.UseSession();

            if (env.IsDevelopment())
            {
                app.UseDeveloperExceptionPage();
            }
            else
            {
                app.UseExceptionHandler("/Home/Error");
                // The default HSTS value is 30 days. You may want to change this for production scenarios, see https://aka.ms/aspnetcore-hsts.
                app.UseHsts();
            }
            app.UseHttpsRedirection();

            // 指定静态文件地址
            app.UseStaticFiles(new StaticFileOptions()
            {
                FileProvider = new PhysicalFileProvider(Path.Combine(Directory.GetCurrentDirectory(), "wwwroot"))
            });
            app.UseStaticFiles();

            app.UseRouting();

            app.UseAuthorization();

            app.UseEndpoints(endpoints =>
            {
                endpoints.MapControllerRoute(
                    name: "default",
                    pattern: "{controller=Home}/{action=Index}/{id?}");
            });
        }
    }
}
