package org.resourceservice.service;

import lombok.RequiredArgsConstructor;
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
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ResourceService {

    public Resource getResource(Integer id) {
        return null;
    }

    private final BodyContentHandler bodyContentHandler;
    private final ResourceRepository resourceRepository;

    public ResourceRecord saveResource(MultipartFile multipartFile) throws IOException, TikaException, SAXException {
        //detecting the file type
        // BodyContentHandler handler = new BodyContentHandler();
        Metadata metadata = new Metadata();
        File songFile = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        FileInputStream inputstream = new FileInputStream(songFile);
        ParseContext pcontext = new ParseContext();

        //Mp3 parser
        Mp3Parser Mp3Parser = new Mp3Parser();
        Mp3Parser.parse(inputstream, bodyContentHandler, metadata, pcontext);
        LyricsHandler lyrics = new LyricsHandler(inputstream, bodyContentHandler);

        while (lyrics.hasLyrics()) {
            System.out.println(lyrics.toString());
        }

        System.out.println("Contents of the document:" + bodyContentHandler.toString());
        System.out.println("Metadata of the document:");
        String[] metadataNames = metadata.names();

        for (String name : metadataNames) {
            System.out.println(name + ": " + metadata.get(name));
        }

        Resource resource = Resource.builder()
                .createdAt(LocalDateTime.now())
                .build();

        resourceRepository.save(resource);
        return ResourceRecord.builder()
                .id(Long.valueOf(resource.getId()))
                .build();
    }
}
