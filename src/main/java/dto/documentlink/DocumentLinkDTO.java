package dto.documentlink;

import domain.entity.documentlink.DocumentLink;
import dto.DocumentDTO;

import java.io.Serializable;
import java.util.Objects;

/**
 * Data transfer object for {@link DocumentLink}.
 *
 * @author <a href="mailto:a7l97nn@protonmail.com">Aleksei Laptev</a> on 23-Aug-2021
 */
public class DocumentLinkDTO implements Serializable {

    private static final long serialVersionUID = 8756775996474346853L;

    private Long id;
    private DocumentDTO sourceDocument;
    private DocumentDTO targetDocument;
    private DocumentLinkTypeDTO linkType;


    public DocumentLinkDTO() {
    }

    public DocumentLinkDTO( DocumentLink documentLink ) {
        this.id = documentLink.getId();
        this.sourceDocument = new DocumentDTO( documentLink.getSourceDocument() );
        this.targetDocument = new DocumentDTO( documentLink.getTargetDocument() );
        this.linkType = new DocumentLinkTypeDTO( documentLink.getLinkType() );
    }


    public Long getId() {
        return id;
    }

    public void setId( Long id ) {
        this.id = id;
    }

    public DocumentDTO getSourceDocument() {
        return sourceDocument;
    }

    public void setSourceDocument( DocumentDTO sourceDocument ) {
        this.sourceDocument = sourceDocument;
    }

    public DocumentDTO getTargetDocument() {
        return targetDocument;
    }

    public void setTargetDocument( DocumentDTO targetDocument ) {
        this.targetDocument = targetDocument;
    }

    public DocumentLinkTypeDTO getLinkType() {
        return linkType;
    }

    public void setLinkType( DocumentLinkTypeDTO linkType ) {
        this.linkType = linkType;
    }

    public DocumentLink createRealObject() {
        DocumentLink documentLink = new DocumentLink();
        documentLink.setId( id );
        documentLink.setSourceDocument( sourceDocument.createRealObject() );
        documentLink.setTargetDocument( targetDocument.createRealObject() );
        documentLink.setLinkType( linkType.createRealObject() );
        return documentLink;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        DocumentLinkDTO that = ( DocumentLinkDTO ) o;
        return Objects.equals( id, that.id ) && Objects.equals( sourceDocument, that.sourceDocument ) && Objects.equals( targetDocument, that.targetDocument ) && Objects.equals( linkType, that.linkType );
    }

    @Override
    public int hashCode() {
        return Objects.hash( id, sourceDocument, targetDocument, linkType );
    }
}
