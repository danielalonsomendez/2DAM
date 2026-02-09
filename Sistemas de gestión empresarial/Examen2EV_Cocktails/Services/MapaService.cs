using danielAlonso2Eval.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Text.Json;
using System.Threading.Tasks;

namespace danielAlonso2Eval.Services;
public class MapaService
{
    private readonly JsonSerializerOptions _jsonOptions = new()
    {
        PropertyNameCaseInsensitive = true
    };

    public async Task<List<MapaCocktail>?> GetMapaCocktailAsync()
    {
        try
        {
            // Primero intentamos leer desde AppDataDirectory (si ya existe)
            var path = Path.Combine(FileSystem.AppDataDirectory, "mapa.json");
            if (File.Exists(path))
            {
                var json = await File.ReadAllTextAsync(path);
                return JsonSerializer.Deserialize<List<MapaCocktail>>(json) ?? new List<MapaCocktail>();
            }
            else
            {
                // Si no existe, cargamos el archivo inicial desde Resources/raw
                using var stream = await FileSystem.OpenAppPackageFileAsync("mapa.json");
                using var reader = new StreamReader(stream);
                var json = reader.ReadToEnd();
               return JsonSerializer.Deserialize<List<MapaCocktail>>(json) ?? new List<MapaCocktail>();
            }
        }
        catch (Exception ex)
        {
            return new List<MapaCocktail>();
        }

    }
}
