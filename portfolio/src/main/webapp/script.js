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

/**
 * Fetches the data from the DataServlet and displays.
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
