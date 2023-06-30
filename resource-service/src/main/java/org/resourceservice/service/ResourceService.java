package org.resourceservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.mp3.LyricsHandler;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.resourceservice.domain.Resource;
import org.resourceservice.domain.ResourceRecord;
import org.resourceservice.repository.ResourceRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
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
    private final BodyContentHandler bodyContentHandler;
    private final Metadata metadata;

    public ResourceRecord saveResource(MultipartFile multipartFile) throws IOException, TikaException, SAXException {
        File songFile = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        FileInputStream inputstream = new FileInputStream(BASE_PATH + songFile.getPath());
        log.info("SONGFILE: " + multipartFile);
        log.info("PATH: " + songFile.getPath());
        ParseContext pcontext = new ParseContext();

        //Mp3 parser
        Mp3Parser Mp3Parser = new Mp3Parser();
        Mp3Parser.parse(inputstream, bodyContentHandler, metadata, pcontext);
        LyricsHandler lyrics = new LyricsHandler(inputstream, bodyContentHandler);

        while (lyrics.hasLyrics()) {
            System.out.println(lyrics.toString());
        }


        Resource resource = Resource.builder()
                .createdAt(LocalDateTime.now())
                .build();

        resourceRepository.save(resource);
        return ResourceRecord.builder()
                .id(Long.valueOf(resource.getId()))
                .build();
    }

    public Resource getResource(Long id) {
        return resourceRepository.findById(Math.toIntExact(id))
                .orElseThrow(() -> new RuntimeException("Resource with this id could not be found"));
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
