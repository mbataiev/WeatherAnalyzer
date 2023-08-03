package com.app.data.notifier.entity;

import com.app.data.notifier.dto.ValidWeather;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "notifications")
public class UserNotification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String city;
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ValidWeather weather;

}
