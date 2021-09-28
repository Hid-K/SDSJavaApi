package dto.template;


import domain.builder.TemplateEntityBuilder;
import domain.entity.template.TemplateLayout;

import java.util.Objects;


/**
 * @author <a href="mailto:a7l97nn@protonmail.com">Aleksei Laptev</a> on 13-Aug-2020
 */
public class TemplateLayoutDTO {

    private Long id;

    private Integer sequence;

    private Long templateId;

    private Long fieldBlockId;


    public TemplateLayoutDTO() {
    }

    public TemplateLayoutDTO( Long id, Integer sequence, Long templateId, Long fieldBlockId ) {
        this.id = id;
        this.sequence = sequence;
        this.templateId = templateId;
        this.fieldBlockId = fieldBlockId;
    }

    public TemplateLayoutDTO( TemplateLayout templateLayout ) {
        id = templateLayout.getId();
        sequence = templateLayout.getSequence();
        templateId = templateLayout.getTemplate().getId();
        fieldBlockId = templateLayout.getFieldBlock().getId();
    }


    public Long getId() {
        return id;
    }

    public TemplateLayoutDTO setId( Long id ) {
        this.id = id;
        return this;
    }

    public Integer getSequence() {
        return sequence;
    }

    public TemplateLayoutDTO setSequence( Integer sequence ) {
        this.sequence = sequence;
        return this;
    }

    public Long getTemplateId() {
        return templateId;
    }

    public TemplateLayoutDTO setTemplateId( Long templateId ) {
        this.templateId = templateId;
        return this;
    }

    public Long getFieldBlockId() {
        return fieldBlockId;
    }

    public TemplateLayoutDTO setFieldBlockId( Long fieldBlockId ) {
        this.fieldBlockId = fieldBlockId;
        return this;
    }

    /**
     * This method can be used when you want to create real object from DTO instance
     *
     * @return Real POJO instead of DTO
     */
    public TemplateLayout createRealObject() {
        return new TemplateEntityBuilder.TemplateLayoutBuilder()
                .setId( id )
                .setSequence( 1 )
                .setTemplate( new TemplateEntityBuilder.TemplateBuilder().setId( templateId ).build() )
                .setFieldBlock( new TemplateEntityBuilder.FieldBlockBuilder().setId( fieldBlockId ).build() )
                .build();
    }

    /**
     * This method converts real POJO to related DTO.
     *
     * @param templateLayout Object which should be converted.
     *
     * @return DTO instance.
     */
    public static TemplateLayoutDTO createDtoFromRealObject( TemplateLayout templateLayout ) {
        return new TemplateLayoutDTO(
                templateLayout.getId(),
                templateLayout.getSequence(),
                templateLayout.getTemplate().getId(),
                templateLayout.getFieldBlock().getId() );
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        TemplateLayoutDTO that = ( TemplateLayoutDTO ) o;
        return Objects.equals( id, that.id ) &&
                Objects.equals( sequence, that.sequence ) &&
                Objects.equals( templateId, that.templateId ) &&
                Objects.equals( fieldBlockId, that.fieldBlockId );
    }

    @Override
    public int hashCode() {
        return Objects.hash( id, sequence, templateId, fieldBlockId );
    }
}
