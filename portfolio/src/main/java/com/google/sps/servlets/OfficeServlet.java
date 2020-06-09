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

import com.google.sps.data.OfficeGame;
import com.google.gson.Gson;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Servlet that encapsulates the Office game */
@WebServlet("/office-game")
public final class OfficeServlet extends HttpServlet {
  private OfficeGame game = new OfficeGame();

  private int state = 0;

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setContentType("application/json");
    // state 0 signifies they submit their answer, state 1 signifies saying whether they won
    if(state == 0){
      game.newChoice();
      String json = "{ \"state\" : " + state + ",  \"quote\" : \"" + game.getCurrent() + "\" } ";
      response.getWriter().println(json);
    }
    else {
      String json = "{ \"state\" : " + state + ", \"correct\" : " + game.getSubmission() +
      	", \"answer\" : \"" + game.getAnswer() + "\"}";
      response.getWriter().println(json);
      state = 0;
    }
  }

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    // they submit their answer and the game switches to state 1
    String playerSubmission = request.getParameter("player-submission");
    game.submitAnswer(playerSubmission);
    state = 1;
    response.sendRedirect("/theoffice/theofficeresult.html");
  }
}