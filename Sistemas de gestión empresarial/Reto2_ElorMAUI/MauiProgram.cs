using ElorMaui.Services;
using Microsoft.Extensions.Logging;
using Microsoft.Maui.Devices;


namespace ElorMAUI
{
    public static class MauiProgram
    {
        public static MauiApp CreateMauiApp()
        {
            var builder = MauiApp.CreateBuilder();
            builder
                .UseMauiApp<App>()
                .ConfigureFonts(fonts =>
                {
                    fonts.AddFont("OpenSans-Regular.ttf", "OpenSansRegular");
                });


			// HttpClient para llamadas al backend (login)
			builder.Services.AddSingleton(new HttpClient
			{
				BaseAddress = new Uri(
		 DeviceInfo.Platform == DevicePlatform.Android
			 ? "http://10.0.2.2:8080/"
			 : "http://localhost:8080/"
	 )
			});

			// Servicios de la app
			builder.Services.AddScoped<AuthService>(); //registro de servicio de autenticación
            builder.Services.AddScoped<CentrosService>(); //registro de servicio de centros
            builder.Services.AddSingleton<CentrosStore>(); //registro

            builder.Services.AddScoped<MeteoService>(); //registro de servicio de meteo

            builder.Services.AddMauiBlazorWebView();

#if DEBUG
    		builder.Services.AddBlazorWebViewDeveloperTools();
    		builder.Logging.AddDebug();
#endif

            return builder.Build();
        }
    }
}
