package domain.entity.template;

import domain.entity.CustomField;
import domain.entity.base.Identity;

import java.util.Objects;


/**
 * @author <a href="mailto:a7l97nn@protonmail.com">Aleksei Laptev</a> on 17-Mar-2020
 */
public class FieldBlockLayout
        extends Identity<Long>
{

    private static final long serialVersionUID = -8224256233647161876L;

    private Long id;

    private Integer sequence;

    private FieldBlock fieldBlock;

    private CustomField customField;

    private boolean required;


    public FieldBlockLayout() {
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

    public FieldBlock getFieldBlock() {
        return fieldBlock;
    }

    public void setFieldBlock( FieldBlock fieldBlock ) {
        this.fieldBlock = fieldBlock;
    }

    public CustomField getCustomField() {
        return customField;
    }

    public void setCustomField( CustomField customField ) {
        this.customField = customField;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired( boolean requiredField ) {
        this.required = requiredField;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        if ( !super.equals( o ) ) return false;
        FieldBlockLayout that = ( FieldBlockLayout ) o;
        return required == that.required &&
                Objects.equals( id, that.id ) &&
                Objects.equals( sequence, that.sequence ) &&
                Objects.equals( fieldBlock, that.fieldBlock ) &&
                Objects.equals( customField, that.customField );
    }

    @Override
    public int hashCode() {
        return Objects.hash( super.hashCode(), id, sequence, fieldBlock, customField, required );
    }
}
