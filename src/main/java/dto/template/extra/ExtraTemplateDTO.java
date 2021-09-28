package dto.template.extra;

import domain.entity.template.Template;
import dto.customfield.extra.ExtraCustomFieldDTO;
import dto.template.TemplateDTO;

import java.util.List;
import java.util.Objects;


/**
 * DTO for {@link Template}
 *
 * @author <a href="mailto:a7l97nn@protonmail.com">Aleksei Laptev</a> on 19-Feb-2021
 */
public class ExtraTemplateDTO extends TemplateDTO
{

    protected int relatedDocumentCount;

    protected List<ExtraCustomFieldDTO> defaultCustomFields;

    protected List<ExtraFieldBlockDTO> content;

    public ExtraTemplateDTO() {
        super();
    }

    public ExtraTemplateDTO( Template template ) {
        super( template );
    }


    public int getRelatedDocumentCount() {
        return relatedDocumentCount;
    }

    public void setRelatedDocumentCount( int relatedDocumentCount ) {
        this.relatedDocumentCount = relatedDocumentCount;
    }

    public List<ExtraCustomFieldDTO> getDefaultCustomFields() {
        return defaultCustomFields;
    }

    public void setDefaultCustomFields( List<ExtraCustomFieldDTO> defaultCustomFields ) {
        this.defaultCustomFields = defaultCustomFields;
    }

    public List<ExtraFieldBlockDTO> getContent() {
        return content;
    }

    public void setContent( List<ExtraFieldBlockDTO> content ) {
        this.content = content;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        if ( !super.equals( o ) ) return false;
        ExtraTemplateDTO that = ( ExtraTemplateDTO ) o;
        return relatedDocumentCount == that.relatedDocumentCount && Objects.equals( defaultCustomFields, that.defaultCustomFields ) && Objects.equals( content, that.content );
    }

    @Override
    public int hashCode() {
        return Objects.hash( super.hashCode(), relatedDocumentCount, defaultCustomFields, content );
    }
}
