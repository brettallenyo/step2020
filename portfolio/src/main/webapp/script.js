// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

/** Creates a map and adds it to the page. */
var map;
function initMap() {
  map = new google.maps.Map(
      document.getElementById('map'),
      {center: {lat: 42.357177, lng: -71.092641}, zoom: 15});
  addLandmark(
      map, 42.359725, -71.092144, 'The Big Dome',
      'This is the famous Big Dome.');
  addLandmark(
      map, 42.350698, -71.090884, 'Theta Tau',
      'Theta Tau Professional Engineering Fraternity, where Brett lives.');
  addLandmark(
      map, 42.359102, -71.095976, 'Rockwell Cage',
      'Rockwell Cage in the Zesiger Fitness Center, where Brett plays volleyball.');
}

/** Adds a marker that shows an info window when clicked. */
function addLandmark(map, lat, lng, title, description) {
  const marker = new google.maps.Marker(
      {position: {lat: lat, lng: lng}, map: map, title: title});

  const infoWindow = new google.maps.InfoWindow({content: description});
  marker.addListener('click', () => {
    infoWindow.open(map, marker);
  });
}
