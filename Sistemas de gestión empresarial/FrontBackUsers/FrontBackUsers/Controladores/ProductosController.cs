using FrontBackUsers.Client.Modelos;
using Microsoft.AspNetCore.Mvc;
using System.Text.Json;

namespace FrontBackUsers.Controladores
{
    [ApiController]
    [Route("api/[controller]")]
    public class ProductosController : Controller
    {

        private readonly IWebHostEnvironment _env;

        public ProductosController(IWebHostEnvironment env)
        {
            _env = env;
        }

        [HttpGet]
        public async Task<ActionResult<IEnumerable<Producto>>> Get()
        {
            var filePath = Path.Combine(_env.ContentRootPath, "Datos", "Productos.json");

            if (!System.IO.File.Exists(filePath))
                return NotFound("El fichero JSON no existe");

            var json = await System.IO.File.ReadAllTextAsync(filePath);
            var productos = JsonSerializer.Deserialize<List<Producto>>(json);

            return Ok(productos);
        }
    }
}
