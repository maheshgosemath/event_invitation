import biweekly.Biweekly;
import biweekly.ICalendar;
import biweekly.component.VAlarm;
import biweekly.component.VEvent;
import biweekly.parameter.Related;
import biweekly.property.*;
import biweekly.util.Duration;
import biweekly.util.Frequency;
import biweekly.util.Recurrence;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by mahesh on 1/20/17.
 */
public class InviteUtil {

    public static String getInvitationBody(CalendarInviteVO calendarInviteVO) {
        List<String> attendeesEmail = new ArrayList<String>();
        ICalendar ical = new ICalendar();
        VEvent event = new VEvent();

        event.setOrganizer(calendarInviteVO.getOrganizer().getEmail());
        event.setUid("123456789012"); //unique to an event
        event.setDateStart(calendarInviteVO.getStartDate());

        Summary summary = event.setSummary(calendarInviteVO.getSummary());
        summary.setLanguage("en-us");
        event.setSummary(summary);

        Duration duration = new Duration.Builder().minutes(calendarInviteVO.getDuration()).build();
        event.setDuration(duration);

        if(calendarInviteVO.getAttendees() != null && calendarInviteVO.getAttendees().size() > 0) {
            for(Attendee attendee: calendarInviteVO.getAttendees()) {
                event.addAttendee(attendee);
                attendeesEmail.add(attendee.getEmail());
            }
        }

        event.setSequence(0);
        event.setCreated(new Date());
        event.setLastModified(new Date());

        /*
        Method.request for new events
        Method.cancel to cancel events
         */
        Method method = Method.request();
        ical.setMethod(method);//yes no maybe (rsvp)
        ical.addEvent(event);
        return Biweekly.write(ical).go();
    }
}
