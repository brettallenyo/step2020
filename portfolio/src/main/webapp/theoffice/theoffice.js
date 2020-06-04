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
 * Adds a random greeting to the page.
 */
function addRandomGreeting() {/* exported addRandomGreeting */
  const greetings = [
    'I\'m Prison Mike!',
    'Sometimes I\'ll start a sentence and I don\'t even know where it\'s going'+
        '. I just hope I find it along the way.',
    'I want people to be afraid of how much they love me.',
    'I\'m not superstitious, but I am a little stitious.',
    'Bears, beets, Battlestar Galactica', 'I declare BANKRUPTCY!',
    'Did I stutter?', 'I talk a lot, so I\'ve learned to tune myself out.',
    'Me think, why waste time say lot word, when few word do trick.',
    'Dwight mercy-killed Angela\'s cat.',
    'I got six numbers, one more and it would have been a complete phone ' +
        'number.',
    'And the worst thing about prison was the dementors.',
    'Identity theft is not a joke, Jim! Millions of families suffer every ' +
        'year.',
    'Bonfire, James Bond-fire. Michael Scarn!',
    'Today, smoking is going to save lives.', 'Ryan started the fire!',
    'Fool me once, strike one. Fool me twice, strike three.',
    'Well, well, well, how the turntables...'];

  // Pick a random greeting.
  const greeting = greetings[Math.floor(Math.random() * greetings.length)];

  // Add it to the page.
  const greetingContainer = document.getElementById('greeting-container');
  greetingContainer.innerText = greeting;
}
