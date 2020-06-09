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
import java.util.List;
import java.util.HashMap;

/**
 * Class representing the Office game, where players are instructed to finish a prompted quote.
 *
 */
public class OfficeGame {

  /** Map of values from prompts to answers */
  private final HashMap<String, String> values = new HashMap<String, String>();

  /** The current prompt to answer */
  private String current = "I'm Prison ";

  /** Is true or false based on whether the user got the last question right */
  private boolean lastSubmission = true;

  /** Constructor adds all the quotes to the HashMap */
  public OfficeGame(){
    values.put("I'm Prison ", "Mike");
    values.put("Sometimes I\'ll start a sentence and I don\'t even know where it\'s going"+
        ". ", "I just hope I find it along the way");
    values.put("I want people to afraid of ", "how much they love me");
    values.put("I\'m not superstitious, ", "but I am a little stitious");
    values.put("Bears, beets, ", "Battlestar Galactica");
    values.put("I declare ", "bankruptcy");
    values.put("Did I ", "stutter");
	values.put("I talk a lot, so I\'ve learned ", "to tune myself out");
    values.put("Me think, why waste time say lot word, ", "when few word do trick");
    values.put("Dwight mercy-killed ", "Angela\'s cat");
    values.put("I got six numbers, one more and it would have been ", 
        "a complete phone number");
    values.put("And the worst thing about prison ", "was the dementors");
    values.put("Identity theft is not a joke, Jim! ", "Millions of families suffer every year");
    values.put("Bonfire, James Bond-fire. ", "Michael Scarn");
    values.put("Today, smoking is going to ", "save lives");
    values.put("Ryan started ", "the fire");
    values.put("Fool me once, strike one. Fool me twice, ", "strike three");
    values.put("Well, well, well, ", "how the turntables");
  }

  public String getCurrent(){
    return current;
  }

  public String getAnswer(){
    return values.get(current);
  }

  /** "Submits" the answer by checking if it is equal to the answer and assigning
    * that to lastSubmission variable */
  public void submitAnswer(String guess){
    lastSubmission = values.get(current).equals(guess);
  }

  public boolean getSubmission(){
    return lastSubmission;
  }

  /** Picks a new prompt at random to assign to the current prompt */
  public void newChoice(){
    int rand = (int) (Math.random()*values.size());
    int index = 0;
    for (String i: values.keySet()){
      if(index == rand){
          current = i;
          break;
      }
      index++;
    }
  }


}