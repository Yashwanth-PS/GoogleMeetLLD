package com.googlemeet.service;

import com.googlemeet.exception.MeetingNotFoundException;
import com.googlemeet.model.Meeting;
import com.googlemeet.repository.MeetingRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class MeetingService {

    private static int meetingCount = 0;
    private final MeetingRepository meetingRepository;

    public MeetingService(MeetingRepository meetingRepository) {
        this.meetingRepository = meetingRepository;
    }

    public Meeting createMeeting() {
        Meeting meeting = new Meeting();
        meetingCount++;
        meeting.setName("Meeting " + meetingCount);
        Random random = new Random();
        String randomPassword = Integer.toString(random.nextInt(10000)); // Generating a random password as integer
        meeting.setPassword(randomPassword);
        meetingRepository.save(meeting);
        return meeting;
    }

    public boolean joinMeeting(int meetingId, String password) {
        Optional<Meeting> meetingOptional = meetingRepository.findById(meetingId);
        if (meetingOptional.isPresent()) {
            Meeting meeting = meetingOptional.get();
            if (meeting.getPassword().equals(password)) {
                meeting.setStartTime(System.currentTimeMillis());
                meetingRepository.save(meeting);
                return true;
            } else {
                return false; // Password mismatch
            }
        } else {
            throw new MeetingNotFoundException("Meeting with the given ID does not exist");
        }
    }

    public boolean exitMeeting(int meetingId) {
        Optional<Meeting> meetingOptional = meetingRepository.findById(meetingId);
        if (meetingOptional.isPresent()) {
            Meeting meeting = meetingOptional.get();
            meeting.setEndTime(System.currentTimeMillis());
            meetingRepository.save(meeting);
            return true;
        } else {
            throw new MeetingNotFoundException("Meeting with the given ID does not exist");
        }
    }
}