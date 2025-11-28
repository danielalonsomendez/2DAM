using Microsoft.AspNetCore.Mvc;

namespace FrontBack.Controladores
{
    [ApiController]
    [Route("api/[controller]")]

    public class PostController : Controller
    {
        [HttpGet]
        public IEnumerable<string> Get()
        {
            return new string[] { "post1", "post2", "post3" };
        }


    }
}
