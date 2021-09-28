package domain.entity.template;

import domain.entity.CustomField;
import domain.entity.base.Identity;

import java.util.Objects;


/**
 * @author <a href="mailto:a7l97nn@protonmail.com">Aleksei Laptev</a> on 09-Jul-2021
 */
public class TemplateDefaultCustomFieldLayout extends Identity<Long>
{

    private static final long serialVersionUID = -8585034611039876497L;

    private Long id;

    private Integer sequence;

    private Template template;

    private CustomField customField;


    @Override
    public Long getId() {
        return id;
    }

    public void setId( Long id ) {
        this.id = id;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence( Integer sequence ) {
        this.sequence = sequence;
    }

    public Template getTemplate() {
        return template;
    }

    public void setTemplate( Template template ) {
        this.template = template;
    }

    public CustomField getCustomField() {
        return customField;
    }

    public void setCustomField( CustomField customField ) {
        this.customField = customField;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        if ( !super.equals( o ) ) return false;
        TemplateDefaultCustomFieldLayout that = ( TemplateDefaultCustomFieldLayout ) o;
        return Objects.equals( id, that.id ) && Objects.equals( sequence, that.sequence ) && Objects.equals( template, that.template ) && Objects.equals( customField, that.customField );
    }

    @Override
    public int hashCode() {
        return Objects.hash( super.hashCode(), id, sequence, template, customField );
    }
}
