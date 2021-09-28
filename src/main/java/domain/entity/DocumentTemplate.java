package domain.entity;

import domain.entity.base.Identity;
import domain.entity.template.Template;

import java.util.Objects;


/**
 * This class created for declaring relationship between Documents and Templates.
 *
 * @author <a href="mailto:a7l97nn@protonmail.com">Aleksei Laptev</a> on 17-Mar-2020
 */
public class DocumentTemplate
        extends Identity<Long>
{

    private static final long serialVersionUID = 2779769902353819454L;

    private Long id;

    private Document document;

    private Template template;


    public DocumentTemplate() {
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

    public Document getDocument() {
        return document;
    }

    public void setDocument( Document document ) {
        this.document = document;
    }

    public Template getTemplate() {
        return template;
    }

    public void setTemplate( Template template ) {
        this.template = template;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\":" + id +
                ", \"document\":" + document.getId() +
                ", \"template\":" + template.getId() +
                ", \"@class\":" + "\"" + this.getClass().getCanonicalName() + "\"" +
                '}';
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        if ( !super.equals( o ) ) return false;
        DocumentTemplate that = ( DocumentTemplate ) o;
        return id.equals( that.id ) &&
                document.equals( that.document ) &&
                template.equals( that.template );
    }

    @Override
    public int hashCode() {
        return Objects.hash( super.hashCode(), id, document, template );
    }
}
