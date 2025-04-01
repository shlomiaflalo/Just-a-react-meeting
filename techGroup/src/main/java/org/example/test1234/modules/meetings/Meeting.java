package org.example.test1234.modules.meetings;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.example.test1234.modules.tech_groups.TechGroup;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Meeting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.EAGER)
    private TechGroup techGroup;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String description;
    private String roomName;
}
