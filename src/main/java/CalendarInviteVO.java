import biweekly.property.Attendee;
import biweekly.property.Organizer;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by mahesh on 1/20/17.
 */
public class CalendarInviteVO implements Serializable{
    private String summary;
    private Date startDate;
    private Integer duration;
    private Organizer organizer;
    private List<Attendee> attendees;
    private String message;
    //you can add location of the event too

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Organizer getOrganizer() {
        return organizer;
    }

    public void setOrganizer(Organizer organizer) {
        this.organizer = organizer;
    }

    public List<Attendee> getAttendees() {
        return attendees;
    }

    public void setAttendees(List<Attendee> attendees) {
        this.attendees = attendees;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
