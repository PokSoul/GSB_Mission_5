using System.Web.Http;
using WebActivatorEx;
using GSB_Mission_5_Server;
using Swashbuckle.Application;

[assembly: PreApplicationStartMethod(typeof(SwaggerConfig), "Register")]

namespace GSB_Mission_5_Server
{
    public class SwaggerConfig
    {
        public static void Register()
        {
            var thisAssembly = typeof(SwaggerConfig).Assembly;

            GlobalConfiguration.Configuration
                               .EnableSwagger(c => c.SingleApiVersion("v1", "GSB_Mission_5_Server"))
                               .EnableSwaggerUi();
        }
    }
}