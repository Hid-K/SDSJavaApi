package dto;

import domain.builder.CustomFieldBuilder;
import domain.entity.CustomField;

import java.util.Objects;


/**
 * DTO for {@link CustomField}
 *
 * @author <a href="mailto:a7l97nn@protonmail.com">Aleksei Laptev</a> on 12-Aug-2020
 */
public class CustomFieldDTO {

    private Long id;

    private String name;

    private String description;

    private String type;

    public CustomFieldDTO() {
    }

    public CustomFieldDTO( Long id, String name, String description, String type ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
    }

    public CustomFieldDTO( CustomField customField ) {
        this.id = customField.getId();
        this.name = customField.getName();
        this.description = customField.getDescription();
        this.type = customField.getCustomFieldType();
    }

    @Override
    public String toString()
    {
        return "CustomFieldDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public CustomFieldDTO setId( Long id ) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public CustomFieldDTO setName( String name ) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public CustomFieldDTO setDescription( String description ) {
        this.description = description;
        return this;
    }

    public String getType() {
        return type;
    }

    public CustomFieldDTO setType( String customFieldType ) {
        this.type = customFieldType;
        return this;
    }

    /**
     * This method can be used when you want to create real object from DTO instance
     *
     * @return Real POJO instead of DTO
     */
    public CustomField createRealObject() {
        return new CustomFieldBuilder()
                .setId( id )
                .setName( name )
                .setDescription( description )
                .setCustomFieldType( type )
                .build();
    }

    /**
     * This method converts real POJO to related DTO.
     *
     * @param customField Object which should be converted.
     *
     * @return DTO instance.
     */
    public static CustomFieldDTO createDtoFromRealObject( CustomField customField ) {
        return new CustomFieldDTO(
                customField.getId(),
                customField.getName(),
                customField.getDescription(),
                customField.getCustomFieldType() );
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        CustomFieldDTO that = ( CustomFieldDTO ) o;
        return Objects.equals( id, that.id ) &&
                Objects.equals( name, that.name ) &&
                Objects.equals( description, that.description ) &&
                Objects.equals( type, that.type );
    }

    @Override
    public int hashCode() {
        return Objects.hash( id, name, description, type );
    }
}
