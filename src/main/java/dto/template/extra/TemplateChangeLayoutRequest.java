package dto.template.extra;

import java.io.Serializable;
import java.util.Objects;


/**
 * Abstract class for request of changing {@link Template} layout.
 *
 * @author <a href="mailto:a7l97nn@protonmail.com">Aleksei Laptev</a> on 28-Apr-2021
 */
public class TemplateChangeLayoutRequest implements Serializable {

    private static final long serialVersionUID = 2634858596953282477L;

    private final Long templateId;
    private final Long fieldBlockId;
    private final Integer position;


    public TemplateChangeLayoutRequest( Long templateId, Long fieldBlockId, Integer position ) {

        if ( templateId < 0 ) {
            throw new IllegalArgumentException( "Template id must not be less than zero!" );
        }
        if ( fieldBlockId != null && fieldBlockId < 0 ) {
            throw new IllegalArgumentException( "FieldBlock id must not be less than zero!" );
        }
        if ( position != null && position < 0 ) {
            throw new IllegalArgumentException( "Position index must not be less than zero!" );
        }

        this.templateId = templateId;
        this.fieldBlockId = fieldBlockId;
        this.position = position;
    }


    public Long getTemplateId() {
        return templateId;
    }

    public Long getFieldBlockId() {
        return fieldBlockId;
    }

    public Integer getPosition() {
        return position;
    }


    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        TemplateChangeLayoutRequest that = ( TemplateChangeLayoutRequest ) o;
        return Objects.equals( templateId, that.templateId ) && Objects.equals( fieldBlockId, that.fieldBlockId ) && Objects.equals( position, that.position );
    }

    @Override
    public int hashCode() {
        return Objects.hash( templateId, fieldBlockId, position );
    }
}
