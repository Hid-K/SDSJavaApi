package dto.template;

import domain.builder.TemplateEntityBuilder;
import domain.entity.template.FieldBlock;

import java.util.Objects;


/**
 * DTO for {@link FieldBlock}
 *
 * @author <a href="mailto:a7l97nn@protonmail.com">Aleksei Laptev</a> on 12-Aug-2020
 */
public class FieldBlockDTO {

    private Long id;

    private String name;

    private String description;


    public FieldBlockDTO() {
    }

    public FieldBlockDTO( Long id, String name, String description ) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public FieldBlockDTO( FieldBlock fieldBlock ) {
        this.id = fieldBlock.getId();
        this.name = fieldBlock.getName();
        this.description = fieldBlock.getDescription();
    }


    public Long getId() {
        return id;
    }

    public FieldBlockDTO setId( Long id ) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public FieldBlockDTO setName( String name ) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public FieldBlockDTO setDescription( String description ) {
        this.description = description;
        return this;
    }

    /**
     * This method can be used when you want to create real object from DTO instance
     *
     * @return Real POJO instead of DTO
     */
    public FieldBlock createRealObject() {
        return new TemplateEntityBuilder.FieldBlockBuilder()
                .setId( id )
                .setName( name )
                .setDescription( description )
                .build();
    }

    /**
     * This method converts real POJO to related DTO.
     *
     * @param fieldBlock Object which should be converted.
     *
     * @return DTO instance.
     */
    public static FieldBlockDTO createDtoFromRealObject( FieldBlock fieldBlock ) {
        return new FieldBlockDTO(
                fieldBlock.getId(),
                fieldBlock.getName(),
                fieldBlock.getDescription() );
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        FieldBlockDTO that = ( FieldBlockDTO ) o;
        return Objects.equals( id, that.id ) &&
                Objects.equals( name, that.name ) &&
                Objects.equals( description, that.description );
    }

    @Override
    public int hashCode() {
        return Objects.hash( id, name, description );
    }

    @Override
    public String toString()
    {
        return "FieldBlockDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
