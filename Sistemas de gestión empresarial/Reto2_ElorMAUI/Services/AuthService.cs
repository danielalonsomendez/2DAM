using System.Text.Json;
using Microsoft.Maui.Storage;
using ElorMaui.Models;

namespace ElorMaui.Services;

public class AuthService
{
	private readonly HttpClient _http; //se usa para llamadas al backend (/login)

    //permite deserializar JSON ignorando mayúsculas/minúsculas en nombres de propiedades
    private readonly JsonSerializerOptions _jsonOptions = new()
	{
		PropertyNameCaseInsensitive = true
	};

    // Claves para SecureStorage (almacenamiento seguro)
    private const string KeyLogged = "elor_logged";
	private const string KeyUserJson = "elor_user";

	// Evento para avisar a la UI (MainLayout/NavMenu) cuando cambia el login
	public event Action? AuthStateChanged;

	//para que cualquier componente se actualice a tiempo real
	private void NotifyAuthChanged() => AuthStateChanged?.Invoke();

    //constructor que recibe HttpClient inyectado
    public AuthService(HttpClient http)
	{
		_http = http;
	}

	public async Task<(bool ok, string? error)> LoginAsync(string username, string contrasena)
	{
        //limpieza de inputs
        username = (username ?? "").Trim();
		contrasena = (contrasena ?? "").Trim();

        //validación basica
        if (string.IsNullOrWhiteSpace(username) || string.IsNullOrWhiteSpace(contrasena))
			return (false, "Rellena usuario y contraseña.");

        //construcción de URL 
        var url = $"login?username={Uri.EscapeDataString(username)}&contrasena={Uri.EscapeDataString(contrasena)}";

		HttpResponseMessage resp;
		try
		{
			resp = await _http.PostAsync(url, content: null); //envío de petición POST
        }
		catch (Exception ex)
		{
			return (false, "No se pudo conectar con el servidor: " + ex.Message);
		}

		var body = await resp.Content.ReadAsStringAsync(); //lectura de cuerpo de respuesta

        if (!resp.IsSuccessStatusCode)
		{
			if ((int)resp.StatusCode == 401) return (false, "Usuario o contraseña incorrectos.");
			if ((int)resp.StatusCode == 400) return (false, body);
			return (false, $"Error {(int)resp.StatusCode}: {body}");
		}

		var user = JsonSerializer.Deserialize<User>(body, _jsonOptions);
		if (user is null) return (false, "Respuesta de login inválida.");
			
		await SecureStorage.SetAsync(KeyLogged, "true");
		await SecureStorage.SetAsync(KeyUserJson, body);

		NotifyAuthChanged(); //AVISA AL LAYOUT/MENU

		return (true, null);
	}

    //para comprobar si hay usuario logueado
    public async Task<bool> IsLoggedAsync()
		=> (await SecureStorage.GetAsync(KeyLogged)) == "true";

    //para obtener el usuario logueado
    public async Task<User?> GetLoggedUserAsync()
	{
		var json = await SecureStorage.GetAsync(KeyUserJson);
		if (string.IsNullOrWhiteSpace(json)) return null;
		return JsonSerializer.Deserialize<User>(json, _jsonOptions);
	}

    //método asíncrono para cerrar sesión
    public async Task LogoutAsync()
	{
		SecureStorage.Remove(KeyLogged);
		SecureStorage.Remove(KeyUserJson);

		NotifyAuthChanged(); 
		await Task.CompletedTask;
	}

	public void Logout()
	{
		SecureStorage.Remove(KeyLogged);
		SecureStorage.Remove(KeyUserJson);

		NotifyAuthChanged(); 
	}
}
