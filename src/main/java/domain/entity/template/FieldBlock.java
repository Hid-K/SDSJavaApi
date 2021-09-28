package domain.entity.template;

import domain.entity.base.Identity;

import java.util.Objects;


/**
 * @author <a href="mailto:a7l97nn@protonmail.com">Aleksei Laptev</a> on 17-Mar-2020
 */
public class FieldBlock
        extends Identity<Long>
{

    private static final long serialVersionUID = 5501524175846836725L;

    private Long id;

    private String name;

    private String description;


    public FieldBlock() {
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
        FieldBlock that = ( FieldBlock ) o;
        return id.equals( that.id ) &&
                name.equals( that.name ) &&
                Objects.equals( description, that.description );
    }

    @Override
    public int hashCode() {
        return Objects.hash( id, name, description );
    }
}
