package org.resourceservice.api;

import lombok.RequiredArgsConstructor;
import org.apache.tika.exception.TikaException;
import org.resourceservice.domain.Resource;
import org.resourceservice.domain.ResourceRecord;
import org.resourceservice.service.ResourceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/resources")
@RequiredArgsConstructor
public class ResourceController {

    private final ResourceService resourceService;

    @PostMapping(consumes = {MediaType.ALL_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public ResourceRecord saveResource(@RequestParam("file") MultipartFile multipartFile) throws TikaException, IOException, SAXException {
        return resourceService.saveResource(multipartFile);
    }


    @GetMapping("/{id}")
    public Resource getResource(@PathVariable Long id) {
        return resourceService.getResource(id);
    }


}
