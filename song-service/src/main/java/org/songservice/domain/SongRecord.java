package org.songservice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SongRecord {
    private Integer resourceId;
    private String name;
    private String artist;
    private String album;
    private String length;
    private String year;
}
