using System.Text.Json;
using ElorMaui.Models;

namespace ElorMaui.Services;

public class CentrosService
{
    public async Task<List<Centro>> GetCentrosAsync()
    {
        //para abrir el archivo json
        using var stream = await FileSystem.OpenAppPackageFileAsync("EuskadiLatLon.json");

        using var reader = new StreamReader(stream);
        var json = await reader.ReadToEndAsync();

        var root = JsonSerializer.Deserialize<EuskadiCentrosRoot>(json);

        return root?.CENTROS ?? [];
    }
}
