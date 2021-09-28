package domain.entity.template;

import domain.entity.base.Identity;
import java.util.Objects;


/**
 * @author <a href="mailto:a7l97nn@protonmail.com">Aleksei Laptev</a> on 17-Mar-2020
 */
public class TemplateLayout
        extends Identity<Long>
{

    private static final long serialVersionUID = -5338131920800430124L;


    private Long id;

    private Integer sequence;

    private Template template;

    private FieldBlock fieldBlock;


    public TemplateLayout() {
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

    public FieldBlock getFieldBlock() {
        return fieldBlock;
    }

    public void setFieldBlock( FieldBlock fieldBlock ) {
        this.fieldBlock = fieldBlock;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        if ( !super.equals( o ) ) return false;
        TemplateLayout that = ( TemplateLayout ) o;
        return id.equals( that.id ) &&
                sequence.equals( that.sequence ) &&
                template.equals( that.template ) &&
                fieldBlock.equals( that.fieldBlock );
    }

    @Override
    public int hashCode() {
        return Objects.hash( super.hashCode(), id, sequence, template, fieldBlock );
    }
}
