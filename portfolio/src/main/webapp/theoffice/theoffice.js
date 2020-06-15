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

/**
 * Fetches the current state of the game and builds the UI
 */
function getOfficeGame() {/* exported getOfficeGame */
  fetch('/office-game').then((response) => response.json()).then((game) => {
    const state = game.state;
    // state GAME signifies submission
    // state RESULTS signifies saying whether they got it right
    if (state == 'GAME') {
      document.getElementById('quote').innerText = game.quote;
    } else {
      const correct = game.correct;
      if (correct) {
        document.getElementById('result').innerText = 'Correct!';
      } else {
        document.getElementById('result').innerText = 'False!';
        document.getElementById('answer').innerText =
            'Correct answer: ' + game.answer;
      }
    }
    document.getElementById('score').innerText = 'Score: ' + game.score;
  });
}
