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

google.charts.load('current', {'packages':['corechart']});
google.charts.setOnLoadCallback(drawChart);

/** Creates a map and adds it to the page. */
var map;
function initMap() {
  drawChart();
  map = new google.maps.Map(
      document.getElementById('map'),
      {center: {lat: 42.357177, lng: -71.092641}, zoom: 15});
  addLandmark(
      map, 42.359725, -71.092144, 'The Big Dome',
      'The Big Dome, visible from his room across the river, its magnificence brings Brett joy.');
  addLandmark(
      map, 42.350698, -71.090884, 'Theta Tau',
      'Theta Tau Professional Engineering Fraternity, where Brett lives.');
  addLandmark(
      map, 42.359102, -71.095976, 'Rockwell Cage',
      'Rockwell Cage in the Zesiger Fitness Center, where Brett plays volleyball.');
  addLandmark(
      map, 42.363106, -71.087904, 'CAVA',
      'CAVA, Brett\'s favorite place to eat.');
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

/**
 * Draws the pie chart.
 */
function drawChart() {/* exported drawChart */
  const data = new google.visualization.DataTable();
  data.addColumn('string', 'Activity');
  data.addColumn('number', 'Hours');
  data.addRows([
    ['Volleyball', 2],
    ['Lifting Weights', 1],
    ['In Meetings', 2],
    ['Debugging', 5],
    ['Staring at his computer, helpless', 0.5],
    ['Re-running the same code hoping it will work that time', 0.5],
    ['Actually coding new stuff', 1],
    ['Dinner with the family', 2],
    ['Trying to decide what to watch', 2],
    ['Actually watching something', 1],
    ['Sleeping', 7]
  ]);

  const options = {
    'title': 'How Brett Spends His Day',
    'width':600,
    'height':700
  };

  const chart = new google.visualization.PieChart(
      document.getElementById('chart-container'));
  chart.draw(data, options);
}
