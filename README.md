# MeetingCrammer
This is a project written as a recruitment assignment "Spotkajmy się" for internship at Orange.

##### Disclaimer
At the moment, this project lacks any sort of input validation, so if the input files/arguments are incorrect
errors are bound to happen. At the time of writing this project I was in the midst of an exam session, so I didn't have
much time before the deadline. I plan to fix that when I have the time.

## How it works
Whether you build it yourself or just download the  [latest release](https://github.com/k-piekarczyk/MeetingCrammer/releases),
it works the same:

```shell script
java -jar MeetingCrammer.jar path_to_1st_calendar path_to_2st_calendar meeting_duration_in_hh:mm_format
```

#### Calendar format
The calendars should be `JSON` files with this exact format:
```json
{
  "working_hours": {
    "start": "09:00",
    "end": "20:00"
  },
  "planned_meeting": [
    {
      "start": "09:00",
      "end": "10:30"
    },
    {
      "start": "12:00",
      "end": "13:00"
    },
    {
      "start": "16:00",
      "end": "18:30"
    }
  ]
}
```

## Example
##### Used calendars:
calendar1.json
```json
{
  "working_hours": {
    "start": "09:00",
    "end": "20:00"
  },
  "planned_meeting": [
    {
      "start": "09:00",
      "end": "10:30"
    },
    {
      "start": "12:00",
      "end": "13:00"
    },
    {
      "start": "16:00",
      "end": "18:30"
    }
  ]
}
```
calendar2.json
```json
{
  "working_hours": {
    "start": "10:00",
    "end": "18:30"
  },
  "planned_meeting": [
    {
      "start": "10:00",
      "end": "11:30"
    },
    {
      "start": "12:30",
      "end": "14:30"
    },
    {
      "start": "14:30",
      "end": "15:00"
    },
    {
      "start": "16:00",
      "end": "17:00"
    }
  ]
}
```

##### Command line:
```shell script
java -jar MeetingCrammer.jar calendar1.json calendar2.json 00:30
```

##### Output
```
[["11:30", "12:00"], ["15:00", "16:00"]]
```

## The algorithm
The algorithm is quite simple:

1. All meetings are pushed to the same list and sorted by start time and duration.
2. The overlaping meetings are merged, this way the list contains only time separated meetings.
3. A new list is created based on and the previous list of meetings and the work times specified in both calendars.
This list contains free times that match both calendars.
4. And lastly, the list of free times is filtered, removing all the timespans shorter than the specified meeting duration.
