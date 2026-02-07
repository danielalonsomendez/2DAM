namespace ElorMaui.Models;

public class EuskadiCentrosRoot
{
    public List<Centro> CENTROS { get; set; } = [];
}

public class Centro
{
    public int CCEN { get; set; }

    public string NOM { get; set; } = "";
    public string DMUNIC { get; set; } = "";
    public string DTERRC { get; set; } = "";
    public string DTITUC { get; set; } = "";

    public string DOMI { get; set; } = "";
    public string EMAIL { get; set; } = "";

    public double LATITUD { get; set; }
    public double LONGITUD { get; set; }

    // Constructor vacío (necesario para JSON / Blazor)
    public Centro() { }

    // Constructor copia (CLAVE para editar en el CRUD)
    public Centro(Centro other)
    {
        CCEN = other.CCEN;
        NOM = other.NOM;
        DMUNIC = other.DMUNIC;
        DTERRC = other.DTERRC;
        DTITUC = other.DTITUC;
        DOMI = other.DOMI;
        EMAIL = other.EMAIL;
        LATITUD = other.LATITUD;
        LONGITUD = other.LONGITUD;
    }

    // Normaliza coordenadas (para mapa)
    public void NormalizeCoordinates()
    {
        var lat = LATITUD;
        var lon = LONGITUD;

        // Caso típico del JSON de Euskadi (lat y lon intercambiadas)
        // LAT = -2.x  / LON = 42.x
        if (Math.Abs(lat) <= 10 && lon >= 35 && lon <= 50)
        {
            (lat, lon) = (lon, lat);
        }

        // Euskadi: longitudes negativass
        if (lon > 0 && lon <= 10)
            lon = -lon;

        LATITUD = lat;
        LONGITUD = lon;
    }
}
