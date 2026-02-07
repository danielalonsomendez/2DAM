using System.Text.Json.Serialization;

namespace ElorMaui.Models;

public class AirQualityResponse
{
    [JsonPropertyName("current")]
    public AirCurrent? Current { get; set; }

    [JsonPropertyName("current_units")]
    public AirCurrentUnits? CurrentUnits { get; set; }
}

public class AirCurrent
{
    [JsonPropertyName("carbon_monoxide")]
    public double CarbonMonoxide { get; set; }

    [JsonPropertyName("nitrogen_dioxide")]
    public double NitrogenDioxide { get; set; }

    [JsonPropertyName("sulphur_dioxide")]
    public double SulphurDioxide { get; set; }

    [JsonPropertyName("ozone")]
    public double Ozone { get; set; }

    [JsonPropertyName("dust")]
    public double Dust { get; set; }

    [JsonPropertyName("uv_index")]
    public double UvIndex { get; set; }
}

public class AirCurrentUnits
{
    [JsonPropertyName("carbon_monoxide")]
    public string CarbonMonoxide { get; set; } = "";

    [JsonPropertyName("nitrogen_dioxide")]
    public string NitrogenDioxide { get; set; } = "";

    [JsonPropertyName("sulphur_dioxide")]
    public string SulphurDioxide { get; set; } = "";

    [JsonPropertyName("ozone")]
    public string Ozone { get; set; } = "";

    [JsonPropertyName("dust")]
    public string Dust { get; set; } = "";

    [JsonPropertyName("uv_index")]
    public string UvIndex { get; set; } = "";
}
