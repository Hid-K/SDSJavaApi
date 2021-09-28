package domain.entity;


import domain.entity.base.Identity;

import java.util.Objects;


/**
 * You can use {@link CustomFieldBuilder} for instantiation of new {@link CustomField} objects.
 *
 * @author <a href="mailto:a7l97nn@protonmail.com">Aleksei Laptev</a> on 17-Mar-2020
 */
public class CustomField
        extends Identity<Long>
{

    private static final long serialVersionUID = 990176007558405929L;


    private Long id;

    private String name;

    private String description;

    private String customFieldType;


    public CustomField() {
    }

    public CustomField( Long id, String name, String description, String customFieldType ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.customFieldType = customFieldType;
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

    public String getCustomFieldType() {
        return customFieldType;
    }

    public void setCustomFieldType( String customFieldType ) {
        this.customFieldType = customFieldType;
    }


    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        if ( !super.equals( o ) ) return false;
        CustomField that = ( CustomField ) o;
        return Objects.equals( id, that.id ) && Objects.equals( name, that.name ) && Objects.equals( description, that.description ) && Objects.equals( customFieldType, that.customFieldType );
    }

    @Override
    public int hashCode() {
        return Objects.hash( super.hashCode(), id, name, description, customFieldType );
    }
}
