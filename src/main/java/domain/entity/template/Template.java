package domain.entity.template;

import domain.entity.base.Identity;

import java.util.Objects;


/**
 * @author <a href="mailto:a7l97nn@protonmail.com">Aleksei Laptev</a> on 17-Mar-2020
 */
public class Template
        extends Identity<Long>
{

    private static final long serialVersionUID = -2759580883900925063L;

    private Long id;

    private String name;

    private String description;


    public Template() {
        /*
         * Empty constructor for creating new empty instance of a class
         */
    }


    @Override
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

    @Override
    public String toString() {
        return "{" +
                "\"id\":" + id +
                ", \"name\":" + "\"" + name + "\"" +
                ", \"description\":" + "\"" + description + "\"" +
                ", \"@class\":" + "\"" + this.getClass().getCanonicalName() + "\"" +
                '}';
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        if ( !super.equals( o ) ) return false;
        Template template = ( Template ) o;
        return Objects.equals( id, template.id ) && Objects.equals( name, template.name ) && Objects.equals( description, template.description );
    }

    @Override
    public int hashCode() {
        return Objects.hash( super.hashCode(), id, name, description );
    }
}
