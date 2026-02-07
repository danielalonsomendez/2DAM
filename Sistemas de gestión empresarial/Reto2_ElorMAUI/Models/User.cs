namespace ElorMaui.Models;

public class User
{
    public int Id { get; set; }
    public Tipos? Tipos { get; set; }

    public string? Email { get; set; }
    public string? Username { get; set; }
    public string? Password { get; set; }

    public string? Nombre { get; set; }
    public string? Apellidos { get; set; }
    public string? Dni { get; set; }
    public string? Direccion { get; set; }
    public string? Telefono1 { get; set; }
    public string? Telefono2 { get; set; }

    public string? ArgazkiaUrl { get; set; }

    public DateTime? CreatedAt { get; set; }
    public DateTime? UpdatedAt { get; set; }
}


public class Tipos
{
    public int Id { get; set; }
    public string? Nombre { get; set; }
    public string? Name { get; set; }
    public string? NameEu { get; set; }
}
