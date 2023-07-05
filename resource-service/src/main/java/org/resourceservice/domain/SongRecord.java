package org.resourceservice.domain;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SongRecord {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String artist;

    @Lob
    @Column(length = 10000)
    private byte[] data;
}
