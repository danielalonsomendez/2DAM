using ElorMaui.Models;

namespace ElorMaui.Services;

public class CentrosStore
{
    private readonly CentrosService _centrosService;

    public CentrosStore(CentrosService centrosService)
    {
        _centrosService = centrosService;
    }

    public bool IsLoaded { get; private set; }
    public List<Centro> Centros { get; private set; } = new();

    // Evento para avisar a Mapa y Centros
    public event Action? OnChange;

    public async Task EnsureLoadedAsync()
    {
        if (IsLoaded) return;

        Centros = await _centrosService.GetCentrosAsync();
        IsLoaded = true;

        OnChange?.Invoke();
    }

    // CREATE
    public async Task AddAsync(Centro nuevo)
    {
        // Si más adelante guardas en API/DB:
        // await _centrosService.AddCentroAsync(nuevo);

        Centros.Add(nuevo);
        OnChange?.Invoke();
        await Task.CompletedTask;
    }

    // UPDATE
    public async Task UpdateAsync(Centro editado)
    {
        var idx = Centros.FindIndex(c => c.CCEN == editado.CCEN);
        if (idx >= 0)
        {
            Centros[idx] = editado;
            OnChange?.Invoke();
        }

        await Task.CompletedTask;
    }

    // DELETE
    public async Task DeleteAsync(int ccen)
    {
        Centros.RemoveAll(c => c.CCEN == ccen);
        OnChange?.Invoke();
        await Task.CompletedTask;
    }
}
