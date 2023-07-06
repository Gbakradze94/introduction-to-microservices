package org.resourceservice.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Year;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class SongRecord {
    @Id
    @GeneratedValue
    private Integer resourceId;
    private String name;
    private String artist;
    private String album;
    private String length;

    private String year;
    @Lob
    @Column(length = 10000)
    private byte[] data;
}
