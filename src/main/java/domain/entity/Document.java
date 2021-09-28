package domain.entity;

import domain.entity.base.Identity;

import java.util.Date;
import java.util.Objects;


/**
 * @author <a href="mailto:a7l97nn@protonmail.com">Aleksei Laptev</a> on 17-Mar-2020
 */
public class Document
        extends Identity<Long>
{

    private static final long serialVersionUID = 7367904771929968486L;

    private Long id;

    private Date created;


    public Document() {
        /*
         * Empty constructor for creating new empty instance of a class
         */
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

    @Override
    public String toString() {
        return "{" +
                "\"id\":" + id +
                ", \"created\":" + "\"" + created + "\"" +
                ", \"@class\":" + "\"" + this.getClass().getCanonicalName() + "\"" +
                '}';
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        if ( !super.equals( o ) ) return false;
        Document document = ( Document ) o;
        return Objects.equals( id, document.id ) &&
                Objects.equals( created, document.created );
    }

    @Override
    public int hashCode() {
        return Objects.hash( id, created );
    }
}
