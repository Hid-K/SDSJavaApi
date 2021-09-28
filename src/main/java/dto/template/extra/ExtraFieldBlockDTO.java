package dto.template.extra;

import domain.entity.template.FieldBlock;
import dto.customfield.extra.ExtraCustomFieldDTO;
import dto.template.FieldBlockDTO;

import java.util.List;
import java.util.Objects;


/**
 * DTO for {@link FieldBlock}
 *
 * @author <a href="mailto:a7l97nn@protonmail.com">Aleksei Laptev</a> on 19-Feb-2021
 */
public class ExtraFieldBlockDTO extends FieldBlockDTO
{

    private List<ExtraCustomFieldDTO> content;

    public ExtraFieldBlockDTO() {
        super();
    }

    public ExtraFieldBlockDTO( FieldBlock fieldBlock ) {
        super( fieldBlock );
    }

    public void setContent( List<ExtraCustomFieldDTO> content ) {
        this.content = content;
    }

    public List<ExtraCustomFieldDTO> getContent() {
        return content;
    }


    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        if ( !super.equals( o ) ) return false;
        ExtraFieldBlockDTO that = ( ExtraFieldBlockDTO ) o;
        return Objects.equals( content, that.content );
    }

    @Override
    public int hashCode() {
        return Objects.hash( super.hashCode(), content );
    }
}
