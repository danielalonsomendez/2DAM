using System.Text.Json.Serialization;

namespace ElorMaui.Models;

public class MeteoResponse
{
    [JsonPropertyName("current")]
    public MeteoCurrent? Current { get; set; }

    [JsonPropertyName("daily")]
    public MeteoDaily? Daily { get; set; }
}

public class MeteoCurrent
{
    [JsonPropertyName("temperature_2m")]
    public double Temperature2m { get; set; }

    [JsonPropertyName("relative_humidity_2m")]
    public double RelativeHumidity2m { get; set; }

    [JsonPropertyName("weather_code")]
    public int WeatherCode { get; set; }

    [JsonPropertyName("is_day")]
    public int IsDay { get; set; } // 1 día, 0 noche

    [JsonPropertyName("time")]
    public string Time { get; set; } = "";

}

public class MeteoDaily
{
    [JsonPropertyName("time")]
    public string[] Time { get; set; } = [];

    [JsonPropertyName("temperature_2m_min")]
    public double[] TemperatureMin { get; set; } = [];

    [JsonPropertyName("temperature_2m_max")]
    public double[] TemperatureMax { get; set; } = [];

    [JsonPropertyName("weather_code")]
    public int[] WeatherCode { get; set; } = [];
}
