using FrontBackUsers.Client.Modelos;
using Microsoft.AspNetCore.Mvc;
using System.Text.Json;

namespace FrontBackUsers.Controladores
{
    [ApiController]
    [Route("api/[controller]")]
    public class RestaurantesController : Controller
    {

        private readonly IWebHostEnvironment _env;

        public RestaurantesController(IWebHostEnvironment env)
        {
            _env = env;
        }

        [HttpGet]
        public async Task<ActionResult<IEnumerable<Restaurante>>> Get()
        {
            var filePath = Path.Combine(_env.ContentRootPath, "Datos", "Restaurantes.json");

            if (!System.IO.File.Exists(filePath))
                return NotFound("El fichero JSON no existe");

            var json = await System.IO.File.ReadAllTextAsync(filePath);
            var restaurantes = JsonSerializer.Deserialize<List<Restaurante>>(json);

            return Ok(restaurantes);
        }
    }
}
