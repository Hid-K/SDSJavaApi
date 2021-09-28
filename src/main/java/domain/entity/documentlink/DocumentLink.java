package domain.entity.documentlink;

import domain.entity.Document;

import java.util.Objects;

/**
 * @author <a href="mailto:a7l97nn@protonmail.com">Aleksei Laptev</a> on 23-Aug-2021
 */
public class DocumentLink {

    private Long id;

    private Document sourceDocument;

    private Document targetDocument;

    private DocumentLinkType linkType;


    public DocumentLink() {
    }


    public Long getId() {
        return id;
    }

    public void setId( Long id ) {
        this.id = id;
    }

    public Document getSourceDocument() {
        return sourceDocument;
    }

    public void setSourceDocument( Document sourceDocument ) {
        this.sourceDocument = sourceDocument;
    }

    public Document getTargetDocument() {
        return targetDocument;
    }

    public void setTargetDocument( Document targetDocument ) {
        this.targetDocument = targetDocument;
    }

    public DocumentLinkType getLinkType() {
        return linkType;
    }

    public void setLinkType( DocumentLinkType linkType ) {
        this.linkType = linkType;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        DocumentLink that = ( DocumentLink ) o;
        return Objects.equals( id, that.id ) && Objects.equals( sourceDocument, that.sourceDocument ) && Objects.equals( targetDocument, that.targetDocument ) && Objects.equals( linkType, that.linkType );
    }

    @Override
    public int hashCode() {
        return Objects.hash( id, sourceDocument, targetDocument, linkType );
    }
}
