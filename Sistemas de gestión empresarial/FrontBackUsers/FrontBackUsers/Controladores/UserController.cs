using FrontBackUsers.Client.Modelos;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Hosting;

namespace FrontBackUsers.Controladores
{
    [ApiController]
    [Route("api/[controller]")]

    public class UserController : Controller
    {
        private readonly HttpClient _http;

        public UserController(HttpClient http)
        {
            _http = http;
        }

        [HttpGet]
        public async Task<IEnumerable<Usuario>> Get()
        {
            var users = await _http.GetFromJsonAsync<List<Usuario>>(
                "https://jsonplaceholder.org/users"
            );
            return users ?? new List<Usuario>();
        }

    }
}
