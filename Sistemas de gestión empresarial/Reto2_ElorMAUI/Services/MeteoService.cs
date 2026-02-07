using System.Globalization;
using System.Text.Json;
using ElorMaui.Models;

namespace ElorMaui.Services;

public class MeteoService
{
    private readonly HttpClient _http;
    private readonly JsonSerializerOptions _jsonOptions = new()
    {
        PropertyNameCaseInsensitive = true
    };

    public MeteoService(HttpClient http) => _http = http;

    //las llamadas a la API

    // MÉTODO: TIEMPO METEOROLÓGICO
    public async Task<MeteoResponse?> GetWeatherAsync(double lat, double lon)
    {
        // Guarda la cultura actual del sistema
        var oldCulture = CultureInfo.DefaultThreadCurrentCulture;

        // Fuerza cultura en-US para que los decimales usen punto (.)
        CultureInfo.DefaultThreadCurrentCulture = new CultureInfo("en-US");

        try
        {// Construcción de la URL de la API Open-Meteo (forecast)
            var url =
                "https://api.open-meteo.com/v1/forecast" +
                $"?latitude={lat}&longitude={lon}" + //coordenadas
                "&current=temperature_2m,relative_humidity_2m,weather_code,is_day" + //Datos actuales
                "&daily=weather_code,temperature_2m_max,temperature_2m_min" + //Prevision diaria
                "&timezone=Europe%2FMadrid"; //zona horaria

            // Llamada HTTP GET → obtiene el JSON como string
            var json = await _http.GetStringAsync(url);

            // Convierte el JSON en un objeto MeteoResponse
            return JsonSerializer.Deserialize<MeteoResponse>(json, _jsonOptions);
        }
        // Restaura la cultura original
        finally { CultureInfo.DefaultThreadCurrentCulture = oldCulture; }
    }

    // MÉTODO: CALIDAD DEL AIRE
    public async Task<AirQualityResponse?> GetAirQualityAsync(double lat, double lon)
    {
        // Guarda la cultura actual
        var oldCulture = CultureInfo.DefaultThreadCurrentCulture;
        // Fuerza cultura con punto decimal
        CultureInfo.DefaultThreadCurrentCulture = new CultureInfo("en-US");

        try
        {// Construcción de la URL de la API de calidad del aire
            var url =
                "https://air-quality-api.open-meteo.com/v1/air-quality" +
                $"?latitude={lat}&longitude={lon}" +
                "&current=carbon_monoxide,nitrogen_dioxide,sulphur_dioxide,ozone,dust,uv_index" +
                "&timezone=Europe%2FMadrid";

            // Llamada HTTP GET
            var json = await _http.GetStringAsync(url);

            // Convierte el JSON en un objeto AirQualityResponse
            return JsonSerializer.Deserialize<AirQualityResponse>(json, _jsonOptions);
        }
        // Restaura la cultura original
        finally { CultureInfo.DefaultThreadCurrentCulture = oldCulture; }
    }
}
