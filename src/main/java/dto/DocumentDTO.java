package dto;

import domain.builder.DocumentBuilder;
import domain.entity.Document;

import java.util.Date;
import java.util.List;
import java.util.Objects;


/**
 * DTO for {@link Document}
 *
 * @author <a href="mailto:a7l97nn@protonmail.com">Aleksei Laptev</a> on 12-Aug-2020
 */
public class DocumentDTO {

    private Long id;

    private Date created;

    private List<Long> assignedTemplates;


    public DocumentDTO() {
    }

    public DocumentDTO( Long id, Date created ) {
        this.id = id;
        this.created = created;
    }

    public DocumentDTO( Document document ) {
        this.id = document.getId();
        this.created = document.getCreated();
    }


    public Long getId() {
        return id;
    }

    public void setId( Long id ) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated( Date created ) {
        this.created = created;
    }

    public List<Long> getAssignedTemplates() {
        return assignedTemplates;
    }

    public void setAssignedTemplates( List<Long> assignedTemplates ) {
        this.assignedTemplates = assignedTemplates;
    }

    /**
     * This method can be used when you want to create real object from DTO instance
     *
     * @return Real POJO instead of DTO
     */
    public Document createRealObject() {
        return new DocumentBuilder().setId( id ).setCreated( created ).build();
    }

    /**
     * This method converts real POJO to related DTO.
     *
     * @param document Object which should be converted.
     *
     * @return DTO instance.
     */
    public static DocumentDTO createDtoFromRealObject( Document document ) {
        return new DocumentDTO( document.getId(), document.getCreated() );
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        DocumentDTO that = ( DocumentDTO ) o;
        return Objects.equals( id, that.id ) &&
                Objects.equals( created, that.created ) &&
                Objects.equals( assignedTemplates, that.assignedTemplates );
    }

    @Override
    public int hashCode() {
        return Objects.hash( id, created, assignedTemplates );
    }

    @Override
    public String toString()
    {
        return "DocumentDTO{" +
                "id=" + id +
                ", created=" + created +
                ", assignedTemplates=" + assignedTemplates +
                '}';
    }
}
