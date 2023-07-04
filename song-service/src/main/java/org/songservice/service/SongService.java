package org.songservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.mp3.LyricsHandler;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.songservice.domain.Song;
import org.songservice.domain.SongRecord;
import org.songservice.domain.SongRecordId;
import org.songservice.exception.SongNotFoundException;
import org.songservice.repository.SongRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class SongService {


    private final SongRepository songRepository;
    private final SongMapper songMapper;

    public SongRecordId save(SongRecord songRecord) {
        Song song = songRepository.save(songMapper.mapToEntity(songRecord));
        log.info("ID: " + song.getId());
        return new SongRecordId(song.getId());
    }

    public SongRecord getSongById(Long id) {
        Song song = songRepository.findById(id)
                .orElseThrow(() -> new SongNotFoundException("Song with " + id + "could not be found"));
        return songMapper.convert(song);
    }

    public List<SongRecordId> deleteByIds(int[] ids) {

        Arrays.stream(ids)
                .mapToObj(id -> songRepository.getReferenceById(Long.parseLong(String.valueOf(id))))
                .forEach(songRepository::delete);

        return Arrays.stream(ids)
                .mapToObj(id -> new SongRecordId(Long.parseLong(String.valueOf(id))))
        .collect(Collectors.toList());
    }
}
