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

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;


public final class FindMeetingQuery {
  public Collection<TimeRange> query(Collection<Event> events, MeetingRequest request) {
    List<TimeRange> eventRanges = sortedRangesInQuestion(events, request.getAttendees());
    List<TimeRange> possibleRanges = new ArrayList<TimeRange>();
    int startingPointer = 0;
    int endingPointer = 1;
    System.out.println(eventRanges.size());
    while(endingPointer < eventRanges.size()) {
      if(eventRanges.get(startingPointer).contains(eventRanges.get(endingPointer))) {
        endingPointer++;
      }
      else if(eventRanges.get(startingPointer).overlaps(eventRanges.get(endingPointer))) {
        startingPointer = endingPointer;
        endingPointer++;
      }
      else {
        TimeRange between = TimeRange.fromStartEnd(eventRanges.get(startingPointer).end(),
            eventRanges.get(endingPointer).start(), false);
        if(between.duration() >= request.getDuration()){
          possibleRanges.add(between);
        }
        startingPointer++;
        endingPointer++;
      }
    }
    if(eventRanges.size() >= 1){
      TimeRange startOfDay = TimeRange.fromStartEnd(TimeRange.START_OF_DAY, eventRanges.get(0).start(), false);
      if(startOfDay.duration() >= request.getDuration()){
        possibleRanges.add(startOfDay);
      }
      TimeRange latestEnd = eventRanges.get(0);
      for(TimeRange time : eventRanges){
        if(time.end() > latestEnd.end()){
            latestEnd = time;
        }
      }
      TimeRange endOfDay = TimeRange.fromStartEnd(latestEnd.end(), TimeRange.END_OF_DAY, true);
      if(endOfDay.duration() >= request.getDuration()){
        possibleRanges.add(endOfDay);
      }
    }
    if(eventRanges.size() == 0 && request.getDuration() < TimeRange.WHOLE_DAY.duration()){
      TimeRange fullDay = TimeRange.fromStartEnd(TimeRange.START_OF_DAY, TimeRange.END_OF_DAY, true);
      possibleRanges.add(fullDay);
    }
    Collections.sort(possibleRanges, TimeRange.ORDER_BY_START);
    return possibleRanges;
  }

  public List<TimeRange> sortedRangesInQuestion(Collection<Event> events, Collection<String> attendees) {
      List<TimeRange> ranges = new ArrayList<TimeRange>();
      for(Event event : events){
        if(!Collections.disjoint(event.getAttendees(), attendees)){
          ranges.add(event.getWhen());
        }
      }
      Collections.sort(ranges, TimeRange.ORDER_BY_START);
      return ranges;
  }
}
