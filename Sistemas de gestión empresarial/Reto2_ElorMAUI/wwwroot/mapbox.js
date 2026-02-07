// Variable global donde guardamos la instancia del mapa (Mapbox)
let map;

// Array donde guardamos TODOS los marcadores actuales para poder borrarlos luego
let markers = [];

// Referencia a un objeto .NET (Blazor) para poder llamar métodos C# desde JS
let dotnetRef = null;

// Función global: inicializa el mapa (se llama desde C# con JS.InvokeVoidAsync)
window.initializeCentrosMap = (token, dotnetHelper) => {
    // Asignamos el token de Mapbox (necesario para cargar el mapa)
    mapboxgl.accessToken = token;

    // Guardamos el “helper” de .NET para poder hacer invokeMethodAsync luego
    dotnetRef = dotnetHelper;

    // Creamos la instancia del mapa
    map = new mapboxgl.Map({
        container: "map",                         // id del div donde se dibuja el mapa
        style: "mapbox://styles/mapbox/streets-v12", // estilo visual del mapa
        center: [-2.6, 43.0],                     // centro inicial [longitud, latitud] (ojo el orden)
        zoom: 8                                   // zoom inicial
    });

    // Añade controles de navegación (zoom +, -, brújula)
    map.addControl(new mapboxgl.NavigationControl());
};

// Función global: coloca marcadores en el mapa a partir de una lista de centros
window.setCentrosMarkers = (centros) => {
    // Si el mapa aún no está inicializado, no hacemos nada
    if (!map) return;

    // Log para depuración: cuántos centros llegan desde C#
    console.log("setCentrosMarkers -> recibidos:", centros?.length ?? 0);

    // 1) Limpiar marcadores anteriores del mapa
    markers.forEach(m => m.remove());  // elimina cada marker del mapa
    markers = [];                      // reinicia el array de markers

    // 2) bounds sirve para encuadrar todos los puntos al final (fitBounds)
    let bounds = new mapboxgl.LngLatBounds();

    // Flag para saber si hemos añadido al menos 1 marcador válido
    let any = false;

    // Recorremos cada centro recibido
    centros.forEach(c => {
        // Intentamos leer lat/lon en dos formatos:
        // - camelCase (latitud/longitud)
        // - mayúsculas (LATITUD/LONGITUD) por si viene de otro modelo
        const latRaw = c.latitud ?? c.LATITUD;
        const lonRaw = c.longitud ?? c.LONGITUD;

        // Si falta alguno, saltamos este centro
        if (latRaw == null || lonRaw == null) return;

        // Convertimos a número:
        // - reemplazamos coma por punto por si viene "43,12"
        let lat = Number(String(latRaw).replace(",", "."));
        let lon = Number(String(lonRaw).replace(",", "."));

        // Si no son números válidos, saltamos
        if (!isFinite(lat) || !isFinite(lon)) return;

        // “Swap típico” si vienen invertidos:
        // Si lat parece muy pequeña (<=10) y lon parece una latitud de España (35-50),
        // intercambiamos para corregir el orden.
        if (Math.abs(lat) <= 10 && lon > 35 && lon < 50) {
            const tmp = lat;
            lat = lon;
            lon = tmp;
        }

        // Validación de rango mundial: lat [-90..90], lon [-180..180]
        if (lat < -90 || lat > 90 || lon < -180 || lon > 180) return;

        // Creamos marcador rojo
        const marker = new mapboxgl.Marker({ color: "red" })
            .setLngLat([lon, lat]) // Mapbox siempre usa [longitud, latitud]
            .addTo(map);           // lo añade al mapa

        // Evento click del marcador
        marker.getElement().addEventListener("click", () => {
            // 1) mueve la cámara al marcador y hace zoom
            map.flyTo({ center: [lon, lat], zoom: 14 });

            // 2) avisa a C# qué centro se ha seleccionado
            if (dotnetRef) {
                // Llama a un método C# marcado como [JSInvokable] llamado "OnCentroSelected"
                // y le pasa el objeto centro "c"
                dotnetRef.invokeMethodAsync("OnCentroSelected", c);
            }
        });

        // Guardamos el marker en el array para poder limpiarlo luego
        markers.push(marker);

        // Añadimos este punto al “bounds” para encuadrar todos los marcadores
        bounds.extend([lon, lat]);

        // Ya tenemos al menos un marcador
        any = true;
    });

    // Si añadimos alguno, ajustamos el mapa para que se vean todos
    if (any) {
        map.fitBounds(bounds, { padding: 80 }); // margen alrededor
    }
};

// Función global: borra todos los marcadores actuales
window.clearCentrosMarkers = () => {
    // Si el mapa no existe, no hacemos nada
    if (!map) return;

    // Quitamos cada marcador del mapa
    markers.forEach(m => m.remove());

    // Vaciamos el array
    markers = [];
};