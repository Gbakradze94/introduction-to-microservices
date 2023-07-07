package org.songservice.service;

import org.songservice.domain.Song;
import org.songservice.domain.SongRecord;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SongMapper implements Converter<Song, SongRecord> {

    @Override
    public SongRecord convert(Song song) {
        return SongRecord.builder()
                .name(song.getName())
                .artist(song.getArtist())
                .album(song.getAlbum())
                .year(song.getYear())
                .length(song.getLength())
                .build();
    }

    public Song mapToEntity(SongRecord songRecord) {
        return Song.builder()
                .songId(songRecord.getResourceId())
                .name(songRecord.getName())
                .artist(songRecord.getArtist())
                .album(songRecord.getAlbum())
                .year(songRecord.getYear())
                .length(songRecord.getLength())
                .build();
    }
}
