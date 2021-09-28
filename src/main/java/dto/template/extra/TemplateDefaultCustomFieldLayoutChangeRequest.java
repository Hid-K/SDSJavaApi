package dto.template.extra;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author <a href="mailto:a7l97nn@protonmail.com">Aleksei Laptev</a> on 09-Jul-2021
 */
public class TemplateDefaultCustomFieldLayoutChangeRequest implements Serializable {

    private static final long serialVersionUID = -8615288349502871735L;


    private final Long templateId;
    private final Long customFieldId;
    private final Integer position;

    public TemplateDefaultCustomFieldLayoutChangeRequest( Long templateId, Long customFieldId, Integer position ) {
        if ( templateId < 0 ) {
            throw new IllegalArgumentException( "Template id must not be less than zero!" );
        }
        if ( customFieldId != null && customFieldId < 0 ) {
            throw new IllegalArgumentException( "CustomField id must not be less than zero!" );
        }
        if ( position != null && position < 0 ) {
            throw new IllegalArgumentException( "Position index must not be less than zero!" );
        }

        this.templateId = templateId;
        this.customFieldId = customFieldId;
        this.position = position;
    }

    public Long getTemplateId() {
        return templateId;
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
        TemplateDefaultCustomFieldLayoutChangeRequest that = ( TemplateDefaultCustomFieldLayoutChangeRequest ) o;
        return Objects.equals( templateId, that.templateId ) && Objects.equals( customFieldId, that.customFieldId ) && Objects.equals( position, that.position );
    }

    @Override
    public int hashCode() {
        return Objects.hash( templateId, customFieldId, position );
    }
}
