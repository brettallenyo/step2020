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

package com.google.sps;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public final class FindMeetingQuery {
  /**
   *  Solves for the possible time ranges available to schedule a meeting
   *  with the given attendees and current events going on.
   *
   *  @param events Collection of events that are going on during that day
   *  @param request A requested meeting with certain attendees
   *  
   *  @return A collection of time ranges possible to schedule
   */
  public Collection<TimeRange> query(Collection<Event> events, MeetingRequest request) {
    List<TimeRange> eventRanges = sortedRangesInQuestion(events, request.getAttendees());
    List<TimeRange> possibleRanges = new ArrayList<TimeRange>();
    if (eventRanges.size() >= 1){
      TimeRange startOfDay = TimeRange.fromStartEnd(TimeRange.START_OF_DAY, eventRanges.get(0).start(), false);
      if (startOfDay.duration() >= request.getDuration()){
        possibleRanges.add(startOfDay);
      }
    }
    int startingPointer = 0;
    for (int endingPointer = 1; endingPointer < eventRanges.size(); endingPointer++) {
      if (eventRanges.get(startingPointer).overlaps(eventRanges.get(endingPointer))) {
        startingPointer = endingPointer;
      }
      else {
        TimeRange between = TimeRange.fromStartEnd(eventRanges.get(startingPointer).end(),
            eventRanges.get(endingPointer).start(), false);
        if (between.duration() >= request.getDuration()){
          possibleRanges.add(between);
        }
        startingPointer++;
      }
    }
    if (eventRanges.size() >= 1){
      TimeRange latestEnd = eventRanges.get(0);
      for (TimeRange time : eventRanges){
        if (time.end() > latestEnd.end()){
            latestEnd = time;
        }
      }
      TimeRange endOfDay = TimeRange.fromStartEnd(latestEnd.end(), TimeRange.END_OF_DAY, true);
      if (endOfDay.duration() >= request.getDuration()){
        possibleRanges.add(endOfDay);
      }
    }
    if (eventRanges.size() == 0 && request.getDuration() < TimeRange.WHOLE_DAY.duration()){
      TimeRange fullDay = TimeRange.fromStartEnd(TimeRange.START_OF_DAY, TimeRange.END_OF_DAY, true);
      possibleRanges.add(fullDay);
    }
    return possibleRanges;
  }

  private List<TimeRange> sortedRangesInQuestion(Collection<Event> events, Collection<String> attendees) {
      List<TimeRange> ranges = events.stream().filter(event -> !Collections.disjoint(event.getAttendees(), 
        attendees)).map(event -> event.getWhen()).collect(Collectors.toList());
      Collections.sort(ranges, TimeRange.ORDER_BY_START);
      return ranges;
  }
}
