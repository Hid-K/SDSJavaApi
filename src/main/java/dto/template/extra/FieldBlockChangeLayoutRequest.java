package dto.template.extra;

import java.io.Serializable;
import java.util.Objects;


/**
 * Abstract class for request of changing {@link FieldBlock} layout.
 *
 * @author <a href="mailto:a7l97nn@protonmail.com">Aleksei Laptev</a> on 28-Apr-2021
 */
public class FieldBlockChangeLayoutRequest implements Serializable {

    private static final long serialVersionUID = -8956140004129323703L;

    private final Long fieldBlockId;
    private final Long customFieldId;
    private final Integer position;


    public FieldBlockChangeLayoutRequest( Long fieldBlockId, Long customFieldId, Integer position ) {

        if ( fieldBlockId < 0 ) {
            throw new IllegalArgumentException( "FieldBlock id must not be less than zero!" );
        }
        if ( customFieldId != null && customFieldId < 0 ) {
            throw new IllegalArgumentException( "CustomField id must not be less than zero!" );
        }
        if ( position != null && position < 0 ) {
            throw new IllegalArgumentException( "Position index must not be less than zero!" );
        }

        this.fieldBlockId = fieldBlockId;
        this.customFieldId = customFieldId;
        this.position = position;
    }


    public Long getFieldBlockId() {
        return fieldBlockId;
    }

    public Long getCustomFieldId() {
        return customFieldId;
    }

    public Integer getPosition() {
        return position;
    }


    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        FieldBlockChangeLayoutRequest that = ( FieldBlockChangeLayoutRequest ) o;
        return Objects.equals( fieldBlockId, that.fieldBlockId ) && Objects.equals( customFieldId, that.customFieldId ) && Objects.equals( position, that.position );
    }

    @Override
    public int hashCode() {
        return Objects.hash( fieldBlockId, customFieldId, position );
    }
}
