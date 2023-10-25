package dbp.techcall.booking.models;


import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="meeting_details")
public class MeetingDetails {
    @Id
    @Column(name = "id",nullable = false)
    private Integer id;

    @Column(name="end_date",nullable = false)
    private String endDate;

    @Column(name="is_locked",nullable = false)
    private Boolean isLocked;

    @Column(name="room_mode",nullable = false)
    private String roomMode;

   @Column(name="room_name_prefix",nullable = false)
    private String roomNamePrefix;

    @Column(name="template_type",nullable = false)
    private String templateType;

//    @Column(name="recording",nullable = false)
//    private  recording; //jsonb
    //type jsonb

    @Column(name="hostroomurl",nullable = false)
    private String hostRoomUrl;

    @Column(name="viewerroomurl",nullable = false)
    private String viewerRoomUrl;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name="booking_id", referencedColumnName="id", insertable = false, updatable = false)
    private Booking bookingId;

}