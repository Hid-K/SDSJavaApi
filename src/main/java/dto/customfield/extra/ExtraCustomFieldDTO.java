package dto.customfield.extra;

import domain.entity.CustomField;
import dto.CustomFieldDTO;

import java.io.Serializable;
import java.util.Objects;


/**
 * This is an extended DTO class for {@link CustomFieldDTO}.
 *
 * @author <a href="mailto:a7l97nn@protonmail.com">Aleksei Laptev</a> on 24-May-2021
 */
public class ExtraCustomFieldDTO extends CustomFieldDTO implements Serializable {

    private static final long serialVersionUID = 8076318811301628677L;

    // if it's date, then use pattern = "yyyy-MM-dd HH:mm:ss"
    private Object value;


    public ExtraCustomFieldDTO() {
    }

    public ExtraCustomFieldDTO( Long id, String name, String description, String customFieldType ) {
        super( id, name, description, customFieldType );
    }

    public ExtraCustomFieldDTO( CustomField customField ) {
        super( customField );
    }


    public Object getValue() {
        return value;
    }

    public void setValue( Object value ) {
        this.value = value;
    }


    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        if ( !super.equals( o ) ) return false;
        ExtraCustomFieldDTO that = ( ExtraCustomFieldDTO ) o;
        return Objects.equals( value, that.value );
    }

    @Override
    public int hashCode() {
        return Objects.hash( super.hashCode(), value );
    }
}
