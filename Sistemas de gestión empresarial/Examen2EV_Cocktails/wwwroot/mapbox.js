

let map; // el mapa debe ser accesible desde varios métodos
let markers = []; //guardamos los marcadores para poder borrarlos

window.initializeMap3 = (token, latitude, longitude, zoom) => {
    mapboxgl.accessToken = token;

    map = new mapboxgl.Map({
        container: 'map',
        style: 'mapbox://styles/mapbox/streets-v12',
        center: [longitude, latitude],
        zoom: zoom
    });

    map.addControl(new mapboxgl.NavigationControl());
};

window.clearMarkers = () => {
    markers.forEach(m => m.remove()); // quita los marcadores del mapa
    markers = []; // vacia el array
};

window.showSingleMarker = (lat, lon, name, category) => {
    window.clearMarkers();

    const marker = new mapboxgl.Marker()
        .setLngLat([lon, lat])
        .setPopup(new mapboxgl.Popup().setHTML(
            "<strong>" + name + "</strong><br/>" + category))
        .addTo(map);

    markers.push(marker);
    map.flyTo({ center: [lon, lat], zoom: 15 });
};

window.showAllMarkers = (restaurantes) => {
    window.clearMarkers();

    restaurantes.forEach(r => {
        const marker = new mapboxgl.Marker()
            .setLngLat([r.longitud, r.latitud])
            .setPopup(new mapboxgl.Popup().setHTML(
                "<strong>" + r.name + "</strong><br/>" + r.category))
            .addTo(map);

        markers.push(marker);
    });
    map.flyTo({ center: [restaurantes[0].longitud, restaurantes[0].latitud], zoom: 12 });

};

