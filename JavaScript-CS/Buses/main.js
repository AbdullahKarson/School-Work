(function () {

    //geoCollection
    let geojsonCollection = {
        "type": "FeatureCollection",
        "features": []
    };

    //create bus icon
    var busIcon = L.Icon.extend({
        iconUrl: 'bus.png',
        iconSize: [30, 30]
    })

    //create map in leaflet and tie it to the div called 'theMap'
    var map = L.map('theMap').setView([44.650627, -63.597140], 12);

    //map Info
    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
        attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
    }).addTo(map);

    //json data
    function busses() {
        //fetch bus api information
        fetch('https://hrmbusapi.herokuapp.com/')
            .then(response => response.json())
            .then(function (data) {

                // console.log(data.entity)

                //for each entry
                data.entity.forEach(function (value) {
                    //busses with route 1 to 10
                    if (value.vehicle.trip.routeId <= 10 && value.vehicle.trip.routeId >= 1) {
                        //positions
                        let latitude = value.vehicle.position.latitude.toFixed(5);
                        let longitude = value.vehicle.position.longitude.toFixed(5);
                        let rotation = value.vehicle.position.bearing;
                        latitude = parseFloat(latitude);
                        longitude = parseFloat(longitude);
                        rotation = parseInt(rotation);

                        //create GeoJson data
                        var geojson = {
                            "type": "Feature",
                            "geometry": {
                                "type": "Point",
                                "coordinates": [longitude, latitude],
                                "rotationAngle" : rotation
                            },
                            "properties": {
                                "routeID": value.vehicle.trip.routeId,
                                "busNum": value.id
                            }
                        };

                        //Push to geoCollection
                        geojsonCollection.features.push(geojson);
                    }
                })
            });
    }

    //create and push bus geoJsons to FeatureCollection
    busses();

    // console.log(geojsonCollection)
    //Update the Map with Busses
    setTimeout(update, 500);


    function update(){
        //create geoJson layer
        // console.log(geojsonCollection)


        var layers = L.geoJSON(geojsonCollection, {
            pointToLayer: function (feature, latLng) {
                return new L.Marker(latLng, {
                    //busIcons for each bus
                    icon: new busIcon({
                        iconUrl: 'bus.png',
                        iconSize: [30, 30]
                    }),
                    //rotation
                    rotationAngle : feature.geometry.rotationAngle
                })
            },
            //for each bus a description
            onEachFeature: function (feature, layer) {
                layer.bindPopup('<h4>Bus Nr. :' + feature.properties.busNum
                    + '<br>Route Nr. : ' + feature.properties.routeID + "</h4>");
            }
            //add to map 
        }).addTo(map)

        //clear Map after 7.1 Second
        setInterval(function(){
            layers.clearLayers()
        },7100)
    }   

    setInterval(function(){

        //clear geoCollection
        geojsonCollection = {
            "type": "FeatureCollection",
            "features": []
        };

        //repopulate geoCollection
        busses()
        
        //update map again after 7 Sec
        setTimeout(update, 500);
    }, 7000);
})()