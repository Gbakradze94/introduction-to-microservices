package org.resourceservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.T;
import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.mp3.LyricsHandler;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.resourceservice.domain.Mp3Record;
import org.resourceservice.domain.Resource;
import org.resourceservice.domain.ResourceRecord;
import org.resourceservice.domain.SongRecord;
import org.resourceservice.repository.Mp3RecordRepository;
import org.resourceservice.repository.ResourceRepository;
import org.songservice.domain.SongRecordId;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.xml.sax.SAXException;
import reactor.core.publisher.Mono;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ResourceService {

    public static final String BASE_PATH = "C:\\Users\\Giorgi_Bakradze\\IdeaProjects\\microservices-architecture-overview\\resource-service\\src\\test\\resources\\files\\";
    private final ResourceRepository resourceRepository;
    private final Mp3RecordRepository mp3RecordRepository;
    private final BodyContentHandler bodyContentHandler;
    private final Metadata metadata;
    private final WebClient webClient;

    public ResourceRecord saveResource(MultipartFile multipartFile) throws IOException, TikaException, SAXException {

        saveSongMetaData(multipartFile);
        Mp3Record record = Mp3Record.builder()
                .data(multipartFile.getBytes())
                .build();
        SongRecord songRecord = SongRecord.builder()
                .id(1L)
                .name("Hello")
                .artist("Beatles")
                .build();
        mp3RecordRepository.save(record);

        Mono<SongRecordId> songRecordId = webClient
                .method(HttpMethod.POST)
                .uri("http://localhost:8080/api/v1/songs")
                .body(BodyInserters.fromValue(songRecord))
                .exchangeToMono(response -> response.toEntity(SongRecordId.class))
                .map(HttpEntity::getBody);

        return ResourceRecord.builder()
                .id(Mono.just(songRecordId.block().getId()).block())
                .build();
    }

    public Resource getResource(Long id) {
        return resourceRepository.findById(Math.toIntExact(id))
                .orElseThrow(() -> new RuntimeException("Resource with this id could not be found"));
    }

    private void saveSongMetaData(MultipartFile multipartFile) throws IOException, TikaException, SAXException {
        File songFile = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        FileInputStream inputstream = new FileInputStream(BASE_PATH + songFile.getPath());
        ParseContext pcontext = new ParseContext();

        Mp3Parser Mp3Parser = new Mp3Parser();
        Mp3Parser.parse(inputstream, bodyContentHandler, metadata, pcontext);
    }

    public List<ResourceRecord> deleteByIds(int[] ids) {
        Arrays.stream(ids)
                .forEach(resourceRepository::deleteById);

        return Arrays.stream(ids)
                .mapToObj(i -> Long.parseLong(String.valueOf(i)))
                .map(ResourceRecord::new)
                .collect(Collectors.toList());
    }
}
