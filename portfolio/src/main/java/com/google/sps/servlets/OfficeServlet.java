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

package com.google.sps.servlets;

import com.google.gson.Gson;
import com.google.sps.data.OfficeGame;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

enum State {
  GAME,
  RESULTS
}


/** Servlet that encapsulates the Office game */
@WebServlet("/office-game")
public final class OfficeServlet extends HttpServlet {

  private class GameJSON {
    State state;
    String quote;
    boolean correct;
    String answer;
    int score;
  }

  private OfficeGame game = new OfficeGame();

  private State currentState = State.GAME;

  /**
   * Get requests are processed here and return the JSON required based on the current state
   * GAME: return the quote
   * RESULTS: return whether they got it right as well as the correct answer
   */
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setContentType("application/json");
    // state GAME signifies they submit their answer, state RESULTS signifies saying whether they won
    GameJSON json = new GameJSON();
    switch(currentState){
      case GAME:
        game.newChoice();
        json.state = currentState;
        json.quote = game.getCurrentPrompt();
        json.score = game.getScore();
        response.getWriter().println(getJSON(json));
        break;
      case RESULTS:
        json.state = currentState;
        json.correct = game.getWinStatus();
        json.answer = game.getAnswer();
        json.score = game.getScore();
        response.getWriter().println(getJSON(json));
        currentState = State.GAME;
        break;
    }
  }

  /**
   * Gets user answer and submits it to the game. Transitions to the RESULTS state
   * Redirects user to results page
   */
  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    // they submit their answer and the game switches to State RESULTS
    String playerSubmission = request.getParameter("player-submission");
    game.submitAnswer(playerSubmission);
    currentState = State.RESULTS;
    response.sendRedirect("/theoffice/theofficeresult.html");
  }

  private String getJSON(GameJSON gameDetails) {
    Gson gson = new Gson();
    String json = gson.toJson(gameDetails);
    return json;
  }

}