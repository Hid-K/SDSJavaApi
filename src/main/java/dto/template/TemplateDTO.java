package dto.template;



import domain.builder.TemplateEntityBuilder;
import domain.entity.template.Template;

import java.util.Objects;


/**
 * DTO for {@link Template}
 *
 * @author <a href="mailto:a7l97nn@protonmail.com">Aleksei Laptev</a> on 28-Jul-2020
 */
public class TemplateDTO {

    protected Long id;

    protected String name;

    protected String description;

    public TemplateDTO() {
    }

    public TemplateDTO( Long id, String name, String description ) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public TemplateDTO( Template template ) {
        this.id = template.getId();
        this.name = template.getName();
        this.description = template.getDescription();
    }

    public Long getId() {
        return id;
    }

    public void setId( Long id ) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription( String description ) {
        this.description = description;
    }

    /**
     * This method can be used when you want to create real object from DTO instance
     *
     * @return Real POJO instead of DTO
     */
    public Template createRealObject() {
        return new TemplateEntityBuilder.TemplateBuilder()
                .setId( id )
                .setName( name )
                .setDescription( description )
                .build();
    }

    /**
     * This method converts real POJO to related DTO.
     *
     * @param template Object which should be converted.
     *
     * @return DTO instance.
     */
    public static TemplateDTO createDtoFromRealObject( Template template ) {
        return new TemplateDTO(
                template.getId(),
                template.getName(),
                template.getDescription() );
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        TemplateDTO that = ( TemplateDTO ) o;
        return Objects.equals( id, that.id ) &&
                Objects.equals( name, that.name ) &&
                Objects.equals( description, that.description );
    }

    @Override
    public String toString() {
        return "TemplateDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash( id, name, description );
    }
}
