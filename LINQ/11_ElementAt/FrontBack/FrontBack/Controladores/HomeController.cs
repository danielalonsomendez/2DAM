using FrontBack.Client.Modelos;
using Microsoft.AspNetCore.Mvc;

namespace FrontBack.Controladores
{
    [ApiController]
    [Route("api/[controller]")]

    public class HomeController : Controller
    {
        private readonly HttpClient _http;

        public HomeController(HttpClient http)
        {
            _http = http;
        }

        [HttpGet]
        public async Task<IEnumerable<Post>> Get()
        {
            var posts = await _http.GetFromJsonAsync<List<Post>>(
                "https://jsonplaceholder.org/posts"
            );
            return posts ?? new List<Post>();
        }


    }
}
