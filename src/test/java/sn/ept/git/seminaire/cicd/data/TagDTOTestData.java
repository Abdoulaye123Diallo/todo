package sn.ept.git.seminaire.cicd.data;

import sn.ept.git.seminaire.cicd.dto.TagDTO;

public final class TagDTOTestData extends TestData {

    public static TagDTO defaultDTO() {
        return TagDTO
                .builder()
                .id(Default.id)
                .createdDate(Default.createdDate)
                .lastModifiedDate(Default.lastModifiedDate)
                .version(Default.version)
                .name(Default.name)
                .description(Default.description)
                .build();
    }

    public static TagDTO updatedDTO() {
        return TagDTO
                .builder()
                .id(Default.id)
                .createdDate(Update.createdDate)
                .lastModifiedDate(Update.lastModifiedDate)
                .version(Update.version)
                .name(Update.name)
                .description(Update.description)
                .build();
    }
}
