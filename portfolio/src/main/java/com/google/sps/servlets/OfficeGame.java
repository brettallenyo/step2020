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

package com.google.sps.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Collections;
import java.util.Map;

/**
 * Class representing the Office game, where players are instructed to finish a prompted quote.
 *
 */
public class OfficeGame {
  /** Map of values from prompts to answers */
  private final Map<String, String> values = Collections.unmodifiableMap(new HashMap<String, String>(){{
    put("I'm Prison ", "Mike");
    put("Sometimes I\'ll start a sentence and I don\'t even know where it\'s going"
            + ". ",
        "I just hope I find it along the way");
    put("I want people to afraid of ", "how much they love me");
    put("I\'m not superstitious, ", "but I am a little stitious");
    put("Bears, beets, ", "Battlestar Galactica");
    put("I declare ", "bankruptcy");
    put("Did I ", "stutter");
    put("I talk a lot, so I\'ve learned ", "to tune myself out");
    put("Me think, why waste time say lot word, ", "when few word do trick");
    put("Dwight mercy-killed ", "Angela\'s cat");
    put("I got six numbers, one more and it would have been ", "a complete phone number");
    put("And the worst thing about prison ", "was the dementors");
    put("Identity theft is not a joke, Jim! ", "Millions of families suffer every year");
    put("Bonfire, James Bond-fire. ", "Michael Scarn");
    put("Today, smoking is going to ", "save lives");
    put("Ryan started ", "the fire");
    put("Fool me once, strike one. Fool me twice, ", "strike three");
    put("Well, well, well, ", "how the turntables");
  }});

  /** The current prompt to answer */
  private String currentPrompt = "I'm Prison ";

  /** Is true or false based on whether the user got the last question right */
  private boolean lastSubmission = true;

  private int score= 0;

  public String getCurrentPrompt() {
    return currentPrompt;
  }

  public String getAnswer() {
    return values.get(currentPrompt);
  }

  /**
   * "Submits" the answer by checking if it is equal to the answer and assigning
   * that to lastSubmission variable
   */
  public void submitAnswer(String guess) {
    lastSubmission = values.get(currentPrompt).equals(guess);
    if(lastSubmission){
        score += 1;
    } else {
        score = 0;
    }
  }

  public boolean getWinStatus() {
    return lastSubmission;
  }

  public int getScore() {
    return score;
  }

  /** Picks a new prompt at random to assign to the current prompt */
  public void newChoice() {
    int rand = (int) (Math.random() * values.size());
    int index = 0;
    for (String i : values.keySet()) {
      if (index == rand) {
        currentPrompt = i;
        break;
      }
      index++;
    }
  }
}
