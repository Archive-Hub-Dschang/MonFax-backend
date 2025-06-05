package com.LDE.monFax_backend.models;

import com.LDE.monFax_backend.enumerations.ResourceType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@Builder
@AllArgsConstructor
@Entity
public class Resource {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String pdfUrl;

    private Long size;

    private  Long uploadableId;

    private ResourceType uploadableType;

    private Long numberOfDownload;

    private Long numberOfView;
}
