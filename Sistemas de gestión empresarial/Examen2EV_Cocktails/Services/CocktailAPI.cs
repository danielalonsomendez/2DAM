using danielAlonso2Eval.Models;
using System;
using System.Collections.Generic;
using System.Globalization;
using System.Linq;
using System.Text;
using System.Text.Json;
using System.Threading.Tasks;

namespace danielAlonso2Eval.Services;
public class CocktailAPI
{
    private readonly HttpClient _http;
    private readonly JsonSerializerOptions _jsonOptions = new()
    {
        PropertyNameCaseInsensitive = true
    };

    public CocktailAPI(HttpClient http) => _http = http;

    //las llamadas a la API

    // MÉTODO: TIEMPO METEOROLÓGICO
    public async Task<CockatilResponse?> GetCocktailsNameAsync(String name)
    {
            var url =
                "https://www.thecocktaildb.com/api/json/v1/1/search.php?s="+name; 

            var json = await _http.GetStringAsync(url);



        CockatilResponse? respuesta = JsonSerializer.Deserialize<CockatilResponse>(json, _jsonOptions);
        if (respuesta != null && respuesta.drinks != null && respuesta.drinks.Length != 0) {
            foreach (var producto in respuesta.drinks)
            {
                if (producto.dateModified != null)
                {
                    producto.dateYear = DateTime.Parse(@producto.dateModified).Year;
                }
            }
        }
        return respuesta;

    }
    public async Task<CockatilResponse?> GetCocktailsIDAsync(int id)
    {
        var url =
            "https://www.thecocktaildb.com/api/json/v1/1/lookup.php?i=" + id;

        var json = await _http.GetStringAsync(url);



        CockatilResponse? respuesta = JsonSerializer.Deserialize<CockatilResponse>(json, _jsonOptions);
        if (respuesta != null && respuesta.drinks != null && respuesta.drinks.Length != 0)
        {
            foreach (var producto in respuesta.drinks)
            {
                if (producto.dateModified != null)
                {
                    producto.dateYear = DateTime.Parse(@producto.dateModified).Year;
                }
            }
        }
        return respuesta;

    }

    public async Task<CockatilResponse?> GetCocktailsFilterAsync(string name)
    {
        var url =
            "https://www.thecocktaildb.com/api/json/v1/1/filter.php?a=" + name;

        var json = await _http.GetStringAsync(url);



        CockatilResponse? respuesta = JsonSerializer.Deserialize<CockatilResponse>(json, _jsonOptions);
        if (respuesta != null && respuesta.drinks != null && respuesta.drinks.Length != 0)
        {
            foreach (var producto in respuesta.drinks)
            {
                if (producto.dateModified != null)
                {
                    producto.dateYear = DateTime.Parse(@producto.dateModified).Year;
                }
            }
        }
        return respuesta;

    }



}
